package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, Integer> {
}
