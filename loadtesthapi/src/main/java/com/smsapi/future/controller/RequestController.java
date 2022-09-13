package com.smsapi.future.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.future.model.ResultPoolModel;
import com.smsapi.future.model.TestRequestM;
import com.smsapi.future.model.TestStatus;
import com.smsapi.future.service.SMSSenderService;

@RestController
public class RequestController {
	
	@Autowired SMSSenderService smssenderservice;

	@Autowired TestStatus teststatus;
	
	@Autowired ResultPoolModel resultpool;
	
	@PostMapping("/starttest")
	public String starttest(@RequestBody TestRequestM requestmodel) {
		
		if(smssenderservice.startTest(requestmodel)) {
			
			return "STARTED";
		}else {
			
			return "IGNORED PREVIOUS TEST IS RUNNING";
		}
	
	}
	
	
	@PostMapping("/teststatus")
	public boolean getmemory() {

		return teststatus.isRunning();
	
	}

	
	@PostMapping("/testresult")
	public ResponseEntity<?> gettestresult() {

		return ResponseEntity.ok().body(resultpool);

	
	}
	
	
}