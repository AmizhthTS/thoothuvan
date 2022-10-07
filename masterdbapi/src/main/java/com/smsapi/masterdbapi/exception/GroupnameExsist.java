package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupnameExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupnameExsist(String msg) {
		super(msg);
	}

	
}
