package com.smsapi.validator.validation;

import org.springframework.http.HttpStatus;

import com.smsapi.validator.model.ErrorModel;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ErrorModel errormodel;
	
	HttpStatus httpstatus;
	
	public ValidationException(String message,ErrorModel errormodel,HttpStatus httpstatus) {
		
		super(message);
		
		this.errormodel=errormodel;
		
		this.httpstatus=httpstatus;
		
	}
}
