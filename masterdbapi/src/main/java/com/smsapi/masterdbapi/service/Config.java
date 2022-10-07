package com.smsapi.masterdbapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import com.smsapi.masterdbapi.dao.RoleDao;
import com.smsapi.masterdbapi.model.RoleCache;
import com.smsapi.masterdbapi.model.RoleModel;
import com.smsapi.masterdbapi.model.UserCacheModel;
import com.smsapi.masterdbapiconstant.Role;


@Configuration
@ComponentScan("com.smsapi.service")
public class Config {

	@Autowired private RoleDao roledao;
	
	
	@Bean("usercachemaster")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public UserCacheModel usercacheModel() {

		return new UserCacheModel();
	
	}
	
	

	@Bean("rolecache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RoleCache setRolesCache() {

		System.out.println("setRolesCache");
		
		RoleCache rolecache=new RoleCache();

		List<RoleModel> rolelist=roledao.findAll();
		
		if(rolelist==null||rolelist.size()<1) {
			System.out.println("setRolesCache rolelist = null");

			List<RoleModel> roles=new ArrayList<RoleModel>();
			roles.add(RoleModel.builder().role(Role.SYSTEM).build());
			roles.add(RoleModel.builder().role(Role.MASTER).build());
			roles.add(RoleModel.builder().role(Role.ADMIN).build());
			roles.add(RoleModel.builder().role(Role.USER).build());
			roles.add(RoleModel.builder().role(Role.CARRIER).build());
			roles.add(RoleModel.builder().role(Role.MONITOR).build());
			roles.add(RoleModel.builder().role(Role.CUSTOMER).build());
			roles.add(RoleModel.builder().role(Role.SALES).build());
			roles.add(RoleModel.builder().role(Role.BROADCASTER).build());
			roles.add(RoleModel.builder().role(Role.DASHBOARD).build());
			roles.add(RoleModel.builder().role(Role.CREDIT).build());
			roles.add(RoleModel.builder().role(Role.DLT).build());
			roles.add(RoleModel.builder().role(Role.ACCOUNTANT).build());

			roledao.saveAll(roles);
			
			rolelist=roledao.findAll();

		}
		
		if(rolelist!=null) {
			
			
			rolecache.updateRoleCache(rolelist);
			
			
		}else {
			
			System.out.println("setRolesCache after rolelist = null");

		}
	
		
		return rolecache;
	}
	

	
	
	@Bean("smsrolesid")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("rolecache")
	public List<Integer> getSmsrolesid(@Autowired RoleCache rolecache) {
		
		List<Integer> rolelist=new ArrayList<Integer>();

		System.out.println("getSmsrolesid");

		if(rolecache!=null) {
			
			System.out.println("getSmsrolesid rolecache!=null");

			if(rolecache.getRole(Role.MASTER)!=null) {
			rolelist.add(rolecache.getRole(Role.MASTER).getRoleid());	
			}
			
			if(rolecache.getRole(Role.ADMIN)!=null) {
				rolelist.add(rolecache.getRole(Role.ADMIN).getRoleid());	
				}
			
			if(rolecache.getRole(Role.USER)!=null) {
				rolelist.add(rolecache.getRole(Role.USER).getRoleid());	
				}

		}else {
			
			System.out.println("getSmsrolesid rolecache = null");

		}

		
		
		return rolelist;
	}
	
	}
