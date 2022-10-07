package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class CarriernameExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarriernameExsist(String msg) {
		super(msg);
	}

	
}
