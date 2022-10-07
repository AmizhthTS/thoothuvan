package com.smsapi.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.smsapi.model.QPTRequestModel;
import com.smsapi.model.RequestModel;

public class Convertor {

	public static QPTRequestModel getData(RequestModel request) {
		
	
		return QPTRequestModel.builder().requestid(request.getRequestID()).username(request.getUsername()).rdate(request.getRdate()).status((byte)0).data(getBytes(request)).build();
	}
	
	
	private static byte[] getBytes(final Object message) {
    	
    	ObjectOutput oo = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		
		try {
			oo = new ObjectOutputStream(bos);
			oo.writeObject(message);
			return bos.toByteArray();		


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return null;
    	
    }
}
