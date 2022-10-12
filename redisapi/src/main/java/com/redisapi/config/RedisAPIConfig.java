package com.redisapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.RedisTemplatePool;


@Configuration
public class RedisAPIConfig {
	
		
	@Bean("retyrindicator")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public boolean indicator() {
		
		String module=System.getenv("module");
				
		if(module.equals("")) {
			
			return true;
		}else {
			 return false;
		}
	}
	
	
	@Bean("messagepublisherpool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn({"redistemplatepool","retyrindicator"})
	public MessagePublisherPool messagepublisherpool(@Autowired RedisTemplatePool redistemplatepool,@Autowired @Qualifier("retyrindicator") boolean retryindicator) {

		System.out.println("messagepublisherpool()");
		return new MessagePublisherPool(redistemplatepool,retryindicator);

	}
	
	
}
