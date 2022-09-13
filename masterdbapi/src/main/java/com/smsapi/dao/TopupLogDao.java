package com.smsapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.TopupLogModel;


@Repository
public interface TopupLogDao extends JpaRepository<TopupLogModel, Integer> {

	
}