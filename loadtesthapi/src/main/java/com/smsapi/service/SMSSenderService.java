package com.smsapi.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smsapi.model.ResultModel;
import com.smsapi.model.ResultPoolModel;
import com.smsapi.model.TestRequestM;
import com.smsapi.model.TestStatus;

@Service
public class SMSSenderService {
	
	@Autowired ResultPoolModel resultpool;
	
	@Autowired TestStatus teststatus;
	
	public boolean startTest(TestRequestM testrequest) {
		
		if(teststatus.isRunning()) {
			
			return false;

		}else {
	
		String testid=UUID.randomUUID().toString();
		
		resultpool.getResultpool().put(testid, new ResultModel(testid,testrequest.getThreadcount()));
		
		teststatus.setRunning(true);
		
		teststatus.setTestid(testid);
		
		for(int i=0;i<testrequest.getThreadcount();i++) {
			
			System.out.println("getThreadcount : "+(i+1));
			CompletableFuture<Void> completablefuture= CompletableFuture
					.runAsync(new SMSSender(resultpool,i,testrequest.getMessagecount(), testid));
			
			completablefuture.handle((msg, ex) -> {
														if (ex != null) {
															ex.printStackTrace();
															return "Recovered from \"" + ex.getMessage() + "\"";
															} else {
																return msg;
															}
															});

		}
		
			return true;
		}
		
	}

}
