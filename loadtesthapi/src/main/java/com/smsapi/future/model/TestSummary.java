package com.smsapi.future.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSummary {

	private Date date;
	
	private long acceptancetps;
	
}
