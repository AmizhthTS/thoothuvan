package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotExsist extends AuthenticationException{

	public UsernameNotExsist(String msg) {
		super(msg);
	}

	
}
