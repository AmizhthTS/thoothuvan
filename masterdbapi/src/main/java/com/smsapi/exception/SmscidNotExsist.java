package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class SmscidNotExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmscidNotExsist(String msg) {
		super(msg);
	}

	
}
