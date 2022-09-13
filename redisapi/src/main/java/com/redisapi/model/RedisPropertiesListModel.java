package com.redisapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisPropertiesListModel {

	private String name;
	
	private RedisPropertiesM prop;
	
}
