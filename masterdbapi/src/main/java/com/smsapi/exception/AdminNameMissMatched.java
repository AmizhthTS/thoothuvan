package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class AdminNameMissMatched extends AuthenticationException{

	public AdminNameMissMatched(String msg) {
		super(msg);
	}

}
