package com.yfzm.recommendation.service;

import com.yfzm.recommendation.dao.CollocationDao;
import com.yfzm.recommendation.dao.MongoClothDao;
import com.yfzm.recommendation.dao.UserDao;
import com.yfzm.recommendation.entity.CollocationEntity;
import com.yfzm.recommendation.entity.ModelClothItem;
import com.yfzm.recommendation.entity.MongoClothEntity;
import com.yfzm.recommendation.util.Constant;
import dto.score.ClothAttributes;
import dto.score.GetScoreForm;
import org.apache.mahout.math.Vector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService extends BaseService {

    public ScoreService(UserDao userDao, MongoClothDao clothDao, CollocationDao collocationDao) {
        super(userDao, clothDao, collocationDao);
    }

    public double getScore(GetScoreForm form) {
        ClothAttributes upper = form.getUpper();
        ClothAttributes lower = form.getLower();
        double vectorScore = getVectorScore(upper.getAttrs(), lower.getAttrs());
        double tagScore = getTagScore(upper.getTags(), lower.getTags());

        return vectorScore + tagScore;
    }

    private double getVectorScore(List<Double> upperAttrs, List<Double> lowerAttrs) {

        Vector upper = convertListToVector(upperAttrs);
        Vector lower = convertListToVector(lowerAttrs);

        double level = getSimilarityLevel(upper, lower, Constant.UPPER_CLOTH);
        if (level < 10) {
            level += getSimilarityLevel(lower, upper, Constant.LOWER_CLOTH);
        }

        System.out.println(level);
        double score = Math.min(70, 7 * level);
        score = Math.max(50, score);

        return score;
    }



    private double getTagScore(List<String> upperTags, List<String> lowerTags) {
        return 30;
    }

    private double getSimilarityLevel(Vector cloth, Vector opCloth, int type) {
        double level = 0;
        List<ItemSimilarity> top5 = getRecommendedItemIds(cloth, type);
        for (ItemSimilarity item: top5) {
            CollocationEntity collocationEntity = (type == Constant.UPPER_CLOTH)
                    ? collocationDao.findByUpperId(item.clothId)
                    : collocationDao.findByLowerId(item.clothId);
            String clothId = (type == Constant.UPPER_CLOTH)
                    ? collocationEntity.getLowerId()
                    : collocationEntity.getUpperId();
            Optional<MongoClothEntity> clothOpt = clothDao.findById(clothId);
            if (clothOpt.isPresent()) {
                Vector matchCloth = convertAttrToVector(clothOpt.get().getAttr());
                double product = item.similarity * matchCloth.dot(opCloth);
                if (product >= 0.81) {
                    level += 10;
                    break;
                } else if (product >= 0.64) {
                    level += 3;
                    if (level >= 10) break;
                } else if (product >= 0.49) {
                    level += 1.5;
                    if (level >= 10) break;
                }
            }
        }
        return level;
    }

}
