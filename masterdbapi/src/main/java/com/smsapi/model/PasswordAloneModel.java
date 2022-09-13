package com.smsapi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class PasswordAloneModel   {
	
	private List<String> passwordlist=new ArrayList<String>();

	public void setPasswordlist(List<OldPasswordModel> passwordlist) {
		
		if(passwordlist!=null) {
			
			for(int i=0;i<passwordlist.size();i++) {
				
				this.passwordlist.add(passwordlist.get(i).getPassword());
			}
		}
	}	
}
