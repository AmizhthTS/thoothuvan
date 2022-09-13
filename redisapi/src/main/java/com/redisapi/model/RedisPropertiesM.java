package com.redisapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisPropertiesM {
	
	private String name;

	
	private String hostname;
	
	
	private int port;
	
	private int db;
}
