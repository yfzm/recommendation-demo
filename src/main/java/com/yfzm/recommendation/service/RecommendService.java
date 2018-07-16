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
public class RecommendService {
    private final UserDao userDao;
    private final MongoClothDao clothDao;
    private final CollocationDao collocationDao;

    class InnerStore {
        String clothId;
        Vector attrs;
    }

    private List<InnerStore> upperAttrMatrix = new ArrayList<>();
    private List<InnerStore> lowerAttrMatrix = new ArrayList<>();

    @Autowired
    public RecommendService(UserDao userDao, MongoClothDao clothDao, CollocationDao collocationDao) {
        this.userDao = userDao;
        this.clothDao = clothDao;
        this.collocationDao = collocationDao;
        initData();
    }

    private void initData() {
        List<MongoClothEntity> clothEntities = clothDao.findAll();
        for (MongoClothEntity entity : clothEntities) {
            List<Double> attrs = new ArrayList<>();
            attrs.addAll(entity.getAttr().getTexture());
            attrs.addAll(entity.getAttr().getFabric());
            attrs.addAll(entity.getAttr().getShape());
            attrs.addAll(entity.getAttr().getPart());
            attrs.addAll(entity.getAttr().getStyle());

            Assert.state(attrs.size() == 1000, "数组维数错误");
            InnerStore innerStore = new InnerStore();
            String clothId = entity.getId().toString();
            Vector vector = convertListToVector(attrs);
            innerStore.clothId = clothId;
            innerStore.attrs = vector;

//            double[] doubleArray = new double[attrs.size()];
//            for (int i = 0; i < attrs.size(); i++) {
//                doubleArray[i] = attrs.get(i);
//            }
//            Vector vector = new DenseVector(attrs.size());
//            vector.assign(doubleArray);
            if (entity.getAttr().getType() == Constant.UPPER_CLOTH) {
                upperAttrMatrix.add(innerStore);
            } else {
                lowerAttrMatrix.add(innerStore);
            }
        }
        if (upperAttrMatrix.size() != 540 || lowerAttrMatrix.size() != 540) {
            throw new RuntimeException("miss cloth! " + upperAttrMatrix.size() + " " + lowerAttrMatrix.size());
        } else {
            System.out.println("Load data successfully");
        }


    }

    private static double getSimilarity(Vector v1, Vector v2) {
        return v1.dot(v2);
    }

    private List<String> getRecommendedItemIds(Vector vec) {
        List<String> targetCloth = new ArrayList<>();
        List<String> distCloth = new ArrayList<>();
        List<String> perfectCloth = new ArrayList<>();
        for (InnerStore cloth : upperAttrMatrix) {
            double similarity = vec.dot(cloth.attrs);
            if (similarity > 0.9) {
                perfectCloth.add(cloth.clothId);
            } else if (similarity > 0.8) {
                distCloth.add(cloth.clothId);
            } else if (similarity > 0.7) {
                targetCloth.add(cloth.clothId);
            }
            if (perfectCloth.size() + distCloth.size() >= 5) {
                break;
            }
        }

        List<String> clothNames = new ArrayList<>(perfectCloth);
        if (clothNames.size() >= 5) {
            return clothNames.subList(0, 5);
        }
        clothNames.addAll(distCloth);
        if (clothNames.size() >= 5) {
            return clothNames.subList(0, 5);
        }
        clothNames.addAll(targetCloth);
        if (clothNames.size() >= 5) {
            return clothNames.subList(0, 5);
        }
        return clothNames;
    }

    public List<CollocationEntity> getRecommendation(List<Double> attrs) {
        Vector vec = convertListToVector(attrs);
        List<String> clothIds = getRecommendedItemIds(vec);
        List<CollocationEntity> clothes = new ArrayList<>();
        for (String clothId: clothIds) {
            CollocationEntity collocationEntity = collocationDao.findByUpperId(clothId);
            clothes.add(collocationEntity);
        }
        return clothes;
    }

    private Vector convertListToVector(List<Double> list) {
        int len = list.size();
        double[] doubleList = new double[len];
        for (int i = 0; i < len; i++) {
            doubleList[i] = list.get(i);
        }
        return new DenseVector(doubleList);
    }
}
