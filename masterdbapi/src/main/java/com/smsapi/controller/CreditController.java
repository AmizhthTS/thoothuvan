package com.smsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.CreditModel;
import com.smsapi.model.TopupHistoryModel;
import com.smsapi.service.CreditService;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@PostMapping("/userlist")
	public ResponseEntity<?> createUser() {

		return ResponseEntity.ok().body(creditService.getUserList());
	
	}
	
	
	@PostMapping("/topuplog")
	public ResponseEntity<?> getTopupLog() {

		return ResponseEntity.ok().body(creditService.getTopuplog());
	
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<?> createUser(@RequestBody CreditModel createDTO) {

		CreditModel authOTPVerifyResponseModel = creditService.addUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/topup")
	public ResponseEntity<?> topup(@RequestBody TopupHistoryModel createDTO) {

		List<TopupHistoryModel> authOTPVerifyResponseModel = creditService.topupCredit(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/topuphistory")
	public ResponseEntity<?> topuphistory() {

		return ResponseEntity.ok().body(creditService.getTopuphistory());
	
	}
	
	
	
	
}