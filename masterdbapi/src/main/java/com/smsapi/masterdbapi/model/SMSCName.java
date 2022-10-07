package com.smsapi.masterdbapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder	
@Entity(name = "smsclist")
public class SMSCName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupid;
	
	private String smscid;
}
