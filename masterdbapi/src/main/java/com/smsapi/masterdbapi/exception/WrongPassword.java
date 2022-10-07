package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class WrongPassword extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongPassword(String msg) {
		super(msg);
	}

}
