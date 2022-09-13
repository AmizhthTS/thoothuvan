package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class BilltypeNotPrepaid extends AuthenticationException{

	public BilltypeNotPrepaid(String msg) {
		super(msg);
	}

}
