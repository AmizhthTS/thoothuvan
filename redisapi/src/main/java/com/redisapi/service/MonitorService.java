package com.redisapi.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.redisapi.model.MessagePublisherPool;
import com.redisapi.model.QueuePoolModel;
import com.redisapi.model.RedisTemplatePool;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
@Transactional
public class MonitorService {
	
	@Autowired RedisTemplatePool redistemplatepool;
	
	@Autowired MessagePublisherPool messagepublisherpool;
	
	public void doMonitorRedisQueue() {
		
		QueuePoolModel queuepoolinfo=getQueuePoolInfo();
		
		if(queuepoolinfo!=null) {
			
			redistemplatepool.getQueueinfomap().forEach((queuename,queuelist)->{
				
				redistemplatepool.getQueueinfomap().put(queuename, queuepoolinfo.getQueueinfomap().get(queuename));
			});
			
			messagepublisherpool.setAvailability();
		}
	}
	
	private QueuePoolModel getQueuePoolInfo() {
		
		boolean retry=true;
		while(retry) {
			
				
			try {
    	Gson gson =new Gson();
        Response response = given()
        					.baseUri("http://redisqmonitorlb:8080")
        					.contentType(ContentType.JSON)
                            .when()
                            .post("/sms-api/redis/getqueuepoolinfo");

        if(response.getStatusCode()==200) {
        	
        	retry=false;
        	return gson.fromJson(response.then().extract().asString(), QueuePoolModel.class);
        }
				}catch(Exception e) {
				
					gotosleep();
			}
		}
                            
        
        
        return null;
	
	}
	
	private void gotosleep() {
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
