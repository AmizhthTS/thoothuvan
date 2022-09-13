package com.smsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.dao.RedisQueuePropertiesDao;
import com.smsapi.model.RedisPropertiesModel;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {


	@Autowired private RedisQueuePropertiesDao redisqueueproperties; 
	
	
	
	@PostMapping("/create/queueredis")
	public ResponseEntity<?> createUser(@RequestBody RedisPropertiesModel createDTO) {

		RedisPropertiesModel authOTPVerifyResponseModel = redisqueueproperties.saveAndFlush(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/getall/queueredisproperties")
	public ResponseEntity<?> getUser() {

		return ResponseEntity.ok().body(RedisPropertiesModel.getData(redisqueueproperties.findAll()));
	
	}
	
	
}