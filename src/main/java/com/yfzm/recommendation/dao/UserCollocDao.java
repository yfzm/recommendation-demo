package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.UserCollocEntity;
import com.yfzm.recommendation.entity.UserCollocEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollocDao extends JpaRepository<UserCollocEntity, UserCollocEntityPK> {

    Page<UserCollocEntity> findAllByUserUserId(Integer userId, Pageable pageable);

    Page<UserCollocEntity> findAllByCollocation_CollocationId(Integer collocationId, Pageable pageable);

    UserCollocEntity findFirstByUserUserIdAndCollocationCollocationId(Integer userId, Integer collocationId);

}
