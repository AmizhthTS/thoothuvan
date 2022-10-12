package com.smsapi.hapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication(exclude = {
	    DataSourceAutoConfiguration.class, 
	    DataSourceTransactionManagerAutoConfiguration.class, 
	    HibernateJpaAutoConfiguration.class,
	    RedisAutoConfiguration.class,
	    SecurityAutoConfiguration.class
	    })
@Configuration
@EnableScheduling
@ComponentScan({"com.smsapi.user.*","com.smsapi.user.*.*","com.smsapi.route.*","com.smsapi.route.*.*","com.redisapi.*","com.redisapi.*.*","com.smsapi.hapi.*","com.smsapi.hapi.*.*"})
public class HAPI {

	public static void main(String[] args) {
		
		String domainname="masterapilb";
	    System.setProperty("server.servlet.context-path", "/sms-api");
		System.setProperty("smsapi.username","root");
		System.setProperty("smsapi.password","root123");
		
		System.setProperty("auth.login.url","http://"+domainname+":9092/sms-api/auth/login");
		System.setProperty("credit.userlist.url","http://"+domainname+":9092/sms-api/credit/userlist");
		

		SpringApplication.run(HAPI.class, args);
		
	}

}
