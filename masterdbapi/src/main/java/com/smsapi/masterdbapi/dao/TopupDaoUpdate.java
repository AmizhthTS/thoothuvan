package com.smsapi.masterdbapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.TopupHistoryModel;

@Repository 
public interface TopupDaoUpdate extends CrudRepository<TopupHistoryModel, Integer> {

}