package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordMissMatch extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMissMatch(String msg) {
		super(msg);
	}

	
}
