package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class RedisExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedisExsist(String msg) {
		super(msg);
	}

	
}
