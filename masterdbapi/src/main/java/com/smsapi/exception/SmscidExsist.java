package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class SmscidExsist extends AuthenticationException{

	public SmscidExsist(String msg) {
		super(msg);
	}

	
}
