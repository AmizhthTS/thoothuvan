package com.smsapi.masterdbapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.RouteDefaultDao;
import com.smsapi.masterdbapi.exception.GroupnameNotExsist;
import com.smsapi.masterdbapi.exception.RouteExsist;
import com.smsapi.masterdbapi.exception.RouteNotExsist;
import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.masterdbapi.model.RouteGroupModel;


@Service
@Transactional
public class RouteService {

	@Autowired
	private RouteDefaultDao routedefaultdao;
	
	
	
	public RouteDefaultModel createRouteDefaultModel(RouteDefaultModel rdm){

		RouteDefaultModel routedefaultmodel = routedefaultdao.findByIdEquals(rdm.getId());

		if(routedefaultmodel!=null) {
		
			throw new RouteExsist("RouteExsist");
		}
		
		routedefaultdao.saveAndFlush(rdm);
		
		return routedefaultdao.findByIdEquals(rdm.getId());
						
	}
	
	public RouteDefaultModel getRouteDefaultModel(RouteDefaultModel rdm){

		RouteDefaultModel routedefaultmodel = routedefaultdao.findByIdEquals(rdm.getId());
		
		if(routedefaultmodel==null) {
			
			throw new RouteNotExsist("RouteNotExsist");
			
		}
		
		return routedefaultmodel;
						
	}

	public List<RouteDefaultModel> listRouteDefault() {
		
			return routedefaultdao.findAll();
	}

	public RouteDefaultModel editRouteDefault(RouteDefaultModel createDTO) {

		return routedefaultdao.saveAndFlush(createDTO);
	}
	
	

}