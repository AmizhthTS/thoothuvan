package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class AdminNameMissMatched extends AuthenticationException{

	public AdminNameMissMatched(String msg) {
		super(msg);
	}

}
