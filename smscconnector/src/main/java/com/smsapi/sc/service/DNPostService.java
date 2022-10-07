package com.smsapi.sc.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.sc.model.DNModel;
import com.smsapi.sc.model.TPS;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class DNPostService {

	private static final String DNPOST_URL="http://masterapilb:8080";
	
	private static final String DNPOST_CONTEXT="/sms-api/dn/add";
	
	@Autowired
	TPS tps;
	
	 public void post(DNModel dnmodel)  {
		 
	    	Gson gson =new Gson();
	    	
	        Response response = given().baseUri(DNPOST_URL).contentType(ContentType.JSON).body(gson.toJson(dnmodel, DNModel.class))
	                            .when()
	                            .post(DNPOST_CONTEXT);

	        
	                            
	        if(response.getStatusCode()==200) {
	           
	        	tps.incrementDNPostSuccess();
	        }else {

	        	tps.incrementDNPostFailuere();
	        }
	        
	                 
	 
	 
	    }
	 
}
