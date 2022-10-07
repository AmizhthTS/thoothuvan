package com.smsapi.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel implements Serializable {
	
	private static final long serialVersionUID = 4L;

	private String requestID;
	

	private String username;
	
	private String password;
	
	private Date rdate=new Date();
	
	
	private String fullmessage;
	
	private String mobile;
	
	private String senderid;
	
	private String templateid;
	
	private String entityid;
	
	private String statuscode;
	
	public String toString() {
		
		Gson gson=new Gson();
		
		return gson.toJson(this, RequestModel.class);
	}

	
	
}
