package com.smsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smsapi.dao.UserDao;
import com.smsapi.model.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userdao;

	private String username;

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = null;
	
		userModel = userdao.findByUsernameEquals(username);

		if (userModel !=null ) {
			return userModel.getUser();
		} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}