package com.smsapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@PostMapping("/hello")
	public String hello() {
		return "Hello World";
	}

}
