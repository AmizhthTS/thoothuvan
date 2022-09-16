package com.smsapi.config;

import java.util.HashMap;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.smsapi.model.ResultModel;
import com.smsapi.model.ResultPoolModel;
import com.smsapi.model.TestStatus;

@EnableAsync
@Configuration
public class AsyncConfiguration {
	
    @Bean (name = "taskExecutor")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.afterPropertiesSet();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("SMSThread-");
        executor.initialize();
        return executor;
    }
	

    @Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ResultPoolModel getResultPool() {
    	
    	return ResultPoolModel.builder().resultpool(new HashMap<String,ResultModel>()).build();
    }
    
    @Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TestStatus getTestStatus() {
    	
    	return new TestStatus();
    }
}
