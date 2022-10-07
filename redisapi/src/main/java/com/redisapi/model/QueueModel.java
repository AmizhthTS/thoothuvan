package com.redisapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueueModel {

	private String queuename;
	
	private RedisInfo redisinfo;
	
	private long queuecount;
	
	private boolean connectionavailable;
	
	public boolean isAvailable(boolean retry) {
		
		if(connectionavailable) {
		
		if(retry) {
			
			if(queuecount>500) {
				
				return false;
			}else {
				
				return true;
			}
			
		}else {
			

			if(queuecount>400) {
				
				return false;
			}else {
				
				return true;
			}
			
		}
		}else {
			
			return false;
		}
	}
	
}
