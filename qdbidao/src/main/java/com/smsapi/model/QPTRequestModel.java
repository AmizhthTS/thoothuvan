package com.smsapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "qpt_request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QPTRequestModel {
	
	private static final long serialVersionUID = 4L;

	@Id
	private String requestid;
	
	private String username;
	
	private Date rdate;
	
	@Lob
	private byte[] data;
	
	private byte status;
	
}
