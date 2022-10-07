package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupnameNotExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupnameNotExsist(String msg) {
		super(msg);
	}

	
}
