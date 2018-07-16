package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.MongoClothEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoClothDao extends MongoRepository<MongoClothEntity, String> {

    List<MongoClothEntity> findAllByAttr_Type(int type);

}
