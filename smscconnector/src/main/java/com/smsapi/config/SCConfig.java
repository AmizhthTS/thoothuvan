package com.smsapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.redisapi.model.MessageListenerPool;
import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.QueueName;
import com.redisapi.model.RedisTemplatePool;

@Service
@Configuration
@EnableScheduling
public class SCConfig {
	
		
	@Bean("messagelistenerpool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn({"messagepublisherpool","redistemplatepool"})
	public MessageListenerPool messagelistenerpool(@Autowired RedisTemplatePool redistemplatepool,@Autowired MessagePublisherPool messagePublisherPool) {

		System.out.println("messagelistenerpool Bean");

		return new MessageListenerPool(QueueName.ROUTER, redistemplatepool,messagePublisherPool);

	}
	
		
	
}
