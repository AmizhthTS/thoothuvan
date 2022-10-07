package com.smsapi.masterdbapi.view;

public interface FormAction {

	public static final String WEBSITENAME="http://"+System.getenv("website.host")+":"+System.getenv("website.port")+"/";
	
	
	public static final String LOGIN_ACTION=WEBSITENAME+"login";
	
	
	public static final String METHOD="POST";

	
}
