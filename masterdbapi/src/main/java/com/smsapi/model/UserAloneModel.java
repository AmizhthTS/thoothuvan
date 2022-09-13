package com.smsapi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class UserAloneModel   {
	

	private List<String> usernamelist=new ArrayList<String>();

	public void setUsers(List<CreditModel> userlist) {
		
		if(userlist!=null) {
			
			for(int i=0;i<userlist.size();i++) {
				
				usernamelist.add(userlist.get(i).getUsername());
			}
		}
	}
	

	public void setOldPasswordModel(List<OldPasswordModel> oldpassowrdlist) {
		
		if(oldpassowrdlist!=null) {
			
			for(int i=0;i<oldpassowrdlist.size();i++) {
				
				usernamelist.add(oldpassowrdlist.get(i).getUsername());
			}
		}
	}
	
}
