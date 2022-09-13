package com.smsapi.future.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smsapi.future.model.ResultModel;
import com.smsapi.future.model.ResultPoolModel;
import com.smsapi.future.model.TestRequestM;
import com.smsapi.future.model.TestStatus;
import com.smsapi.future.util.SMSSender;

@Service
public class SMSSenderService {
	
	@Autowired ResultPoolModel resultpool;
	
	@Autowired TestStatus teststatus;
	
	public boolean startTest(TestRequestM testrequest) {
		
		if(teststatus.isRunning()) {
			return false;

		}else {
	
		String testid=UUID.randomUUID().toString();
		
		resultpool.getResultpool().put(testid, new ResultModel(testrequest.getThreadcount()));
		
		for(int i=0;i<testrequest.getThreadcount();i++) {
			
			CompletableFuture.runAsync(new SMSSender(testrequest.getMessagecount(), testid));

		}
		
			return true;
		}
		
	}

}
