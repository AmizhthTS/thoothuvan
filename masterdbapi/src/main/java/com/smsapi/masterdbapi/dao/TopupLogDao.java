package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.TopupLogModel;


@Repository
public interface TopupLogDao extends JpaRepository<TopupLogModel, Integer> {

	
}