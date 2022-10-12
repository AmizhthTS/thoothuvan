package com.smsapi.hapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.QueueName;
import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.validator.model.RequestModel;

@Service
@DependsOn({"messagepublisherpool"})
public class RedisService {

	private static final String MODULE="smsc";
	
	@Autowired MessagePublisherPool messagepublisherpool;

	@Autowired RouteDefaultModel routedefaultmodel;
	
	public boolean addQueue(RequestModel requestmodel) {
		
		
		try {

			System.out.println("requestmodel : "+requestmodel.getJSON());
		if(messagepublisherpool.isAvailable(MODULE,routedefaultmodel.getTransactionalgroupname(),requestmodel.getUsername())) {
			System.out.println("messagepublisherpool : messagepublisherpool true ");

			messagepublisherpool.getPublisher(MODULE,routedefaultmodel.getTransactionalgroupname(),requestmodel.getUsername()).publish(requestmodel);
			
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
