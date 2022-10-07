package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameNotExsist(String msg) {
		super(msg);
	}

	
}
