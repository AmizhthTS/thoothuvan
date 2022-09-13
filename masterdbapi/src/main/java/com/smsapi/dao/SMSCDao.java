package com.smsapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.SMSCModel;


@Repository
public interface SMSCDao extends JpaRepository<SMSCModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	SMSCModel saveAndFlush(SMSCModel entity);
	
	SMSCModel findBySmscidEquals(String smscid);
	

}