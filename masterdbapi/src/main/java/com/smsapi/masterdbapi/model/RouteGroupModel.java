package com.smsapi.masterdbapi.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "routegroup")
public class RouteGroupModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupid;

	private String groupname;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<SMSCName> smscidlist;
	
	private int otp;
	
	private int intl;
	
	private int transactional;
	
	private int promotional;
}
