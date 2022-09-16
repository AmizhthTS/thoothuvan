package com.smsapi.service;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.smsapi.model.RequestModel;
import com.smsapi.model.ResponseModel;
import com.smsapi.model.ResultPoolModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Component
public class SMSSender implements Runnable{
	
	int id;
	
	int count;
	
	String testid;
	
	static Map<Integer,Integer> result=new HashMap<Integer,Integer>();
	
	@Autowired ResultPoolModel resultpool;
	
	public SMSSender() {
		
	}
	public SMSSender(@Autowired ResultPoolModel resultpool,int id,int count,String testid) {
		
		this.resultpool=resultpool;
		
		this.id=id;
		
		this.count=count;
		
		this.testid=testid;
	}

	@Override
	public void run() {
		
		System.out.println("Start send SMS count:"+count);
		RequestModel request=RequestModel.builder().username("master").password("password").fullmessage("test message").senderid("TESTAB").mobile("9487660738").build();

		for(int i=0;i<count;i++) {
		
	        result.put(id, i);

	        System.out.println("result :"+result);

			sendSMS(request);
			
			resultpool.addTotalcount(testid);
		}
		
		resultpool.setEndtime(testid);
		
		resultpool.addCompletedCount(testid);
	}

	private void sendSMS(RequestModel request) {
		
		Gson gson =new Gson();

		
        Response response = given()
        					.baseUri("http://hapi:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(request, RequestModel.class))
                            .when()
                            .post("/sms-api/send");

   //     System.out.println(response.getStatusCode());
        
     //   System.out.println(response.then().extract().asString());
                            
        if(response.getStatusCode()==200) {
        	
        	
        	if(gson.fromJson(response.then().extract().asString(), ResponseModel.class).getStatuscode().equals("200")) {
        		
        		if(resultpool==null) {
        			
        			System.out.println("resultpool null");
        		}else {
        			
            		resultpool.addSuccesscount(testid);

        		}
        	}else {
        	
        		resultpool.addFailurecount(testid);
        	}
        }else {
    		resultpool.addFailurecount(testid);

        }


		
	
	}

}
