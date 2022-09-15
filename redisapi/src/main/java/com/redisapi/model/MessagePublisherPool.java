package com.redisapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;



public class MessagePublisherPool {

	@Autowired RedisTemplatePool redistemplatepool;

	private Map<String,Integer> index=new HashMap<String,Integer>();

	Map<String,Map<String,MessagePublisher>> poolpublisher=new HashMap<String,Map<String,MessagePublisher>>();
	
	Map<String,List<MessagePublisher>> messagepublishermap=new HashMap<String,List<MessagePublisher>>();
	
	public MessagePublisherPool() {}
	
	public boolean isAvailable(String queuename) {
		
		
		System.out.println("isAvailable : "+queuename);
		
		if(!poolpublisher.containsKey(queuename)) {
			
			System.out.println("isAvailable : "+queuename+" NO");

			
			poolpublisher.put(queuename, getMessagePublisher(queuename));
			
			
			List<String> redisidlist=redistemplatepool.getActiveredismap().get(queuename);
			

			if(redisidlist==null) {
				
				System.out.println(" getActiveredismap redisidlist : null"+queuename+" NO");

				List<String> queuenamelist=new ArrayList<String>();
				queuenamelist.add(queuename);
				redistemplatepool.refreshAvailability(queuenamelist, redistemplatepool.getAvailableredislist());
				redisidlist=redistemplatepool.getActiveredismap().get(queuename);
			}
			messagepublishermap.put(queuename, getMessagePublisherList(poolpublisher.get(queuename),redisidlist));

		}
		
		if(messagepublishermap.get(queuename).size()>0) {
			
			return true;
		}
		
		return false;
		
	}
	private List<MessagePublisher> getMessagePublisherList(Map<String, MessagePublisher> publisherredismap, List<String> redisidlist) {
		
		List<MessagePublisher> puplisherlist=new ArrayList<MessagePublisher>();
		
		if(redisidlist!=null) {
			
			for(int i=0;i<redisidlist.size();i++) {
				
				puplisherlist.add(publisherredismap.get(redisidlist.get(i)));
			}
		}
		return puplisherlist;
	}

	public void refreshAvailability(List<String> queuenamelist) {
		
		if(queuenamelist!=null) {
		
			redistemplatepool.refreshAvailability(queuenamelist,redistemplatepool.getAvailableredislist());
		
		for(int i=0;i<queuenamelist.size();i++) {
			
			String queuename=queuenamelist.get(i);
			
			List<String> redisidlist=redistemplatepool.getActiveredismap().get(queuename);

			List<MessagePublisher> messagepublisherlist=new ArrayList<MessagePublisher>();
		
			if(redisidlist!=null) {
				
				for(int j=0;j<redisidlist.size();j++) {
					
					String redisid=redisidlist.get(j);
					
					messagepublisherlist.add(poolpublisher.get(queuename).get(redisid));
				}
			}
			
			messagepublishermap.put(queuename, messagepublisherlist);
		}
		
		}
	}
	
	private Map<String, MessagePublisher> getMessagePublisher(String queuename) {

		Map<String,MessagePublisher> publisher=new HashMap<String,MessagePublisher>();
		
		Map<String,RedisTemplate<String, Object>> poolmap=redistemplatepool.getPoolmap();
		
		Iterator<String> itr=poolmap.keySet().iterator();
		
		while(itr.hasNext()) {
			
			String redisname=itr.next();
			
			System.out.println("redisname : "+redisname+" RedisMessagePublisher");
			MessagePublisher mb=new RedisMessagePublisher(poolmap.get(redisname), queuename);
			
			publisher.put(redisname, mb);
		}
		
		return publisher;
	}
	
	
	public MessagePublisher getPublisher(String queuename) {
		
		
		int seq=0;
		
		
		try{
			seq=index.get(queuename);
		}catch(Exception e) {
			
		}
		
		if(seq>messagepublishermap.get(queuename).size()-1) {
			
			seq=0;
		}	
		
		int currrent=seq;
		seq++;
		index.put(queuename, seq);
		
		return messagepublishermap.get(queuename).get(currrent);
		
	}

	
}
