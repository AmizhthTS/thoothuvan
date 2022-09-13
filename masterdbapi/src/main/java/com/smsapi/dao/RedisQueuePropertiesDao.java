package com.smsapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.RedisPropertiesModel;




@Repository
public interface RedisQueuePropertiesDao extends JpaRepository<RedisPropertiesModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	RedisPropertiesModel saveAndFlush(RedisPropertiesModel entity);
	

	

}