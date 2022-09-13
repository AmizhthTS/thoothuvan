package com.smsapi.model;

import lombok.Data;

@Data
public class SplitMessageModel {

	private String requestid;
	
	private String messageid;
	
	private String sms;
	
	private String udh;
	
	private int totalsmscount;
	
	private int smssequence;
	
}
