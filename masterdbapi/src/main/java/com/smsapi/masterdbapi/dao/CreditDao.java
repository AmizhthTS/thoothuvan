package com.smsapi.masterdbapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.CreditModel;


@Repository
public interface CreditDao extends JpaRepository<CreditModel, Integer> {


	CreditModel findByUsernameEquals(String username );
	
	List<CreditModel>  findAll() ;
	
	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	CreditModel saveAndFlush(CreditModel entity);
	
	List<CreditModel> findByAdminEquals(List<String> username);
	
	CreditModel findByAdminEquals(String username);


}