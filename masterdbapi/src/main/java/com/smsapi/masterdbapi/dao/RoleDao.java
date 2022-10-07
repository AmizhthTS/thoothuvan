package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.RoleModel;


@Repository
public interface RoleDao extends JpaRepository<RoleModel, Integer> {

	RoleModel findByRoleEquals(String role);


}