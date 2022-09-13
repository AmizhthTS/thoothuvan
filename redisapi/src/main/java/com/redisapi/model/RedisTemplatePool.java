package com.redisapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.Data;

@Data
public class RedisTemplatePool {
	
	private Map<String,RedisTemplate<String, Object>> poolmap=new HashMap<String,RedisTemplate<String, Object>>();

	private Map<String,Integer> indexmap=new HashMap<String,Integer>();
	
	private List<String> availableredislist=null;
	
	private Map<String,List<String>> activeredismap=new HashMap<String,List<String>>();
	
	private boolean isRetry=false;
	

	public RedisTemplatePool(boolean isRetry,RedisPropertiesList redispropertieslist) {
		
		List<RedisPropertiesListModel> list=redispropertieslist.getRedislist();
		
		availableredislist=new ArrayList<String>();
		
		this.isRetry=isRetry;
		
			for(int i=0;i<list.size();i++) {
				
			String redisname=list.get(i).getName();
			
			availableredislist.add(redisname);
			
			RedisTemplate<String, Object> template=redisTemplate(list.get(i).getProp());
			
			template.afterPropertiesSet();
			
			poolmap.put(redisname,template);
			
			
		}
		
	}
	
		
	private JedisConnectionFactory jedisConnectionFactory(RedisPropertiesM prop) {
		JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName(prop.getHostname());
	    jedisConFactory.setPort(prop.getPort());
	    jedisConFactory.setDatabase(prop.getDb());
	    return jedisConFactory;	
	    }

	private RedisTemplate<String, Object> redisTemplate(RedisPropertiesM prop) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory(prop));
	    return template;
	}
	
	
	
	
	
	public void refreshAvailability(List<String> queuenamelist, List<String> redisidlist) {
		
		for(int j=0;j<queuenamelist.size();j++) {
			
			String queuename=queuenamelist.get(j);
			
			List<String> availablepool=new ArrayList<String>();
			

				for(int i=0;i<redisidlist.size();i++) {
					
				String redisid=availableredislist.get(i);
				
				if(isConnected(poolmap.get(redisid))) {
					
					if(!isQueued(queuename,poolmap.get(redisid))) {
					
						availablepool.add(redisid);

					}
				}
			}
			
			
			activeredismap.put(queuename, availablepool);
		}
		
	}
	
	
	private boolean isQueued(String queuename, RedisTemplate<String, Object> redisTemplate) {
		
			
		//	long count=getCount(queuename, redisTemplate);
			long count=0;
			if(isRetry) {
				
				if(count>15000) {
					
					return true;
				}else {
					
					return false;
				}
			}else {
				
				if(count>10000) {
					
					return true;
				}else {
					
					return false;
				}
			}
			
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
			
			connection.close();
		}
		
		return result;
	}

	private boolean isConnected(RedisTemplate<String, Object> redisTemplate) {
		
		RedisConnection connection=null;
		
		try {
			connection=redisTemplate.getConnectionFactory().getConnection();
			
			if(!connection.isClosed()) {
				
				return true;
			}
		}finally {
			
			connection.close();
		}
		return false;
	}

	
	public Map<String,Map<String,Long>> getQueueCountMap(List<String> queuenamelist){
		
		Map<String,Map<String,Long>> redisqueuecountmap=new HashMap<String,Map<String,Long>>();
		
		Iterator itr=poolmap.keySet().iterator();
		
		while(itr.hasNext()) {
			
			String redisid=itr.next().toString();
			
			if(queuenamelist!=null) {
				
				Map<String ,Long > queuemap=new HashMap<String,Long>();
				for(int i=0;i<queuenamelist.size();i++) {
				
					String queuename=queuenamelist.get(i);
					
					queuemap.put(queuename, getCount(queuename, poolmap.get(redisid)));
					
				}
				
				redisqueuecountmap.put(redisid, queuemap);
			}
		}
		
		return redisqueuecountmap;
	}


	
	
}
