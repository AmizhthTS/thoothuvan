package com.smsapi.user.service;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.user.model.TokenModel;
import com.smsapi.user.model.UserModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class TokenService {

	public String getToken() {
		
		UserModel bean=UserModel.builder().username("system").password("password").build();
		boolean retry=true;
		while(retry) {
			
				
			try {
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://masterapilb:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(bean, UserModel.class))
                            .when()
                            .post("/sms-api/auth/login");

        if(response.getStatusCode()==200) {
        	
        	retry=false;
        	return gson.fromJson(response.then().extract().asString(), TokenModel.class).getToken();
        }
				}catch(Exception e) {
				
					gotosleep();
			}
		}
                            
        
        
        return "";
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
