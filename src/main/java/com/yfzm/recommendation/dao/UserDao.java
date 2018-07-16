package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    UserEntity findFirstByUsername(String name);

    UserEntity findFirstByPhone(String phone);

    UserEntity findByUserId(Integer userId);

    Boolean existsByUsername(String userName);

    Boolean existsByPhone(String phone);

}
