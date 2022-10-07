package com.smsapi.masterdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.RedisQueuePropertiesDao;
import com.smsapi.masterdbapi.exception.RedisExsist;
import com.smsapi.masterdbapi.model.RedisPropertiesModel;


@Service
@Transactional
public class RedisService {

	@Autowired
	private RedisQueuePropertiesDao rqpdao;
	
	
	
	public RedisPropertiesModel createRedisQueuePropertyModel(RedisPropertiesModel rdm){

		RedisPropertiesModel routedefaultmodel = rqpdao.findByNameEquals(rdm.getName());

		if(routedefaultmodel!=null) {
		
			throw new RedisExsist("RedisExsist");
		}
		
		rqpdao.saveAndFlush(rdm);
		
		return rqpdao.findByNameEquals(rdm.getName());
						
	}
	
	

	

}