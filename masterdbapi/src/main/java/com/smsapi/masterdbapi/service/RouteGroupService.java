package com.smsapi.masterdbapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.RouteGroupDao;
import com.smsapi.masterdbapi.exception.GroupnameExsist;
import com.smsapi.masterdbapi.exception.GroupnameNotExsist;
import com.smsapi.masterdbapi.model.RouteGroupModel;


@Service
@Transactional
public class RouteGroupService {

	@Autowired
	private RouteGroupDao routegroupdao;
	
	
	
	public RouteGroupModel createRouteGroupModel(RouteGroupModel rgm){

		RouteGroupModel routegroupmodel = routegroupdao.findByGroupnameEquals(rgm.getGroupname());

		if(routegroupmodel!=null) {
		
			throw new GroupnameExsist("GroupnameExsist");
		}
		
		routegroupdao.saveAndFlush(rgm);
		
		return routegroupdao.findByGroupnameEquals(rgm.getGroupname());
						
	}
	
	public RouteGroupModel getRouteGroupModel(RouteGroupModel rgm){

		RouteGroupModel routegroupmodel = routegroupdao.findByGroupnameEquals(rgm.getGroupname());
		
		if(routegroupmodel==null) {
			
			throw new GroupnameNotExsist("GroupnameNotExsist");
			
		}
		
		return routegroupmodel;
						
	}

	public List<RouteGroupModel> listCarrier() {
		
			return routegroupdao.findAll();
	}

	public RouteGroupModel editUser(RouteGroupModel createDTO) {

		return routegroupdao.saveAndFlush(createDTO);
	}
	
	

}