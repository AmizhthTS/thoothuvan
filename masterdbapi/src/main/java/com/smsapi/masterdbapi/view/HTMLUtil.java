package com.smsapi.masterdbapi.view;

import com.smsapi.masterdbapi.model.UserModel;

public class HTMLUtil {
	
	
	public static String getHeader() {
		
		StringBuilder sb=new StringBuilder();
		sb.append("<center><h1>Amizhth SMS Service</h1></center><hr>");
		
		return sb.toString();
	}
	public static  String getLoginBody(UserModel usermodel,String errorMessage) {
		
		StringBuilder sb=new StringBuilder();
		sb.append(getCenterTag(false));
		sb.append(getFormTag(FormAction.LOGIN_ACTION, FormAction.METHOD));

		sb.append(getTableStart(1));
		
		sb.append(getRowStart("center",2));
		sb.append(getLabel("Login"));
		sb.append(getColumnEnd(false));
		
		
		sb.append(getRowStart("right",1));
		sb.append(getLabel("Username"));
		sb.append(getColumnEnd(true));
		sb.append(getTextBox("username",usermodel==null?"":usermodel.getUsername(),16,"text"));
		sb.append(getColumnEnd(false));
		
		
		
		
		sb.append(getRowStart("right",1));
		sb.append(getLabel("Password"));
		sb.append(getColumnEnd(true));
		sb.append(getTextBox("password",usermodel==null?"":usermodel.getPassword(),16,"password"));
		sb.append(getColumnEnd(false));
		
		if(errorMessage!=null&&errorMessage.trim().length()>0) {
			
			sb.append(getRowStart("center",2));
			sb.append(errorMessage);
			sb.append(getColumnEnd(true));
		}
		
		sb.append(getTableEnd());

		sb.append(getCenterTag(true));
		sb.append(getLabel("Login"));
		
		return sb.toString();
	}

	private static String getTextBox(String attibutename,String value,int maxlength,String type) {
		StringBuilder sb=new StringBuilder();
		value=value==null?"":value;
		sb.append("<input type='").append(type).append("' style='font-size: 18pt; height: 40px; width:360px;background-color: #d1d1d1' name=");
		sb.append(" maxlength='").append(maxlength).append("' value='").append(value).append("'/>");
		return sb.toString();
	}
	public static String getFormTag(String action,String method) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("<form action='").append(action).append("' method='").append(method).append("'>");

		return sb.toString();
	}
	public static String getCenterTag(boolean isEnd) {
		
		if(isEnd) {

			return "</center>";
			
		}else {
			return "<center>";
		}
	}
	
	public static String getTableEnd() {
		
		return "</tablel>";
	}
	public static String getTableStart(int border) {
		StringBuilder sb=new StringBuilder();
		sb.append("<tablel  border='"+border+"'>");
		return sb.toString();
	}
	public static String getLabel(String label) {
	
		StringBuilder sb=new StringBuilder();
		
		sb.append("<p style='font-size:30px'>");
		sb.append(label);
		sb.append("</p>");
		
		return sb.toString();
		
	}
	
	
	public static String getColumnEnd(boolean isColumnAlone) {
		
		if(isColumnAlone) {
		
			return "</td>";
		}else {
			return "</td></tr>";
		}
	}
	
	public static String getRowStart(String align,int colspan) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("<tr><td align='").append(align).append("' colspan='").append(colspan).append("' >");
		
		return sb.toString();
	}
	
	public static String getColumnContinue() {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("</td><td>");
		
		return sb.toString();
	}
}
