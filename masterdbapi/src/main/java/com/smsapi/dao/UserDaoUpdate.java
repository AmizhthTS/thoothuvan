package com.smsapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.UserModel;

@Repository 
public interface UserDaoUpdate extends CrudRepository<UserModel, Integer> {

}