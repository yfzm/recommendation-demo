package com.yfzm.recommendation.service;

import com.yfzm.recommendation.dao.CollocationDao;
import com.yfzm.recommendation.dao.MongoClothDao;
import com.yfzm.recommendation.dao.UserDao;
import com.yfzm.recommendation.entity.ModelClothItem;
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
public abstract class BaseService {
    final UserDao userDao;
    final MongoClothDao clothDao;
    final CollocationDao collocationDao;

    class InnerStore {
        String clothId;
        Vector attrs;
    }

    List<InnerStore> upperAttrMatrix = new ArrayList<>();
    List<InnerStore> lowerAttrMatrix = new ArrayList<>();

    @Autowired
    public BaseService(UserDao userDao, MongoClothDao clothDao, CollocationDao collocationDao) {
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

    protected class ItemSimilarity {
        String clothId;
        double similarity;

        public ItemSimilarity(String clothId, double similarity) {
            this.clothId = clothId;
            this.similarity = similarity;
        }
    }

    public List<ItemSimilarity> getRecommendedItemIds(Vector vec, int type) {
        List<ItemSimilarity> targetCloth = new ArrayList<>();
        List<ItemSimilarity> distCloth = new ArrayList<>();
        List<ItemSimilarity> perfectCloth = new ArrayList<>();
        for (InnerStore cloth : (type == Constant.UPPER_CLOTH) ? upperAttrMatrix : lowerAttrMatrix) {
            double similarity = vec.dot(cloth.attrs);
            if (similarity > 0.9) {
                perfectCloth.add(new ItemSimilarity(cloth.clothId, similarity));
            } else if (similarity > 0.8) {
                distCloth.add(new ItemSimilarity(cloth.clothId, similarity));
            } else if (similarity > 0.7) {
                targetCloth.add(new ItemSimilarity(cloth.clothId, similarity));
            }
            if (perfectCloth.size() + distCloth.size() >= 5) {
                break;
            }
        }

        List<ItemSimilarity> clothNames = new ArrayList<>(perfectCloth);
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

    Vector convertAttrToVector(ModelClothItem item) {
        List<Double> attrs = new ArrayList<>();
        attrs.addAll(item.getTexture());
        attrs.addAll(item.getFabric());
        attrs.addAll(item.getShape());
        attrs.addAll(item.getPart());
        attrs.addAll(item.getStyle());
        return convertListToVector(attrs);
    }

    Vector convertListToVector(List<Double> list) {
        int len = list.size();
        double[] doubleList = new double[len];
        for (int i = 0; i < len; i++) {
            doubleList[i] = list.get(i);
        }
        return new DenseVector(doubleList);
    }
}
