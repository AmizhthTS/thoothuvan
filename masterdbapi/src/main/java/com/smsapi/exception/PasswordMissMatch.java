package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordMissMatch extends AuthenticationException{

	public PasswordMissMatch(String msg) {
		super(msg);
	}

	
}
