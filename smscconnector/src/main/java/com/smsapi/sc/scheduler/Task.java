package com.smsapi.sc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.smsapi.sc.model.TPS;
import com.smsapi.sc.service.SessionService;

public class Task {
	
	@Autowired
	SessionService sessionservice;
	
	@Autowired
	TPS tps;

	@Scheduled(fixedDelay=20)
	public void doProcess() {
		
		if(!sessionservice.isBound()) {
			
			sessionservice.bind();
		}
		
		sessionservice.sendEnquireLink();
		
		
	}
	
	
	@Scheduled(fixedDelay=1000)
	public void cleaner() {
		
		tps.clearCount();
	}
}
