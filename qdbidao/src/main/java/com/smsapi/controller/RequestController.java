package com.smsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.RequestModel;
import com.smsapi.service.QPTService;

@RestController
public class RequestController {
	
	private static String RESPONSE="OK";
	

	@Autowired QPTService qptservice;
	
	@PostMapping("/send")
	public String login(@RequestBody RequestModel requestmodel) {
		
	
		qptservice.psersist(requestmodel);
	
		return RESPONSE;
	}
	
	
	

	
	
	
}