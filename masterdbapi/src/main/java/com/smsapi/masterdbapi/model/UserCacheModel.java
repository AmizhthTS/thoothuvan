package com.smsapi.masterdbapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class UserCacheModel {

	private Map<String,UserModel> usercache=new HashMap<String,UserModel>();
	
	private String memoryid;
	
	public void updateUserModel(String memoryid,List<UserModel> userlist) {
		
		this.memoryid=memoryid;
		
		if(userlist!=null&&userlist.size()>0) {
			
			for(int i=0;i<userlist.size();i++) {
			
				usercache.put(userlist.get(i).getUsername(), userlist.get(i));
			}
		}
	}
}
