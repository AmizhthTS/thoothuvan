package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class RouteNotExsist extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RouteNotExsist(String msg) {
		super(msg);
	}

	
}
