package com.smsapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder	
public class UserModel {

	private String username;
	
	private String password;
	
	private int status;
	
	private int balanceavailable;
	
	public String getUsername() {
		
		return username.toLowerCase();
	}
	
}
