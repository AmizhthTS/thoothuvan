package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class CarriernameExsist extends AuthenticationException{

	public CarriernameExsist(String msg) {
		super(msg);
	}

	
}
