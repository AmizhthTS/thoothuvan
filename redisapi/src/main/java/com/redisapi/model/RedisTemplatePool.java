package com.redisapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.Data;

@Data
public class RedisTemplatePool {
	
	private Map<String/*redisidname*/,RedisInfo> redispoolinfomap=new HashMap<String,RedisInfo>();

	private Map<String/*queuename*/,List<QueueModel>> queueinfomap=new HashMap<String,List<QueueModel>>();
	
	private boolean isRetry=false;
	

	public RedisTemplatePool(boolean isRetry,RedisPropertiesList redispropertieslist) {
		
		List<RedisPropertiesListModel> list=redispropertieslist.getRedislist();
		
		this.isRetry=isRetry;
		
			for(int i=0;i<list.size();i++) {
				
			String redisname=list.get(i).getName();
			
			RedisTemplate<String, Object> template=redisTemplate(list.get(i).getProp());
			
			template.afterPropertiesSet();
			
			redispoolinfomap.put(redisname,RedisInfo.builder().redisid(redisname).redistemplate(template).build());
			
			
		}
		
	}
	
		
	@SuppressWarnings("deprecation")
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
	
	
	
	
	
	

	public void addQueue(String queuename) {
		
		if(!queueinfomap.containsKey(queuename)) {
			
			queueinfomap.put(queuename, getList(queuename));
		}
	}

	private List<QueueModel> getList(String queuename) {
	
	List<QueueModel> list =new ArrayList<QueueModel>();
	
	redispoolinfomap.forEach((redisid,redisinfo)->{
		
		list.add(QueueModel.builder().queuename(queuename).connectionavailable(true).redisinfo(redisinfo).build());
	});
	
	return list;
	}
	


	
}
