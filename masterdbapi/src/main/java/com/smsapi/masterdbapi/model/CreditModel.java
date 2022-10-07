package com.smsapi.masterdbapi.model;

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
@Entity(name = "credit")
public class CreditModel   {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double totalbalance;
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double daybalance;
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double monthbalance;
	
	@NotNull
	@ColumnDefault(value="0")
	private int limitenabled;
	
	
	private double daylimit;
	
	private double monthlylimit;
	
	@NotNull	
	private String username;

	@NotNull	
	private String admin;
	
	private LocalDateTime createdtime;
	
	private LocalDateTime modifiedtime;



	
	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();
		this.modifiedtime = LocalDateTime.now();
		double scale = Math.pow(10, 2);
		this.totalbalance = Math.round(this.totalbalance * scale) / scale;
		this.daybalance = Math.round(this.daybalance * scale) / scale;
		this.monthbalance = Math.round(this.monthbalance * scale) / scale;

	}

	@PreUpdate
	public void setModifiedDateTime() {
		this.modifiedtime = LocalDateTime.now();
		double scale = Math.pow(10, 2);
		this.totalbalance = Math.round(this.totalbalance * scale) / scale;
		this.daybalance = Math.round(this.daybalance * scale) / scale;
		this.monthbalance = Math.round(this.monthbalance * scale) / scale;

	}
	

	public String getUsername() {
		
		return this.username.toLowerCase();
	}
}
