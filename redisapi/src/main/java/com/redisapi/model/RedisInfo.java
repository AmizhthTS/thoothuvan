package com.redisapi.model;

import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisInfo {
	
	private String redisid;
	
	@JsonIgnore
	private RedisTemplate<String, Object> redistemplate;

}
