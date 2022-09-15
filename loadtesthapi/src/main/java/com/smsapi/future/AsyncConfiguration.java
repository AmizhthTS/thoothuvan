package com.smsapi.future;

import java.util.HashMap;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.smsapi.future.model.ResultModel;
import com.smsapi.future.model.ResultPoolModel;
import com.smsapi.future.model.TestStatus;

@EnableAsync
@Configuration
public class AsyncConfiguration {
	
    @Bean (name = "taskExecutor")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.afterPropertiesSet();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
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
