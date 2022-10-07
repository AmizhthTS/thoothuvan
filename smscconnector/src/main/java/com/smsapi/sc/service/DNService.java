package com.smsapi.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudhopper.smpp.PduAsyncResponse;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.SmppSessionHandler;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.sc.model.DNModel;


@Service
public class DNService implements SmppSessionHandler {
	

		@Autowired
		DNPostService dnpostservice;


		@Autowired
		SMSCModel smscmodel;
		
		public String lookupTlvTagName(short tag) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		public String lookupResultMessage(int commandStatus) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		public void fireUnrecoverablePduException(UnrecoverablePduException e) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void fireUnknownThrowable(Throwable t) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void fireUnexpectedPduResponseReceived(PduResponse pduResponse) {
			// TODO Auto-generated method stub
			
		}
		
		public void fireRecoverablePduException(RecoverablePduException e) {
			// TODO Auto-generated method stub
			
		}
		
		
		public PduResponse firePduRequestReceived(PduRequest pduRequest) {
			
			if(pduRequest instanceof EnquireLink){
				
				return pduRequest.createResponse();
				
			}else if(pduRequest instanceof DeliverSm){
				
				DeliverSm request=(DeliverSm)pduRequest;
				
				String dn=new String(request.getShortMessage());
				
				sendToQueue(dn);
				
				return pduRequest.createResponse();
			}else{
				
				pduRequest.setCommandStatus(SmppConstants.STATUS_INVCMDID);
				
				return pduRequest.createResponse();
			}
		}
		
		private void sendToQueue(String dn) {
			
			dnpostservice.post(DNModel.builder().dn(dn).smscid(smscmodel.getSmscid()).build());
		}

		
		public void firePduRequestExpired(PduRequest pduRequest) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void fireChannelUnexpectedlyClosed() {
			// TODO Auto-generated method stub
			
		}
	

	}
