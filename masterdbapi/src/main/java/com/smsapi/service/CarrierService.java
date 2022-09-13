package com.smsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.dao.CarrierDao;
import com.smsapi.exception.CarriernameExsist;
import com.smsapi.model.CarrierModel;


@Service
@Transactional
public class CarrierService {

	@Autowired
	private CarrierDao carrierDao;
	
	@Autowired
	private CarrierDao carrierDaoUpdate;
	
	
	public CarrierModel createCarrier(CarrierModel createDTO){

		CarrierModel userModel = carrierDao.findByCarriernameEquals(createDTO.getCarriername());

		if(userModel!=null) {
		
			throw new CarriernameExsist("CarriernameExsist");
		}
		
		carrierDao.saveAndFlush(createDTO);
		
		return carrierDao.findByCarriernameEquals(createDTO.getCarriername());
						
	}
	
	public CarrierModel getCarrier(CarrierModel createDTO){

		CarrierModel userModel = carrierDao.findByCarriernameEquals(createDTO.getCarriername());
		
		if(userModel==null) {
			
			throw new CarriernameExsist("CarriernameExsist");
			
		}
		
		return userModel;
						
	}

	public List<CarrierModel> listCarrier() {
		
			return carrierDao.findAll();
	}

	public CarrierModel editUser(CarrierModel createDTO) {

		return carrierDaoUpdate.saveAndFlush(createDTO);
	}
	
	

}