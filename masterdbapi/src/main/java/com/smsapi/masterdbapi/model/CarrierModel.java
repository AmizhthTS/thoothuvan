package com.smsapi.masterdbapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "carrier")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarrierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String carriername;
	
	private String telemarketerid;
	
}
