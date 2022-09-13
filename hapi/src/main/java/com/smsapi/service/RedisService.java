package com.smsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.QueueName;
import com.smsapi.model.RequestModel;

@Service
public class RedisService {

	@Autowired MessagePublisherPool messagepublisherpool;

	public boolean addQueue(RequestModel requestmodel) {
		
		
		try {

			System.out.println("requestmodel : "+requestmodel.getJSON());
		if(messagepublisherpool.isAvailable(QueueName.ROUTER)) {
			System.out.println("messagepublisherpool : messagepublisherpool true ");

			messagepublisherpool.getPublisher(QueueName.ROUTER).publish(requestmodel);
			
			System.out.println("messagepublisherpool : messagepublisherpool sent ");

			return true;
		}else {
			
			System.out.println("messagepublisherpool : messagepublisherpool false ");

		}
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return false;
		
	}
	
}
