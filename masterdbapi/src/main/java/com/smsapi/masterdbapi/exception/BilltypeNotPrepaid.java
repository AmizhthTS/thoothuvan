package com.smsapi.masterdbapi.exception;

import org.springframework.security.core.AuthenticationException;

public class BilltypeNotPrepaid extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BilltypeNotPrepaid(String msg) {
		super(msg);
	}

}
