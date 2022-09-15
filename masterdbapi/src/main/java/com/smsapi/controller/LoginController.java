package com.smsapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.view.Login;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@GetMapping("/get")
	public String login() {
		
		 HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.TEXT_HTML);
		//return ResponseEntity.ok().headers(headers).body(Login.getContent(null, null));
		    return Login.getContent(null, null);
			
	
	}
	

	
	
	
}