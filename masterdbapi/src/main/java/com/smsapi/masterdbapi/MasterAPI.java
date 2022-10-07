package com.smsapi.masterdbapi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.smsapi.masterdbapi.dao.RoleDao;
import com.smsapi.masterdbapi.exception.CarriernameNotExsist;
import com.smsapi.masterdbapi.exception.GroupnameNotExsist;
import com.smsapi.masterdbapi.exception.RedisExsist;
import com.smsapi.masterdbapi.exception.RouteNotExsist;
import com.smsapi.masterdbapi.exception.SmscidNotExsist;
import com.smsapi.masterdbapi.model.CarrierModel;
import com.smsapi.masterdbapi.model.RedisPropertiesModel;
import com.smsapi.masterdbapi.model.RoleCache;
import com.smsapi.masterdbapi.model.RouteDefaultModel;
import com.smsapi.masterdbapi.model.RouteGroupModel;
import com.smsapi.masterdbapi.model.SMSCModel;
import com.smsapi.masterdbapi.model.SMSCName;
import com.smsapi.masterdbapi.model.UserModel;
import com.smsapi.masterdbapi.service.CarrierService;
import com.smsapi.masterdbapi.service.MasterUserService;
import com.smsapi.masterdbapi.service.RedisService;
import com.smsapi.masterdbapi.service.RouteGroupService;
import com.smsapi.masterdbapi.service.RouteService;
import com.smsapi.masterdbapi.service.SMSCService;
import com.smsapi.masterdbapiconstant.BillType;
import com.smsapi.masterdbapiconstant.Role;


@SpringBootApplication(exclude = {
	    RedisAutoConfiguration.class
	})
@EnableScheduling
@ComponentScan({"com.smsapi.masterdbapi.*","com.smsapi.masterdbapi.*.*"})
public class MasterAPI {

	@Autowired MasterUserService masteruserservice;
	
	@Autowired RoleCache rolecache;
	
	@Autowired RouteGroupService routegroupservice;
	
	@Autowired RouteService routeservice;
	
	@Autowired RoleDao roledao;
	
	@Autowired CarrierService carrierservice;
	
	@Autowired SMSCService smscservice;

	@Autowired RedisService redisservice; 

	public static void main(String[] args) {
		
		
		System.setProperty("spring.main.allow-bean-definition-overriding","true");
	    System.setProperty("server.servlet.context-path", "/sms-api");

		System.setProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults","false");
		System.setProperty("spring.jpa.open-in-view","false");
		System.setProperty("smsapi.username","root");
		System.setProperty("smsapi.password","root123");
		
		System.setProperty("spring.main.lazy-initialization", "false");
	
		SpringApplication.run(MasterAPI.class, args);
		
	}
	
	@PostConstruct
	public void init() {
	
	
		masteruserservice.loadUserCache();
		
		addUsers();
		
		addCarrier();
		
		addSMSC();

		addRouteGroup();
		
		addRouteDefault();
		
		addRedis();
	}		
		

	private void addRedis() {
	
		add("rq1","rq1",6379,1);
		
		add("rq2","rq2",6379,1);

		
	}

	private void add(String name, String hostname, int port, int db) {
		
		RedisPropertiesModel rqp=RedisPropertiesModel.builder().name(name).hostname(hostname).db(db).port(port).build();
				
						
		try {
			
			redisservice.createRedisQueuePropertyModel(rqp);
			
		}catch(RedisExsist e) {
			
		}
	
	}

	private void addRouteDefault() {
		
		RouteDefaultModel rdm =RouteDefaultModel.builder().id(1).intlgroupname("defaultgroup").otpgroupname("defaultgroup").promotionalgroupname("defaultgroup").transactionalgroupname("defaultgroup").build();
		
		try {
			routeservice.getRouteDefaultModel(rdm);
		}catch(RouteNotExsist e) {
			
			routeservice.createRouteDefaultModel(rdm);
			
		}
	}

	private void addRouteGroup() {
		
		List<SMSCName> smslist=new ArrayList<SMSCName>();
		
		smslist.add(SMSCName.builder().smscid("default").build());
		
		RouteGroupModel rgm=RouteGroupModel.builder().groupname("defaultgroup").intl(1).otp(1).transactional(1).promotional(1).smscidlist(smslist).build();
		
		try {
		routegroupservice.getRouteGroupModel(rgm);
		}catch(GroupnameNotExsist e) {
			
			routegroupservice.createRouteGroupModel(rgm);
		}
	}

	private void addSMSC() {
		
		SMSCModel smsmodel=SMSCModel.builder().smscid("default").carriername("internal").intl(1).otp(1).transactional(1).promotional(1).username("smppclient1").password("password").cost(1.0).ip("simulator").port(2775).build();
		
		try {
		smscservice.getSMSC(smsmodel);
		}catch(SmscidNotExsist e) {
			
			smscservice.createSmsc(smsmodel);

		}
	}

	private void addCarrier() {
				
		CarrierModel carrier=CarrierModel.builder().carriername("internal").build();
		try {
			carrierservice.getCarrier(carrier);

		}catch(CarriernameNotExsist e) {
		
			carrierservice.createCarrier(carrier);

		}
	}

		private void addUsers() {
			
			addUser("system","password","system",rolecache.getRole(Role.SYSTEM).getRoleid());
			
			addUser("accountant","password","system",rolecache.getRole(Role.ACCOUNTANT).getRoleid());
			
			addUser("dlt","password","system",rolecache.getRole(Role.DLT).getRoleid());
			
			addUser("credit","password","system",rolecache.getRole(Role.CREDIT).getRoleid());	
			
			addUser("monitor","password","system",rolecache.getRole(Role.MONITOR).getRoleid());
			
			addUser("dashboard","password","system",rolecache.getRole(Role.DASHBOARD).getRoleid());
			
			addUser("master","password","accountant",rolecache.getRole(Role.MASTER).getRoleid());

			addUser("admin","password","master",rolecache.getRole(Role.ADMIN).getRoleid());

			addUser("user","password","admin",rolecache.getRole(Role.USER).getRoleid());
		
		}

		private void addUser(String username,String password,String admin,int roleid) {

		UserModel usermodel=UserModel.builder().status(1).username(username).billtype(BillType.POSTPAID).password(password).admin(admin).roleid(roleid).balanceavailable(1).build();
		
		try {
			masteruserservice.getUser(usermodel);
		}catch(Exception e) {
			
			masteruserservice.createUser(usermodel);

		}
		
		}
	
	
	
	
	
}