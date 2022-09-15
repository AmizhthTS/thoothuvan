package com.smsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.CarrierModel;
import com.smsapi.service.CarrierService;

@RestController
@RequestMapping(value = "/carrier")
public class CarrierController {

	@Autowired
	private CarrierService authService;


		
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody CarrierModel createDTO) {

		CarrierModel authOTPVerifyResponseModel = authService.createCarrier(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<?> editUser(@RequestBody CarrierModel createDTO) {

		CarrierModel authOTPVerifyResponseModel = authService.editUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/get")
	public ResponseEntity<?> getCarrier(@RequestBody CarrierModel createDTO) {

		CarrierModel authOTPVerifyResponseModel = authService.getCarrier(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/list")
	public ResponseEntity<?> listCarrier() {

		List<CarrierModel> authOTPVerifyResponseModel = authService.listCarrier();
		
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
}