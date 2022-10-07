package com.smsapi.masterdbapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.SMSCModel;

@Repository 
public interface SMSCDaoUpdate extends CrudRepository<SMSCModel, Integer> {

}