package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.RedisPropertiesModel;
import com.smsapi.masterdbapi.model.RouteDefaultModel;




@Repository
public interface RedisQueuePropertiesDao extends JpaRepository<RedisPropertiesModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	RedisPropertiesModel saveAndFlush(RedisPropertiesModel entity);
	

	RedisPropertiesModel findByNameEquals(String name);


}