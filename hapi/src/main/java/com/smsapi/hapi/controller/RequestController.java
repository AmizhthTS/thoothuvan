package com.smsapi.hapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.hapi.service.RedisService;
import com.smsapi.validator.model.RequestModel;
import com.smsapi.validator.util.StatusCode;

@RestController
public class RequestController {
	
	@Autowired RedisService redisservice;
	
	@PostMapping("/send")
	public ResponseEntity<?> login(@Valid @RequestBody RequestModel requestmodel) {
		
		if(redisservice.addQueue(requestmodel)) {
			
			requestmodel.setStatuscode(StatusCode.ACCEPT);
		}
	
		return ResponseEntity.ok().body(requestmodel.getResponse());
	}
	
	
	

	
	
	
}