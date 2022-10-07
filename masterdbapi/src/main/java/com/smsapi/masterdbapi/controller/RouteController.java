package com.smsapi.masterdbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.masterdbapi.model.ChangePasswordModel;
import com.smsapi.masterdbapi.model.UserCacheModel;
import com.smsapi.masterdbapi.model.UserModel;
import com.smsapi.masterdbapi.service.MasterUserService;

@RestController
@RequestMapping(value = "/user")
public class RouteController {

	@Autowired private MasterUserService authService;

	@Autowired @Qualifier("usercachemaster") private UserCacheModel usercacheref; 
	
	@PostMapping("/changepassword")
	public ResponseEntity<?> changepassword(@RequestBody ChangePasswordModel authDTO) {

		UserModel authOTPVerifyResponseModel = authService.passwordchange(authDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserModel createDTO) {

		UserModel authOTPVerifyResponseModel = authService.createUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<?> editUser(@RequestBody UserModel createDTO) {

		UserModel authOTPVerifyResponseModel = authService.editUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/get")
	public ResponseEntity<?> getUser(@RequestBody UserModel createDTO) {

		UserModel authOTPVerifyResponseModel = authService.getUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	
	@PostMapping("/getusers")
	public ResponseEntity<?> getUser() {

		return ResponseEntity.ok().body(usercacheref);
	
	}
	
	
	@PostMapping("/getusersmemoryid")
	public String getUsermemoryid() {

		return usercacheref.getMemoryid();
	
	}
	
	
	
	
	
	@PostMapping("/list")
	public ResponseEntity<?> listUser(@RequestBody UserModel createDTO) {

		List<UserModel> authOTPVerifyResponseModel = authService.listUser(createDTO);
		
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
}