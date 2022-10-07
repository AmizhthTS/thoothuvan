package com.redisapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueuePoolModel {
	
	private Map<String/*queuename*/,List<QueueModel>> queueinfomap=new HashMap<String,List<QueueModel>>();
	
}
