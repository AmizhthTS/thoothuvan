package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class AdminUserNotAvailable extends AuthenticationException{

	public AdminUserNotAvailable(String msg) {
		super(msg);
	}

	
}
