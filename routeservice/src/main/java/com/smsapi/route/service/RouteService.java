package com.smsapi.route.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.masterdbapi.model.UserCacheModel;
import com.smsapi.user.model.TokenModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class RouteService {

	@Autowired TokenModel token;
	
	
	
	public RouteDefaultModel getRouteDefault() {
		
    	
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://masterapilb:8080")
        					.auth().oauth2(token.getToken())
        					.contentType(ContentType.JSON)
                            .when()
                            .post("/sms-api/route/getroutedefault");

        
                            
        if(response.getStatusCode()==200) {
        	return gson.fromJson(response.then().extract().asString(), RouteDefaultModel.class);
        }
        
        return null;
	}
	
	
}
