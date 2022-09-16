package com.smsapi.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.redisapi.model.MessagePublisherPool;


@Configuration
@EnableScheduling
public class HAPIConfig {
	
		
	@Bean("messagepublisherpool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("redistemplatepool")
	public MessagePublisherPool messagepublisherpool() {

		return new MessagePublisherPool();

	}
	
	
}
