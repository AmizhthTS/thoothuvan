package com.smsapi.sc.service;

import com.cloudhopper.smpp.SmppClient;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.pdu.EnquireLinkResp;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import com.smsapi.sc.model.SMSResponse;
import com.smsapi.sc.model.SMSResponse.SMSResponseBuilder;
import com.smsapi.sc.model.Stats;
import com.smsapi.sc.model.TPS;
import com.smsapi.sc.util.ErrorMessage;
import com.smsapi.sc.util.ResponseStatus;


public class SessionService implements Runnable{
	
	private String threadname;

	private DNService dnhandler;
	
	private SmppSession session=null;
	
	SmppSessionConfiguration sessionconfiguration;
	
	TPS tps;
	
	Stats stats;
	
	Object data;
	
	public SessionService(SmppSessionConfiguration sessionconfiguration,DNService dnhandler,TPS tps,Stats stats,Object data) {
		
		this.sessionconfiguration=sessionconfiguration;
		this.dnhandler=dnhandler;
		this.tps=tps;
		this.data=data;
	}
	
	public void run() {
		
		this.threadname=Thread.currentThread().getName();
		
	}
	private void bind() {
		
		SmppClient client=new DefaultSmppClient();

		while(true){
		
		try {
			session=client.bind(sessionconfiguration,dnhandler);

			stats.setBounderror(null);

			break;
		} catch (Exception e) {

			stats.setBounderror(ErrorMessage.getMessage(e));

			gotosleep(2000L);
		} 	
		
		}
		
		
	}
		
		
		private void gotosleep(long l) {
			
			try{
				
				Thread.sleep(l);
			}catch(Exception e){
				
			}
			
			
		}
	
	public void sendEnquireLink(){
		
		long diff=System.currentTimeMillis()-stats.getLastupdatetime();
		
		if(diff>18){
			
			if(session!=null&&session.isBound()){
				
				try {
					EnquireLinkResp resp=session.enquireLink(new EnquireLink(), 500L);
					
					if(resp.getCommandStatus()==SmppConstants.STATUS_OK){

						tps.incrementENQUIRELINK();
					}
					
				} catch (Exception e) {
				
					stats.setEnquirelinkerror(ErrorMessage.getMessage(e));
				} 
			}
		}
	}
	
	
	public SMSResponse submit(SubmitSm submit) {
	SMSResponseBuilder responseuilder=SMSResponse.builder();
	
	SubmitSmResp response=null;
	try {
		response = session.submit(submit, 60000L);
	}catch (Exception e) {

		responseuilder.error(ErrorMessage.getMessage(e));
		
	}
	
	if(response!=null) {
		
		if(response.getCommandStatus()==SmppConstants.STATUS_OK){

			tps.incrementSMS();
			
			responseuilder.messageid(response.getMessageId());


		}else {
			
		}
		
		responseuilder.status(response.getCommandStatus());

		
	}else{
		responseuilder.status(ResponseStatus.NO_RESPONSE_FROM_CARRIER);

	}
	
		return responseuilder.build();
		
		
	}
	
	public boolean isBound() {
		
		return session.isBound();
	}

}
