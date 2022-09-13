package com.smsapi.future.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smsapi.future.model.ResultPoolModel;
import com.smsapi.future.model.TestStatus;


@Service
public class Task {


	@Autowired TestStatus teststatus;
	
	@Autowired ResultPoolModel resultpool;
	
	@Scheduled(fixedDelay=1000)
	public void check() {

		if(teststatus.isRunning()) {
			
			if(resultpool.getResultpool().get(teststatus.getTestid()).getCompletedthread()==resultpool.getResultpool().get(teststatus.getTestid()).getTotalthread()) {
				
				teststatus.setRunning(false);
			}
			
		}

	}
	
}
