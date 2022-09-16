package com.redisapi.model;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageListener {
	
	RedisTemplate<String, Object> redisTemplate;
	
	MessagePublisherPool messagePublisherPool;
	
	String queuename;

	public RedisMessageListener(final MessagePublisherPool messagePublisherPool,final RedisTemplate<String, Object> redisTemplate, final String queuename) {
	        this.redisTemplate = redisTemplate;
	        this.queuename = queuename;
	        this.messagePublisherPool=messagePublisherPool;
	}

	public void doProcess() {
		
		
		while(true) {
			
		Object data=getData();
			
			if(data==null) {
				
				return;
			}
			

			send(data);
		}
	}
	
	
	private void send(Object data) {
		
		while(true) {
			if(messagePublisherPool.isAvailable(QueueName.SMSC)) {
			messagePublisherPool.getPublisher(QueueName.SMSC).publish(data);
			return;
			}
			}
		
	}

	private Object getData() {
		
		
		byte bytedata []=getBytes();
		
		
		if(bytedata!=null) {
			
			try {
				return consume(bytedata);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private byte[] getBytes() {
		RedisConnection connection=null;
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
		  return connection.lPop(queuename.getBytes());
		}catch(Exception e) {
			
		}finally {
			
			close(connection);
		}
		
		return null;
	}

	private void close(RedisConnection connection) {
		try {
			
			connection.close();
			
		}catch(Exception e) {
			
		}
		
	}

	private Object consume(byte[] bytes) throws Exception
    {
        ByteArrayInputStream bis = null;
        ObjectInput in = null;

        bis = new ByteArrayInputStream(bytes);

        in = new ObjectInputStream(bis);
        Object dtoobj =  in.readObject();
        in.close();
        bis.close();
        return dtoobj;
    }

}
