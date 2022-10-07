package com.smsapi.sc.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stats {
	
	private String smscid;
	
	private String sessionid=UUID.randomUUID().toString();
	
	private boolean bound;
	
	private String bounderror;
	
	private String enquirelinkerror;

	private long lastupdatetime;
	
	private long lastsmstime;
	
	private long lastdntime;

	private long lastenquirelinktime;

	private long SMS=0;
	
	private long SMS_FAILED=0;
	
	private long SMS_RETRY=0;

	private long DN=0;
	
	private long ENQUIRELINK=0;
	
	private long tps=0;
	
	private long counter=0;

	private long dntps=0;
	
	private long dncounter=0;
	
	private long dnpostsuccess=0;
	
	private long dnpostfailure=0;

}
