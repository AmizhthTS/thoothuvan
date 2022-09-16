package com.redisapi.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public class MessageListner {

	RedisTemplate<String, Object> redisTemplate;
	
	MessagePublisherPool messagePublisherPool;
	
	String queuename;

	public MessageListner(final MessagePublisherPool messagePublisherPool,final RedisTemplate<String, Object> redisTemplate, final String queuename) {
	        this.redisTemplate = redisTemplate;
	        this.queuename = queuename;
	        this.messagePublisherPool=messagePublisherPool;
	}
	
	
	public void doProcess() {
	
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.scheduleAtFixedRate(new RedisMessageListener(messagePublisherPool,redisTemplate,queuename)::doProcess, 0, 50, TimeUnit.MILLISECONDS);
	}

	}

