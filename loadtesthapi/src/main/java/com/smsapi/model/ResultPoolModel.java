package com.smsapi.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultPoolModel {

	Map<String,ResultModel> resultpool=new HashMap<String,ResultModel>();
	
	
	public synchronized void addSuccesscount(String testid) {
		
		if(resultpool.containsKey(testid)) {
			
			resultpool.get(testid).addSuccesscount();
		}
	}
	
	public synchronized  void addFailurecount(String testid) {
		
		if(resultpool.containsKey(testid)) {
		
			resultpool.get(testid).addFailurecount();
		}
		
	}
	
	public synchronized  void addTotalcount(String testid) {
		
		if(resultpool.containsKey(testid)) {
			
			resultpool.get(testid).addTotalcount();
		}
	}
	
	public synchronized void addCompletedCount(String testid) {
		
		if(resultpool.containsKey(testid)) {

			resultpool.get(testid).addCompletedCount();
		}
	}
	
	
	public synchronized void setEndtime(String testid) {
		
		if(resultpool.containsKey(testid)) {

			resultpool.get(testid).setEndtime(System.currentTimeMillis());
		}
	}
	
}
