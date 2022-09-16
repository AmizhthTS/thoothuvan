package com.smsapi.model;

import lombok.Data;

@Data
public class ResultModel {

	private String testid;
	
	private long totalthread;
	
	private long completedthread=0;
	
	private long successcount=0;
	
	private long failurecount=0;
	
	private long totalcount=0;
	
	private long starttime=System.currentTimeMillis();
	
	private long endtime=System.currentTimeMillis();
	
	@SuppressWarnings("unused")
	private long accecptancetps;
	
	public ResultModel(String testid, int totalthread) {
		
		this.testid=testid;
		
		this.totalthread=totalthread;
	}
	public void addSuccesscount() {
		
		successcount++;
		
	}
	
	public void addFailurecount() {
		
		failurecount++;
		
	}
	
	public void addTotalcount() {
		
		totalcount++;
	}
	
	public void addCompletedCount() {
		
		completedthread++;
	}
	
	public long getAccecptancetps() {
		
		long timetaken=endtime-starttime;
		
		if(timetaken>0) {
			
			timetaken/=1000;
			if(timetaken>0) {
				
				if(totalcount>0) {
					
					return totalcount/timetaken;
				}
				
			}else {
				
				return totalcount;
			}
			
		}
		
		return 0;
	}
}
