package com.smsapi.future.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestModel implements Serializable {
	
	private static final long serialVersionUID = 4L;

	private String requestID;
	
	private String username;
	
	private String password;
	
	private LocalDateTime createdtime= LocalDateTime.now();

	
	
	@NotBlank(message= "fullmessage not blank")
	@Size(min = 1, max = 1000, message= "password must be between 1 and 1000 characters")
	private String fullmessage;
	
	@Digits(fraction = 0, integer = 10,message="mobile must be digit")
	@NotBlank(message= "mobile not blank")
	@Size(min = 10, max = 21, message= "mobile must be between 10 and 21 characters")
	private String mobile;
	
	@NotBlank(message= "senderid not blank")
	@Size(min = 4, max = 21, message= "senderid must be between 4 and 21 characters")
	private String senderid;
	
	@Size(min = 4, max = 21, message= "templateid must be between 4 and 21 characters")
	private String templateid;
	
	@Size(min = 4, max = 21, message= "entityid must be between 4 and 21 characters")
	private String entityid;
	
	
	
}
