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
@Entity(name = "topup_history")
public class TopupHistoryModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double topupvalue;
	
	
	@NotNull
	@ColumnDefault(value="'INITIATE'")
	private String status="INITIATE";
	
	
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


	}

	@PreUpdate
	public void setModifiedDateTime() {
		this.modifiedtime = LocalDateTime.now();
	}
	

	public String getUsername() {
		
		return this.username.toLowerCase();
	}
}
