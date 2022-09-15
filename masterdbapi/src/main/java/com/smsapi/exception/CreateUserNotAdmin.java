package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class CreateUserNotAdmin extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateUserNotAdmin(String msg) {
		super(msg);
	}

	
}
