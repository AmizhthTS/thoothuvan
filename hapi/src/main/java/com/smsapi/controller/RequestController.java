package com.smsapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redisapi.model.QueueName;
import com.redisapi.model.RedisTemplatePool;
import com.smsapi.model.RequestModel;
import com.smsapi.service.RedisService;
import com.smsapi.util.StatusCode;

@RestController
public class RequestController {
	
	@Autowired RedisTemplatePool usercache;

	@Autowired RedisService redisservice;
	
	@PostMapping("/send")
	public ResponseEntity<?> login(@Valid @RequestBody RequestModel requestmodel) {
		
		if(redisservice.addQueue(requestmodel)) {
			
			requestmodel.setStatuscode(StatusCode.ACCEPT);
		}
	
		return ResponseEntity.ok().body(requestmodel.getResponse());
	}
	
	
	@PostMapping("/getmemory")
	public ResponseEntity<?> getmemory() {
		
		List<String> queuenamelist=new ArrayList<String>();
		
		queuenamelist.add(QueueName.ROUTER);
		
		return ResponseEntity.ok().body(usercache.getQueueCountMap(queuenamelist));
	}

	
	
	
}