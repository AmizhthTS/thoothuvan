package com.smsapi.redisqmonitor.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redisapi.model.QueueModel;
import com.redisapi.model.QueuePoolModel;
import com.redisapi.model.RedisInfo;
import com.redisapi.model.RedisTemplatePool;

@Service
@Transactional
public class MonitorService {
	
	@Autowired RedisTemplatePool redistemlatepool;
	
	@Autowired QueuePoolModel queuepoolmodel;
	
	byte [] QUEUENAME_SEARCH_PATTERN="*".getBytes();
	
	public void doMonitorRedisQueue() {
		
		addQueue();
		
		refreshAvailability();
	}
	
	
	private void addQueue() {
		

		
		Set<byte[]> queueset =new HashSet<byte[]>();
		
		redistemlatepool.getRedispoolinfomap().forEach((redisid,redisinfo)->{
			
			queueset.addAll(getQueueNameSet(redisinfo));
		});
		
		
		queueset.forEach((queuename)->{
			
			addQueue(new String(queuename));
		});
	
	}


	private Set<byte[]> getQueueNameSet(RedisInfo redisinfo) {
		

		
		RedisConnection connection=null;
		
		long result=0;
		
		Set<byte[]> queueset=null;
		
		try {
			
			connection=redisinfo.getRedistemplate().getConnectionFactory().getConnection();
			
			queueset=connection.keys(QUEUENAME_SEARCH_PATTERN);
			
		
			
		}finally {
			
			close(connection);
		}
		
		return queueset;
	
	}


	private void close(RedisConnection connection) {
		
		try {
			
			connection.close();
			
		}catch(Exception e) {
			
		}
		
	}
	
	

	
	public void refreshAvailability() {
		
		
		redistemlatepool.getQueueinfomap().forEach((queuename,queuelist)->{
			
			queuelist.forEach((queuemodel)->{
				
				if(isConnected(queuemodel.getRedisinfo().getRedistemplate())){
					
					setQueueCount(queuename,queuemodel);
				}else {
					
					queuemodel.setConnectionavailable(false);
				}
				});
			
			
			});
		
	}
	
	
	


	

	private boolean isConnected(RedisTemplate<String, Object> redisTemplate) {
		
		RedisConnection connection=null;
		
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
			
			if(!connection.isClosed()) {
				
				return true;
			}
		}finally {
			
			close(connection);
		}
		return false;
	}
	
	
	private void setQueueCount(String queuename, QueueModel queuemodel) {
		
		long count=getCount(queuename, queuemodel.getRedisinfo().getRedistemplate());
		
		queuemodel.setQueuecount(count);	
	}
	
	
	private long getCount(String queuename, RedisTemplate<String, Object> redisTemplate) {
		
		RedisConnection connection=null;
		
		long result=0;
		
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
			
			Long e= connection.lLen(queuename.getBytes());
			
			if(e!=null) {
				
				result=e;
			}
			
		}finally {
			
			close(connection);
		}
		
		return result;
	}
	
	public void addQueue(String queuename) {
		
		if(!redistemlatepool.getQueueinfomap().containsKey(queuename)) {
			
			redistemlatepool.getQueueinfomap().put(queuename, getList(queuename));
		}
	}

	
	
	private List<QueueModel> getList(String queuename) {
		
		List<QueueModel> list =new ArrayList<QueueModel>();
		
		redistemlatepool.getRedispoolinfomap().forEach((redisid,redisinfo)->{
			
			list.add(QueueModel.builder().queuename(queuename).connectionavailable(true).redisinfo(redisinfo).build());
		});
		
		return list;
	}
}