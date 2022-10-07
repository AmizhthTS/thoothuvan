package com.smsapi.sc.model;

import java.util.List;

import lombok.Data;

@Data
public class SMSRequest {

	private String username;
	
	private String password;
	
	private byte datacoding;
	
	private byte esmclass;
	
	private String entityid;
	
	private String templateid;
	
	private String mobilenumber;
	
	private String senderid;
	
	private List<SMSModel> smslist;
	
}
