package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameExsist extends AuthenticationException{

	public UsernameExsist(String msg) {
		super(msg);
	}

	
}
