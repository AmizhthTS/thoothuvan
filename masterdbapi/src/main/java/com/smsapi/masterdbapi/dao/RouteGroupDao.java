package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.RouteGroupModel;


@Repository
public interface RouteGroupDao extends JpaRepository<RouteGroupModel, Integer> {


	RouteGroupModel findByGroupnameEquals(String groupname );
	
	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	RouteGroupModel saveAndFlush(RouteGroupModel entity);


}