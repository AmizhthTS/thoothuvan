package com.smsapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.SMSCModel;

@Repository 
public interface SMSCDaoUpdate extends CrudRepository<SMSCModel, Integer> {

}