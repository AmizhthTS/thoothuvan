package com.smsapi.model;

import lombok.Data;

@Data
public class ErrorField {

	private String field;
	
	private String message;
	
	private String value;
	
	public ErrorField(String field,String message,String value) {
		
		this.field=field;
		this.message=message;
		this.value=value;
	}
}
