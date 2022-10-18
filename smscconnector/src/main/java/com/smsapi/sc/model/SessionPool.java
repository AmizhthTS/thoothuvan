package com.smsapi.sc.model;

import java.util.Map;

import lombok.Data;

@Data
public class SessionPool {

	private Map<String,SMSCSessionInfo> sessonpool;
}
