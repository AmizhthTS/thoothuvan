package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameExsist(String msg) {
		super(msg);
	}

	
}
