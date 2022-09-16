package com.smsapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.redisapi.model.RedisPropertiesList;
import com.redisapi.model.RedisTemplatePool;
import com.smsapi.model.TokenModel;
import com.smsapi.model.UserCacheModel;
import com.smsapi.service.TokenService;
import com.smsapi.service.UserService;

@Configuration
public class WebConfig {
	
	@Autowired TokenService tokenservice;
	
	@Autowired UserService userservice;
	
	@Bean("usercache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("tokenmodel")
	public UserCacheModel usercacheModel() {

		UserCacheModel ucm=userservice.getUsers();
		
		return UserCacheModel.builder().memoryid(ucm.getMemoryid()).usercache(ucm.getUsercache()).build();
	
	}
	
	
	
	@Bean("redistemplatepool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("tokenmodel")
	public RedisTemplatePool redistemplatepool() {

		try {
		RedisPropertiesList ucm=userservice.getRedisProperties();
		
		if(ucm==null) {
			
			System.err.println("Unable to Get the  Redis Properties from Master DB");
			System.exit(0);
		}
		
		return new RedisTemplatePool(false,ucm);
		
	
		}catch(Exception e) {
			
			System.err.println("Unable to Get the  Redis Properties from Master DB");
			System.exit(0);
		}
		
		return null;
	}

	
	@Bean("tokenmodel")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public TokenModel setTokenModel() {
		
		
		return TokenModel.builder().token(tokenservice.getToken()).build();
	}
	
	
}
