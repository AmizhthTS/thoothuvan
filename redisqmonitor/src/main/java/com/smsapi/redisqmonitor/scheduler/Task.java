package com.smsapi.redisqmonitor.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.redisqmonitor.service.MonitorService;


@Service
@Transactional
public class Task {

	@Autowired MonitorService monitorservice;
	
	@Scheduled(fixedDelay=5)
	public void doRedisQMonitorProcess() {
		
		monitorservice.doMonitorRedisQueue();
		
	}
}
