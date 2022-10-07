package com.smsapi.sc.model;

public class TPS{

	
	Stats stats;
	
	public TPS(Stats stats) {
		
		this.stats=stats;
	}
	
	
	public void clearCount(){
		
			stats.setTps(stats.getCounter());
			stats.setCounter(0);
			
			stats.setDntps(stats.getDncounter());
			stats.setDncounter(0);
			
	}

	public synchronized void incrementSMS(){
	
		stats.setSMS(stats.getSMS()+1);
		stats.setCounter(stats.getCounter()+1);
		stats.setLastsmstime(System.currentTimeMillis());
		stats.setLastupdatetime(System.currentTimeMillis());

	}
	
	
	public synchronized void incrementSMSFailed(){
		stats.setSMS_FAILED(stats.getSMS_FAILED()+1);
		stats.setCounter(stats.getCounter()+1);
		stats.setLastsmstime(System.currentTimeMillis());
		stats.setLastupdatetime(System.currentTimeMillis());

	}
	
	public synchronized void incrementSMSRetry(){
		stats.setSMS_FAILED(stats.getSMS_FAILED()-1);
		stats.setCounter(stats.getCounter()-1);
		stats.setSMS_RETRY(stats.getSMS_RETRY()+1);
		stats.setLastsmstime(System.currentTimeMillis());
		stats.setLastupdatetime(System.currentTimeMillis());

	}
	
	
	public synchronized void incrementDN(){
		
		stats.setDN(stats.getDN()+1);
		stats.setDncounter(stats.getDncounter()+1);
		stats.setLastdntime(System.currentTimeMillis());
		stats.setLastupdatetime(System.currentTimeMillis());
		stats.setLastupdatetime(System.currentTimeMillis());

	}
	
	public synchronized void incrementENQUIRELINK(){
		
		stats.setENQUIRELINK(stats.getENQUIRELINK()+1);
		
		stats.setLastenquirelinktime(System.currentTimeMillis());
		
		stats.setLastupdatetime(System.currentTimeMillis());

	}
	
	
	public synchronized void incrementDNPostSuccess() {
		
		stats.setDnpostsuccess(stats.getDnpostsuccess()+1);
	}
	
	public synchronized void incrementDNPostFailuere() {

		stats.setDnpostfailure(stats.getDnpostfailure()+1);
	}
	
	
}
