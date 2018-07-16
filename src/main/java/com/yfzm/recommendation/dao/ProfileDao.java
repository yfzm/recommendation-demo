package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.MongoProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends MongoRepository<MongoProfileEntity, String> {

    MongoProfileEntity findByUserId(Integer id);
}
