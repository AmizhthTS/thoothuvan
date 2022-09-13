package com.redisapi.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisPropertiesList {

	List<RedisPropertiesListModel> redislist;
}
