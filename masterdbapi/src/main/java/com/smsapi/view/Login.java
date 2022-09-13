package com.smsapi.view;

import com.smsapi.model.UserModel;

public class Login {

	public static String getContent(UserModel usermoderl,String errormessage) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(HTMLUtil.getHeader());
		
		sb.append(HTMLUtil.getLoginBody(usermoderl,errormessage));
		
		return sb.toString();
	}
}
