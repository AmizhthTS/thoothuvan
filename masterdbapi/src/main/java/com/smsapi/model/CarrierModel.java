package com.smsapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
