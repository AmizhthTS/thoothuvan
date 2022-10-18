package com.smsapi.sc.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.RedisTemplatePool;
import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.sc.listener.MessageListener;
import com.smsapi.sc.listener.MessageListenerPool;
import com.smsapi.sc.model.Stats;
import com.smsapi.sc.model.TPS;
import com.smsapi.sc.service.SMSCModelService;


@Configuration
public class SCConfig {

	
	@Autowired SMSCModelService smscmodleservice;
		
	@Bean("SmppSessionConfiguration")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("SMSCModel")
	public SmppSessionConfiguration setSmppSessionConfiguration(@Autowired SMSCModel smscmodel) {
		System.out.println("SmppSessionConfiguration()");

		SmppSessionConfiguration config=new SmppSessionConfiguration(SmppBindType.TRANSCEIVER, smscmodel.getUsername(), smscmodel.getPassword());

		config.setHost(smscmodel.getIp());
		config.setPort(smscmodel.getPort());
		
		return config;
	}
	
	@Bean("Stats")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Stats getStats() {
		System.out.println("getStats()");

		return Stats.builder().build();
		
	}
	
	
	@Bean("tps")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("Stats")
	public TPS getTPS(@Autowired Stats stats) {
		System.out.println("getTPS()");

		return new TPS(stats);
	}
	
	
   	@Bean("SMSCModel")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("tokenmodel")
	public SMSCModel getSMSCModel()  {
		System.out.println("getSMSCModel()");

   		return smscmodleservice.getSMSCInfo();
   	}
   	
   	@Bean("messagelistenerpool")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn({"messagepublisherpool","SmppSessionConfiguration","tps"})
   	public MessageListenerPool startSMSCConnectorListener(@Autowired RedisTemplatePool redistemplatepool,@Autowired MessagePublisherPool messagepublisherpool) {
   		
   		System.out.println("startSMSCConnectorListener :");
   		
   		List<MessageListener> listenerlist=new ArrayList<MessageListener>();
   		
   		MessageListenerPool pool=MessageListenerPool.builder().messagelistener(listenerlist).build();
   		
   		redistemplatepool.getRedispoolinfomap().forEach((redisname,redisinfo)->{
   			
   	   		System.out.println("Add message Listener for Redis  :"+redisname);

   			listenerlist.add(new MessageListener(redisname,messagepublisherpool, redisinfo.getRedistemplate())) ;
   			
   		});
   		
   		return pool;
   	}
   	
   	
   	@Bean(name="smscconnectorExecutor")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Executor taskExecutor() {
      ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
      poolExecutor.setCorePoolSize(2);
      poolExecutor.setMaxPoolSize(Integer.parseInt(System.getenv("sessioncount")));
      poolExecutor.setThreadNamePrefix("smscconnectorExecutor");
      poolExecutor.initialize();
      return poolExecutor;
    }
  
   	
   	@Bean("sessionpool")
   	@

}
