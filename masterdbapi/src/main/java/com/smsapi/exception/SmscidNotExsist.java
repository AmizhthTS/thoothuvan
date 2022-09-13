package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class SmscidNotExsist extends AuthenticationException{

	public SmscidNotExsist(String msg) {
		super(msg);
	}

	
}
