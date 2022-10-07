package com.smsapi.masterdbapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "smsc")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMSCModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String carriername;
	
	@Transient
	private String telemarketerid;
	
	private String smscid;

	private String username;
	
	private String password;
	
	private String ip;
	
	private int port;
	
	private int otp;
	
	private int intl;
	
	private int transactional;
	
	private int promotional;
	
	private double cost;
	
}
