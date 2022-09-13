package com.smsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.TopupHistoryModel;


@Repository
public interface TopupDao extends JpaRepository<TopupHistoryModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	TopupHistoryModel saveAndFlush(TopupHistoryModel entity);
	
	List<TopupHistoryModel> findByAdminEquals(String admin);
	
	List<TopupHistoryModel> findByUsernameEquals(String username);

	List<TopupHistoryModel> findByStatusEquals(String status);

}