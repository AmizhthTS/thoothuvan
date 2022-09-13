package com.smsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.OldPasswordModel;


@Repository
public interface OldPasswordDao extends JpaRepository<OldPasswordModel, Integer> {

	List<OldPasswordModel> findByUsernameEquals(String username);
	
	void deleteByUsername(String username);

}