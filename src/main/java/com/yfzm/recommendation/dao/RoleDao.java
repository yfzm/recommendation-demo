package com.yfzm.recommendation.dao;

import com.yfzm.recommendation.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByRoleId(Integer roleId);
}
