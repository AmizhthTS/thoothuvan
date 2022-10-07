package com.smsapi.sc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.tlv.Tlv;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.sc.model.SMSModel;
import com.smsapi.sc.model.SMSRequest;
import com.smsapi.sc.model.SMSResponse;
import com.smsapi.sc.model.TPS;
import com.smsapi.sc.util.ResponseStatus;

@Service
public class SMSService {
	
	@Autowired
	SMSCModel smscmodel;
	
	@Autowired
	SessionService sessionservice;
	
	@Autowired
	TPS tps;
	
	private boolean isValidCredential(SMSRequest smsrequest) {
		
		return true;
	}
	
	
	public List<SMSResponse> send(SMSRequest smsrequest) throws Exception {
		
		
		if(isValidCredential(smsrequest)) {
			
			
			return doProcess(smsrequest);
			
		}else {
			
			return getInvalidCredentialResponse(smsrequest);
		}
		
	}
	
	public List<SMSResponse> getInvalidCredentialResponse(SMSRequest smsrequest){
		
		List<SMSResponse> smsresponselist=new ArrayList<SMSResponse>();
		
		List<SMSModel> smslist=smsrequest.getSmslist();
		
		if(smslist!=null&&smslist.size()>0) {
			
			for(int i=0;i<smslist.size();i++) {
				smsresponselist.add(SMSResponse.builder().requestid(smslist.get(i).getRequestid()).status(ResponseStatus.INVALID_CREDENTIAL).build());

			}
		}
		
		return smsresponselist;
	}
	private List<SMSResponse> doProcess(SMSRequest smsrequest) throws Exception {

		
		if(!sessionservice.isBound()) {
			
			sessionservice.bind();
		}
		
		
		SubmitSm submit=new SubmitSm();
		
		Address dest=new Address((byte)1,(byte)0,smsrequest.getMobilenumber());
		Address source=new Address((byte)5,(byte)0,smsrequest.getSenderid());
		submit.setDestAddress(dest);
		submit.setSourceAddress(source);
		submit.setRegisteredDelivery((byte)1);
		submit.setDataCoding(smsrequest.getDatacoding());
		submit.setEsmClass(smsrequest.getEsmclass());
		setDLTValue(submit,smsrequest);

		List<SMSModel> smslist=smsrequest.getSmslist();
		
		List<SMSResponse> smsresponselist=new ArrayList<SMSResponse>();

		if(smslist!=null&&smslist.size()>0) {
			
			for(int i=0;i<smslist.size();i++) {
				
				SMSModel sms=smslist.get(i);
				
				setMessage(submit, smsrequest.getDatacoding(), sms);
				
				smsresponselist.add(send(submit,sms.getRequestid()));
						
						
			}
		}

		
		return smsresponselist;
	}

	
	
	
	
	
	

	private SMSResponse send(SubmitSm submit,String requestid) {
		
		
		SMSResponse smsresponse=null;
		
		while(true) {
			
			smsresponse=sessionservice.submit(submit);

			if(smsresponse.getStatus()==SmppConstants.STATUS_THROTTLED) {
			
				tps.incrementSMSRetry();
				
				gotosleep();
			
			}else {
				
				smsresponse.setRequestid(requestid);
				break;
			}
				
		
		}
		
		
		return smsresponse;
		
	}


	private void gotosleep() {
		
		try {
			Thread.sleep(20);
		}catch(Exception ignore) {
			
		}
		
	}


	private void setDLTValue(SubmitSm submit,SMSRequest smsrequest) {
		
		if(smsrequest.getEntityid()!=null){
			
			Tlv entityid=new Tlv((short)0x1400, smsrequest.getEntityid().getBytes());
			submit.setOptionalParameter(entityid);

		}
		
		if(smsrequest.getTemplateid()!=null) {
			
			Tlv templateid=new Tlv((short)0x1401, smsrequest.getTemplateid().getBytes());

			submit.setOptionalParameter(templateid);

		}

		if(smscmodel.getTelemarketerid()!=null&&smscmodel.getTelemarketerid().trim().length()>0) {
			
			
			Tlv telemarketerid=new Tlv((short)0x1402, smscmodel.getTelemarketerid().getBytes());

			submit.setOptionalParameter(telemarketerid);
		}
		
		
		
		
	}

		
	private void setMessage(SubmitSm submit,byte datacoding,SMSModel smsmodel) throws SmppInvalidArgumentException {
		
		if(smsmodel.getUdh()==null||smsmodel.getUdh().trim().length()<1) {
			
			submit.setShortMessage(smsmodel.getSms().getBytes());

		}else {
			
			StringBuilder sb=new StringBuilder();
			sb.append(smsmodel.getUdh());
			
			if(datacoding==0||datacoding==16) {
				
				
				String temp=toHexStringASCII(smsmodel.getSms());
				temp=temp.replaceAll("2019", "27");
				temp=temp.replaceAll("60", "27");
				sb.append(temp);
				
				
			}else {
				sb.append(smsmodel.getSms());
			}
			
			submit.setShortMessage(sb.toString().getBytes());

		}
	}
	
	private  String toHexStringASCII(String str) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < str.length(); i++) {
	      sb.append(toHexString(str.charAt(i)));
	    }
	    return sb.toString().replaceAll("00", "");
	  }
  
	private  String toHexString(char ch) {
	    String hex = Integer.toHexString((int) ch);
	    while (hex.length() < 4) {
	      hex = "0" + hex;
	    }
	    return hex;
	  
	  }


}
