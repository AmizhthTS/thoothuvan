package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class AdminUserNotAvailable extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminUserNotAvailable(String msg) {
		super(msg);
	}

	
}
