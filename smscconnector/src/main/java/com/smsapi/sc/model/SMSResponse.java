package com.smsapi.sc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSResponse {

	private String requestid;
	
	private String error;
	
	private String messageid;
	
	private int status;
	
}
