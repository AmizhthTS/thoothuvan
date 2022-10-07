package com.smsapi.user.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smsapi.user.model.UserCacheModel;
import com.smsapi.user.service.UserService;

@Service
public class GetUserJob {

	@Autowired UserService userservice;
	
	@Autowired UserCacheModel usercache;
	
	
	

	@Scheduled(fixedDelay=1000)
	public void getUsersCache() {
		
		String memoryid=userservice.getUsersmemoryid();
		
		if(memoryid!=null) {
		
			if(usercache.getMemoryid()==null||!memoryid.equals(usercache.getMemoryid())) {
				
			UserCacheModel ucm=userservice.getUsers();
			
			if(ucm!=null) {
				
				if(ucm.getMemoryid()!=null&&ucm.getUsercache()!=null) {
				
					usercache.setUsercache(ucm.getUsercache());

					usercache.setMemoryid(ucm.getMemoryid());
					
				}
			}
			}
		}
	
	}
	
}
