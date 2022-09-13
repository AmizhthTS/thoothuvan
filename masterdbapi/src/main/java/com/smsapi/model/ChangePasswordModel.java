package com.smsapi.model;

import lombok.Data;


@Data
public class ChangePasswordModel {

	private String username;

	private String password;
	
	private String changepassword;
	
	private String confirmpassword;
	
	public String getUsername() {
		
		return this.username.toLowerCase();
	}
	
}
