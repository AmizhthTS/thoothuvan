package com.smsapi.sc.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.user.model.TokenModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class SMSCModelService {

	@Autowired TokenModel tokenmodel; 
	public SMSCModel getSMSCInfo() {
		
		
		boolean retry=true;

		while(retry) {
			
			System.out.println("getSMSCInfo");
			try {
				
    	Gson gson =new Gson();
    	
    	
    	
        Response response = given()
        					.baseUri("http://masterapilb:8080").contentType(ContentType.JSON)
        					.auth().oauth2(tokenmodel.getToken())
        					.body(gson.toJson(SMSCModel.builder().smscid(System.getenv("smscid")).build(), SMSCModel.class))
                            .when()
                            .post("/sms-api/smsc/get");

        
                            
        if(response.getStatusCode()==200) {
        
        	retry=false;

        	return gson.fromJson(response.then().extract().asString(), SMSCModel.class);
        		
        }
        
			}catch(Exception e) {
				
				gotosleep();
		}
        
                 
 
		}
    
	
		return null;
                            
        
        
	}

	private void gotosleep() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
