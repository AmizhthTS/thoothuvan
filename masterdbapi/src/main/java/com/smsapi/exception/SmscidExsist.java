package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class SmscidExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmscidExsist(String msg) {
		super(msg);
	}

	
}
