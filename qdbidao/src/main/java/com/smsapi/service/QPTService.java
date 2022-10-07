package com.smsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.smsapi.dao.QPTRequestDao;
import com.smsapi.model.RequestModel;
import com.smsapi.util.Convertor;

@Service
public class QPTService {

	@Autowired
	QPTRequestDao qptRequestDao;
	
	public boolean psersist(RequestModel requestmodel) {
		
		qptRequestDao.saveAndFlush(Convertor.getData(requestmodel));

		return true;
	}
	
}
