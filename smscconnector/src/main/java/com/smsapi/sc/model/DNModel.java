package com.smsapi.sc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DNModel {

	private String smscid;
	
	private String dn;
	
}
