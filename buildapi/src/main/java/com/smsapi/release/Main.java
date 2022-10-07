package com.smsapi.release;

import com.smsapi.hapi.HAPI;
import com.smsapi.masterdbapi.MasterAPI;
import com.smsapi.sc.SCAPI;

public class Main {

	public static void main(String[] args) {
	
		String module=System.getenv("module");
		
		System.out.println("module : "+module);
		
		if(module.equals("masterdbapi")) {
			
			MasterAPI.main(args);
		}else if(module.equals("hapi")) {
			
			HAPI.main(args);
		}else if(module.equals("sc")) {
			
			SCAPI.main(args);
		}

	}

}
