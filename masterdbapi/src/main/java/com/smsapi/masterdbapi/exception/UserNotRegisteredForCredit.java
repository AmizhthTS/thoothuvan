package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotRegisteredForCredit extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotRegisteredForCredit(String msg) {
		super(msg);
	}

}
