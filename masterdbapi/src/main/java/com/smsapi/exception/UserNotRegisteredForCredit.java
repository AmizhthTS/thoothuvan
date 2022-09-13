package com.smsapi.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotRegisteredForCredit extends AuthenticationException{

	public UserNotRegisteredForCredit(String msg) {
		super(msg);
	}

}
