package com.redisapi.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessagePublisher implements MessagePublisher {
	
    
    private RedisTemplate<String, Object> redisTemplate;

    private String queuename;

    public RedisMessagePublisher() {
    }

    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final String queuename) {
        this.redisTemplate = redisTemplate;
        this.queuename = queuename;
    }

    public void publish(final Object message) {
        
        redisTemplate.getConnectionFactory().getConnection().lPush(queuename.getBytes(), getBytes(message));
    }
    

    private byte[] getBytes(final Object message) {
    	
    	ObjectOutput oo = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		
		try {
			oo = new ObjectOutputStream(bos);
			oo.writeObject(message);
			return bos.toByteArray();		


		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		return null;
    	
    }
}
