package com.smsapi.masterdbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.masterdbapi.service.SMSCService;

@RestController
@RequestMapping(value = "/smsc")
public class SMSCController {

	@Autowired
	private SMSCService authService;

	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody SMSCModel createDTO) {

		SMSCModel authOTPVerifyResponseModel = authService.createSmsc(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<?> editUser(@RequestBody SMSCModel createDTO) {

		SMSCModel authOTPVerifyResponseModel = authService.editSmsc(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/get")
	public ResponseEntity<?> getUser(@RequestBody SMSCModel createDTO) {

		SMSCModel authOTPVerifyResponseModel = authService.getSMSC(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);

	}
	
	
	@PostMapping("/list")
	public ResponseEntity<?> listSMSC() {

		List<SMSCModel> authOTPVerifyResponseModel = authService.listSMSC();
		
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
}