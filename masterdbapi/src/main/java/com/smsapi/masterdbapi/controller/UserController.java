package com.smsapi.masterdbapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.masterdbapi.service.RouteService;

@RestController
@RequestMapping(value = "/route")
public class UserController {

	@Autowired private RouteService routeservice;

	@PostMapping("/getroutedefault")
	public ResponseEntity<?> getroutedefault() {

		return ResponseEntity.ok().body(routeservice.getRouteDefaultModel(RouteDefaultModel.builder().id(1).build()));
	
	}
	
	
		
	
}