package com.smsapi.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.smsapi.exception.AdminNameMissMatched;
import com.smsapi.exception.AdminUserNotAvailable;
import com.smsapi.exception.BilltypeNotPrepaid;
import com.smsapi.exception.CreateUserNotAdmin;
import com.smsapi.exception.PasswordMissMatch;
import com.smsapi.exception.UserNotRegisteredForCredit;
import com.smsapi.exception.UsernameExsist;
import com.smsapi.exception.UsernameNotExsist;
import com.smsapi.exception.WrongPassword;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		if(authException instanceof UsernameNotFoundException) {
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credential");

		}else if(authException instanceof BadCredentialsException){
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credential");

		}else if(authException instanceof DisabledException){
			
			response.sendError(HttpServletResponse.SC_MOVED_TEMPORARILY, "Account Disabled");

		}else if(authException instanceof PasswordMissMatch){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "PasswordMissMatch");

		}else if(authException instanceof WrongPassword){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "WrongPassword");

		}else if(authException instanceof UsernameExsist){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "UsernameExsists");

		}else if(authException instanceof CreateUserNotAdmin){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "CreateUserNotAdmin");

		}else if(authException instanceof AdminUserNotAvailable){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "AdminUserNotAvailable");

		}else if(authException instanceof UsernameNotExsist){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "UsernameNotExsist");

		}else if(authException instanceof BilltypeNotPrepaid){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "BilltypeNotPrepaid");

		}else if(authException instanceof AdminNameMissMatched){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "AdminNameMissMatched");

		}else if(authException instanceof UserNotRegisteredForCredit){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "UserNotRegisteredForCredit");

		}else {
			
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	
		}
	}
}
