package com.smsapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.TopupHistoryModel;

@Repository 
public interface TopupDaoUpdate extends CrudRepository<TopupHistoryModel, Integer> {

}