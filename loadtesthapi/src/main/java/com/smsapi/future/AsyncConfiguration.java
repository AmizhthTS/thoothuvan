package com.smsapi.future;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.smsapi.future.model.ResultPoolModel;
import com.smsapi.future.model.TestStatus;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	
    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CarThread-");
        executor.initialize();
        return executor;
    }

    @Bean
    public ResultPoolModel getResultPool() {
    	
    	return new ResultPoolModel();
    }
    
    @Bean
    public TestStatus getTestStatus() {
    	
    	return new TestStatus();
    }
}
