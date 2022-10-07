package com.smsapi.masterdbapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.UserModel;

@Repository 
public interface CarrierDaoUpdate extends CrudRepository<UserModel, Integer> {

}