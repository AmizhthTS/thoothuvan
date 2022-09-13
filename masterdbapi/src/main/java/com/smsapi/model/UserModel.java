package com.smsapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int status=1;
	
	@NotNull
	@ColumnDefault(value="1")
	private int passwordactivation=1;
	
	@NotNull
	@ColumnDefault(value="0.00")
	private double profitpercentage;
	
	
	@NotNull
	@ColumnDefault(value="'prepaid'")
	private String billtype="postpaid";
	
	
	@NotNull
	private int roleid;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int entityid;
	
	@NotNull	
	private String username;

	@NotNull
	private String password;
	
	@NotNull
	private String admin;
	
	private LocalDateTime createdtime;
	
	private LocalDateTime modifiedtime;
	
	private int balanceavailable=1;


	
	@Transient
	private String token;
	
	/*
	public UserModel() {
		
		super(System.getProperty("smsapi.username"),System.getProperty("smsapi.password"),);
	}
	public UserModel(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username,password,authorities);
	}

*/
	public User getUser() {
		
		User user=new User(username,password,new ArrayList<GrantedAuthority>());
		
		return user;
	}
	
	@PrePersist
	public void setCreationDateTime() {
		this.createdtime = LocalDateTime.now();
		this.modifiedtime = LocalDateTime.now();

		 double scale = Math.pow(10, 2);
		 this.profitpercentage = Math.round(this.profitpercentage * scale) / scale;
	

	}

	@PreUpdate
	public void setModifiedDateTime() {
		this.modifiedtime = LocalDateTime.now();
		double scale = Math.pow(10, 2);
		this.profitpercentage = Math.round(this.profitpercentage * scale) / scale;
	}
	

	public OldPasswordModel getOldPasswordModel() {
		
		OldPasswordModel bean=new OldPasswordModel();
		
		bean.setUsername(getUsername());
		bean.setPassword(getPassword());
		
		return bean;
	}
	public String getUsername() {
		
		return this.username.toLowerCase();
	}
}
