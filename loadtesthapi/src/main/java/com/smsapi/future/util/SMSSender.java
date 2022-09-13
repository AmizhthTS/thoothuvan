package com.smsapi.future.util;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.smsapi.future.model.RequestModel;
import com.smsapi.future.model.ResponseModel;
import com.smsapi.future.model.ResultPoolModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SMSSender implements Runnable{
	
	int count;
	
	String testid;
	
	@Autowired ResultPoolModel resultpool;
	
	public SMSSender(int count,String testid) {
		
		this.count=count;
	}

	public void run() {
		
		RequestModel request=RequestModel.builder().username("master").password("password").fullmessage("test message").senderid("TESTAB").mobile("9487660738").build();
		Gson gson =new Gson();

		for(int i=0;i<count;i++) {
		
			sendSMS(gson,request);
			resultpool.getResultpool().get(testid).addTotalcount();
		}
		
		resultpool.getResultpool().get(testid).setEndtime(System.currentTimeMillis());
		
		resultpool.getResultpool().get(testid).addCompletedCount();
	}

	private void sendSMS(Gson gson, RequestModel request) {
		

		
        Response response = given()
        					.baseUri("http://hapi:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(request, RequestModel.class))
                            .when()
                            .post("/sms-api/send");

        
                            
        if(response.getStatusCode()==200) {
        	
        	if(gson.fromJson(response.then().extract().asString(), ResponseModel.class).getStatuscode().equals("200")) {
        		
        		resultpool.getResultpool().get(testid).addSuccesscount();
        	}else {
        	
        		resultpool.getResultpool().get(testid).addFailurecount();
        	}
        }else {
        	
        	resultpool.getResultpool().get(testid).addFailurecount();
        }


		
	
	}

}
