package com.smsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smsapi.model.TokenModel;
import com.smsapi.model.UserCacheModel;
import com.smsapi.service.TokenService;
import com.smsapi.service.UserService;


@Service
public class LoginJob {


	@Autowired @Qualifier("tokenmodel") TokenModel tokenmodel;
	
	@Autowired TokenService tokenservice;
	

	
	@Scheduled(fixedDelay=900000)
	public void login() {

		tokenmodel.setToken(tokenservice.getToken());
	

	}
	
}
