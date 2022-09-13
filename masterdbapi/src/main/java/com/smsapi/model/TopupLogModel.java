package com.smsapi.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity(name = "topup_log")

public class TopupLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@OneToOne(cascade=CascadeType.MERGE)
	TopupHistoryModel topupmodel;
	
	@OneToOne(cascade=CascadeType.MERGE)
	CreditBalanceModel before;
	
	@OneToOne(cascade=CascadeType.MERGE)
	CreditBalanceModel after;
	
	private LocalDateTime createdtime;
	

	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();

	}


}
