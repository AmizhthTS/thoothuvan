package com.smsapi.sc.model;

import com.cloudhopper.smpp.SmppSession;

import lombok.Data;

@Data
public class SMSCSessionInfo {

	SmppSession session;
	
	long lastSMSUpdate;
}
