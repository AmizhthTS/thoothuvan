package com.redisapi.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public class MessageListener {

	String redisname;
	
	RedisTemplate<String, Object> redisTemplate;
	
	MessagePublisherPool messagePublisherPool;
	
	RedisMessageListener listener;
	
	ScheduledExecutorService executorService;
	
	public MessageListener(final String redisname,final MessagePublisherPool messagePublisherPool,final RedisTemplate<String, Object> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	        this.messagePublisherPool=messagePublisherPool;
			this.listener=new RedisMessageListener(messagePublisherPool,redisTemplate);
			this.executorService=Executors.newSingleThreadScheduledExecutor();
			this.redisname=redisname;
			executorService.scheduleAtFixedRate(listener::doProcess, 0, 50, TimeUnit.MILLISECONDS);
	}
	
	

	}

