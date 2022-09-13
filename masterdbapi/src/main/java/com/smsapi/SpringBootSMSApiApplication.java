package com.smsapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.smsapi.constant.BillType;
import com.smsapi.constant.Role;
import com.smsapi.dao.RoleDao;
import com.smsapi.model.RoleCache;
import com.smsapi.model.UserModel;
import com.smsapi.service.UserService;


@SpringBootApplication(exclude = {
	    RedisAutoConfiguration.class
	})
@EnableScheduling
public class SpringBootSMSApiApplication {

	@Autowired UserService userservice;
	
	@Autowired RoleCache rolecache;
	
	@Autowired RoleDao roledao;
	
	

	public static void main(String[] args) {
		
		
		System.setProperty("spring.main.allow-bean-definition-overriding","true");
	    System.setProperty("server.servlet.context-path", "/sms-api");

		System.setProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults","false");
		System.setProperty("spring.jpa.open-in-view","false");
		System.setProperty("smsapi.username","root");
		System.setProperty("smsapi.password","root123");
		
		System.setProperty("spring.main.lazy-initialization", "false");
	
		SpringApplication.run(SpringBootSMSApiApplication.class, args);
		
	}
	
	@PostConstruct
	public void init() {
	
	
		userservice.loadUserCache();
		
		addUsers();
		
		

		
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

		UserModel usermodel=UserModel.builder().status(1).username(username).billtype(BillType.POSTPAID).password(password).admin(admin).roleid(roleid).build();
		
		try {
			userservice.getUser(usermodel);
		}catch(Exception e) {
			
			userservice.createUser(usermodel);

		}
		
		}
	
	
	
	
	
}