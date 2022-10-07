package com.smsapi.sc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSCModel {
	
	private String smscid;

	private String username;
	
	private String password;
	
	private String ip;
	
	private int port;
	
	private String telemarketerid;
}
