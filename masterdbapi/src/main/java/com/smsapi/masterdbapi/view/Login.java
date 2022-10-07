package com.smsapi.masterdbapi.view;

import com.smsapi.masterdbapi.model.UserModel;

public class Login {

	
	public static String getContent(UserModel usermoderl,String errormessage) {
		
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(HTMLUtil.getHeader());
		
		sb.append(HTMLUtil.getLoginBody(usermoderl,errormessage));
		
		return sb.toString();
	}
}
