package com.yfzm.recommendation.service;

import com.yfzm.recommendation.dao.CollocationDao;
import com.yfzm.recommendation.dao.MongoClothDao;
import com.yfzm.recommendation.dao.UserDao;
import com.yfzm.recommendation.entity.CollocationEntity;
import com.yfzm.recommendation.entity.MongoClothEntity;
import com.yfzm.recommendation.util.Constant;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendService extends BaseService {


    public RecommendService(UserDao userDao, MongoClothDao clothDao, CollocationDao collocationDao) {
        super(userDao, clothDao, collocationDao);
    }

    private static double getSimilarity(Vector v1, Vector v2) {
        return v1.dot(v2);
    }



    public List<CollocationEntity> getRecommendation(List<Double> attrs) {
        Vector vec = convertListToVector(attrs);
        List<ItemSimilarity> clothIds = getRecommendedItemIds(vec, Constant.UPPER_CLOTH);
        List<CollocationEntity> clothes = new ArrayList<>();
        for (ItemSimilarity clothId: clothIds) {
            CollocationEntity collocationEntity = collocationDao.findByUpperId(clothId.clothId);
            clothes.add(collocationEntity);
        }
        return clothes;
    }


}
