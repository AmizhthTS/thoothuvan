package com.smsapi.user.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smsapi.user.model.TokenModel;
import com.smsapi.user.service.TokenService;


@Service
public class LoginJob {


	@Autowired @Qualifier("tokenmodel") TokenModel tokenmodel;
	
	@Autowired TokenService tokenservice;
	

	
	@Scheduled(fixedDelay=900000)
	public void login() {

		tokenmodel.setToken(tokenservice.getToken());
	

	}
	
}
