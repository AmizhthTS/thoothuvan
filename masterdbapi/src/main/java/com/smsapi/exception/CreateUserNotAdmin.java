package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class CreateUserNotAdmin extends AuthenticationException{

	public CreateUserNotAdmin(String msg) {
		super(msg);
	}

	
}
