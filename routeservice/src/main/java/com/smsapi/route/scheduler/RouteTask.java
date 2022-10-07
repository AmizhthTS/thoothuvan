package com.smsapi.route.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RouteTask {

	
	@Scheduled(fixedDelay=1000)
	public void routeDefault() {

		
	}
}
