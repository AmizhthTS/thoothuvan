package com.smsapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel {
	
	private String requestid;
	
	private String statuscode;
	
	private String statusdescription;
	
	private String time;
	

}
