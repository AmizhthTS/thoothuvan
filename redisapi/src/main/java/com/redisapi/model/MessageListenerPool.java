package com.redisapi.model;

import org.springframework.beans.factory.annotation.Autowired;



public class MessageListenerPool {

	
	
	public MessageListenerPool(String queuename,@Autowired RedisTemplatePool redistemplatepool,@Autowired MessagePublisherPool messagePublisherPool) {
		
		System.out.println("Start MessageListenerPool");

		redistemplatepool.getAvailableredislist().forEach((redisid)->{
			
							System.out.println("Start RedisMessageListener");
							new RedisMessageListener(messagePublisherPool,redistemplatepool.getPoolmap().get(redisid),queuename ).doProcess();
			
						});
	}
	
		
}
