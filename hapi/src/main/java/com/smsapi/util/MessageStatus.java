package com.smsapi.util;

import java.util.HashMap;
import java.util.Map;

public class MessageStatus {

	private static  MessageStatus obj=new MessageStatus();
	
	private static Map<String,String> STATUSMAP=new HashMap<String,String>();
	
	static {
		
		STATUSMAP.put("200", "Request Accepted for Delivery");

		STATUSMAP.put("101", "Invalid Credential");
		
		STATUSMAP.put("102", "Account Deactivated");
		
		STATUSMAP.put("103", "invalid Mobile Number");
		
		STATUSMAP.put("104", "invalid Message Content");
		
		STATUSMAP.put("105", "invalid Senderid");
		
		STATUSMAP.put("701", "Send to Queue Error");


		STATUSMAP.put("201", "User has not enough Balance for Send the Message");

		STATUSMAP.put("202", "Admin has not enough Balance for Send the Message");

		STATUSMAP.put("203", "Customer has not enough Balance for Send the Message");

	}
	private MessageStatus() {
		
	}
	
	public static MessageStatus getInstance() {
		
		return obj;
		
	}
	
	
	public String getStatusDescription(String statuscode) {
		
		return STATUSMAP.get(statuscode);
		
	}
}
