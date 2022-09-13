package com.smsapi.service;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.model.TokenModel;
import com.smsapi.model.UserModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class TokenService {

	public String getToken() {
		
		UserModel bean=UserModel.builder().username("system").password("password").build();
    	
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://masterapilb:8080")
        					.contentType(ContentType.JSON)
        					.body(gson.toJson(bean, UserModel.class))
                            .when()
                            .post("/sms-api/auth/login");

        
                            
        if(response.getStatusCode()==200) {
        	return gson.fromJson(response.then().extract().asString(), TokenModel.class).getToken();
        }
        
        return "";
	}
}
