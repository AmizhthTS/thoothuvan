package com.smsapi.simulator.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import com.smsapi.util.Bean;
import com.smsapi.util.MapKeys;
import com.smsapi.util.QueueMap;
import com.smsapi.util.SubmitObject;


@Service
public class SubmitSmValidator {
	

	
	
	public SubmitSmValidator() {
	
	
	}
	
	public void validate(SubmitSm request,SubmitSmResp submitResponse,String systemId,String host) {

			Map<String,Object> logmap=new HashMap<String,Object>();
			Map<String,Object> msgmap=new HashMap<String,Object>();

			Bean.setDefaultValues(msgmap);
			msgmap.put(MapKeys.PROTOCOL, "smpp");
			msgmap.put(MapKeys.INTERFACE_TYPE, "smpp");

    		submitResponse.setMessageId(msgmap.get(MapKeys.ACKID).toString());
	    		
	    	String strDestAddr = request.getDestAddress().getAddress();
	    		
	    	strDestAddr=validateDestinationAddress(systemId,strDestAddr,submitResponse);
	    	
	    	if(systemId.equals("smppclient5")){
	    		
	    		gotosleep();
	    	}
	    	msgmap.put(MapKeys.MOBILE, strDestAddr);
			msgmap.put(MapKeys.USERNAME, systemId);
			msgmap.put(MapKeys.CUSTOMERIP, host);

	    		//if valid destination address
	    		if(submitResponse.getCommandStatus()==0) {
	    			
	    				//extract request object
		    			SubmitObject so = new SubmitObject(	request,submitResponse);
		    			so.setValues(msgmap);

							deliverSubmitSm(msgmap,submitResponse,logmap);
						
	        	}	

	    		logmap.putAll(msgmap);
	    		
				logmap.put("logname", "smpp_submit");

				try{
					
					long rtime=Long.parseLong(msgmap.get(MapKeys.RTIME).toString());
					logmap.put("timetaken in ms", ""+(System.currentTimeMillis()-rtime));

				}catch(Exception e){
					
				}
	    		logmap.put("smpp status ", submitResponse.getCommandStatus()+" ");
	    		
	
		}
	
		
		private void gotosleep() {
		try{
			
			Thread.sleep(125L);
					
		}catch(Exception e){
			
		}
		
	}

		private String validateDestinationAddress(String systemid,String strDestAddr,SubmitSmResp submitResponse) {
			
    		if( strDestAddr == null || strDestAddr.trim().length() < 8 
    				|| strDestAddr.trim().length() > 15 || StringUtils.isNumeric(StringUtils.stripStart(strDestAddr, "+"))==false)	{              						
		
    			submitResponse.setCommandStatus(SmppConstants.STATUS_INVDSTADR);
    			
    		}  else {//format destination address appending 91 if required
    			
    			strDestAddr = StringUtils.stripStart(strDestAddr, "+");
        		strDestAddr = StringUtils.stripStart(strDestAddr, "0");
        		
        
        	
    		}
    		
    		return strDestAddr;
		}
		
		private void deliverSubmitSm(Map<String,Object> msgmap,SubmitSmResp submitResponse,Map<String,Object> logmap) {
				
				
					try
					{
						boolean pushed=false;
						
						 QueueMap.getInstance().offer(msgmap.get(MapKeys.USERNAME).toString(),msgmap);
						
				
						
					}
					catch(Exception e)
					{
						submitResponse.setCommandStatus(SmppConstants.STATUS_SYSERR);
					}		      
			
	
		}
		
	    
}
