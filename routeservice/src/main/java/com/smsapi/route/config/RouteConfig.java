package com.smsapi.route.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.route.service.RouteService;
import com.smsapi.user.service.TokenService;

@Configuration
public class RouteConfig {
	
	
	@Autowired RouteService routeservice;
	
	@Bean("routedefaultcache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("tokenmodel")
	public RouteDefaultModel usercacheModel() {

		RouteDefaultModel rdm=routeservice.getRouteDefault();
		
		return rdm;
	
	}
	
}
