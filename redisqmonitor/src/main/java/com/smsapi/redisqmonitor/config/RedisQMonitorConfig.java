package com.smsapi.redisqmonitor.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.redisapi.model.QueueModel;
import com.redisapi.model.QueuePoolModel;

public class RedisQMonitorConfig {
	
	
	@Bean("queuepoolmodelmaster")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public QueuePoolModel queuepoolmodel() {

		System.out.println("queuepoolmodel()");
		
		return QueuePoolModel.builder().queueinfomap(new HashMap<String,List<QueueModel>>()).build();

	}

}
