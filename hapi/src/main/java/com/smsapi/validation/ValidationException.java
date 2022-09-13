package com.smsapi.validation;

import org.springframework.http.HttpStatus;

import com.smsapi.model.ErrorModel;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException{

	ErrorModel errormodel;
	
	HttpStatus httpstatus;
	
	public ValidationException(String message,ErrorModel errormodel,HttpStatus httpstatus) {
		
		super(message);
		
		this.errormodel=errormodel;
		
		this.httpstatus=httpstatus;
		
	}
}
