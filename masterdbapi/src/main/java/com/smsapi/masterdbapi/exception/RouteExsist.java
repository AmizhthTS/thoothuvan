package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class RouteExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RouteExsist(String msg) {
		super(msg);
	}

	
}
