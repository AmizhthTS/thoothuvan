package com.smsapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;


@Data
@Entity(name = "credit_balance")
public class CreditBalanceModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double balance;
	
	@NotNull	
	private String type;

	private LocalDateTime createdtime;
	

	
	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();
	}

}
