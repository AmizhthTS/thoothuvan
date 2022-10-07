package com.smsapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.QPTRequestModel;


@Repository
public interface QPTRequestDao extends JpaRepository<QPTRequestModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	QPTRequestModel saveAndFlush(QPTRequestModel entity);
	
}