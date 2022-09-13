package com.smsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.UserModel;


@Repository
public interface UserDao extends JpaRepository<UserModel, Integer> {


	UserModel findByUsernameEquals(String username );
	
	List<UserModel> findByRoleidIn(List<Integer> roleid);
	
	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	UserModel saveAndFlush(UserModel entity);
	
	List<UserModel> findByAdminEquals(String admin);
	

}