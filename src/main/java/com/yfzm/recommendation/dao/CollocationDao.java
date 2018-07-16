package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.CollocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollocationDao extends JpaRepository<CollocationEntity, Integer> {

    CollocationEntity findByCollocationId(Integer id);

}
