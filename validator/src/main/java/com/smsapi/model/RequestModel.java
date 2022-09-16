package com.smsapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import com.smsapi.util.MessageStatus;
import com.smsapi.util.StatusCode;
import com.smsapi.validation.Activation;
import com.smsapi.validation.Balance;
import com.smsapi.validation.Credential;
import com.smsapi.validation.DependentParams;
import com.smsapi.validation.RequestID;

import lombok.Builder;
import lombok.Data;

@Data
@Credential
@RequestID
@DependentParams
@Builder
public class RequestModel implements Serializable {
	
	private static final long serialVersionUID = 4L;

	private String requestID;
	
	@Activation
	@Balance
	private String username;
	
	private String password;
	
	
	@NotBlank(message= "fullmessage not blank")
	@Size(min = 1, max = 1000, message= "password must be between 1 and 1000 characters")
	private String fullmessage;
	
	@Digits(fraction = 0, integer = 10,message="mobile must be digit")
	@NotBlank(message= "mobile not blank")
	@Size(min = 10, max = 21, message= "mobile must be between 10 and 21 characters")
	private String mobile;
	
	@NotBlank(message= "senderid not blank")
	@Size(min = 4, max = 21, message= "senderid must be between 4 and 21 characters")
	private String senderid;
	
	@Size(min = 4, max = 21, message= "templateid must be between 4 and 21 characters")
	private String templateid;
	
	@Size(min = 4, max = 21, message= "entityid must be between 4 and 21 characters")
	private String entityid;
	
	private String statuscode=StatusCode.QUEUING_ERROR;
	
	public String toString() {
		
		Gson gson=new Gson();
		
		return gson.toJson(this, RequestModel.class);
	}

	@SuppressWarnings("deprecation")
	public String getResponse() {
		
		Gson gson=new Gson();
		
		return gson.toJson(ResponseModel.builder().requestid(this.requestID).statuscode(this.statuscode).statusdescription(MessageStatus.getInstance().getStatusDescription(this.statuscode)).time(new Date().toLocaleString()).build(), ResponseModel.class);
	}
	
	
	public String getJSON() {
		
    	Gson gson =new Gson();

    	return gson.toJson(this, RequestModel.class);
	}
	
}
