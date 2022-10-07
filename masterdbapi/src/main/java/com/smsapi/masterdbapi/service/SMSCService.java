package com.smsapi.masterdbapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.CarrierDao;
import com.smsapi.masterdbapi.dao.SMSCDao;
import com.smsapi.masterdbapi.dao.SMSCDaoUpdate;
import com.smsapi.masterdbapi.exception.SmscidExsist;
import com.smsapi.masterdbapi.exception.SmscidNotExsist;
import com.smsapi.masterdbapi.model.CarrierModel;
import com.smsapi.masterdbapi.model.SMSCModel;


@Service
@Transactional
public class SMSCService {

	@Autowired
	private CarrierDao carrierDao;
	
	@Autowired
	private SMSCDao smscDao;
	
	
	@Autowired
	private SMSCDaoUpdate smscDaoupdsate;
	
	
	
	public SMSCModel createSmsc(SMSCModel createDTO){

		SMSCModel userModel = smscDao.findBySmscidEquals(createDTO.getSmscid());

		if(userModel!=null) {
		
			throw new SmscidExsist("SmscidExsist");
		}
		
		smscDao.saveAndFlush(createDTO);
		
		return smscDao.findBySmscidEquals(createDTO.getSmscid());
						
	}
	
	public SMSCModel getSMSC(SMSCModel createDTO){
		
		SMSCModel userModel = smscDao.findBySmscidEquals(createDTO.getSmscid());

		if(userModel==null) {
			
			throw new SmscidNotExsist("SmscidNotExsist");
		}

		CarrierModel carrierModel = carrierDao.findByCarriernameEquals(userModel.getCarriername());
		
		userModel.setTelemarketerid(carrierModel.getTelemarketerid());;
		
		return userModel;
						
	}

	public SMSCModel editSmsc(SMSCModel createDTO) {
		
		return smscDaoupdsate.save(createDTO);
	}

	public List<SMSCModel> listSMSC() {

		return smscDao.findAll();
	
	}
	
	

}