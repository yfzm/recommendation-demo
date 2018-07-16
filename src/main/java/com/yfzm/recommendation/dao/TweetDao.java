package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetDao extends JpaRepository<TweetEntity, Integer> {
}
