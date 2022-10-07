package com.smsapi.masterdbapi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRouteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	
	private int operatorroute;
	
	private int circleroute;
	
	private int nsnroute;
	
	private String transactionalgroupname;
	
	private String promotionalgroupname;
	
	private String otpgroupname;
	
	private String intlgroupname;
}
