package com.smsapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.dao.OldPasswordDao;
import com.smsapi.dao.UserDao;
import com.smsapi.dao.UserDaoUpdate;
import com.smsapi.jwt.JwtTokenUtil;
import com.smsapi.model.PasswordAloneModel;
import com.smsapi.model.UserModel;

@Service
@Transactional
public class AuthService {

	@Autowired
	private OldPasswordDao oldpasswordDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoUpdate userUpdateDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@SuppressWarnings("unused")
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@SuppressWarnings("unused")
	public UserModel login(UserModel userDTO)  {
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

		UserModel authModel = userDao.findByUsernameEquals(userDTO.getUsername());
		if (authModel != null) {
			
			boolean passwordmatch=authModel.getPassword().equals(userDTO.getPassword());
			
			if(!passwordmatch) {
				
				if(authModel.getPasswordactivation()==0) {
					
					PasswordAloneModel passwordlist=new PasswordAloneModel();
					
					passwordlist.setPasswordlist(oldpasswordDao.findByUsernameEquals(authModel.getUsername()));
					passwordmatch=passwordlist.getPasswordlist().contains(userDTO.getPassword());
				}
			}
			if(passwordmatch) {
				
				
				if(authModel.getStatus()==1) {
				

					UserModel retAuthDTO =authModel;
					
					retAuthDTO.setToken(jwtTokenUtil.generateToken(authModel.getUsername()));
					retAuthDTO.setStatus(1);
					retAuthDTO.setId(authModel.getId());
				
					if(retAuthDTO.getPasswordactivation()==0) {
						
						retAuthDTO.setPasswordactivation(1);
						
						userUpdateDao.save(retAuthDTO);
					}
					return retAuthDTO;
				
				}else {
					
					throw new DisabledException("Account Disabled");
					
				}	
				
			}else {
				
				throw new BadCredentialsException("Password Not Matched ");
			}
		}else {
			
			throw new UsernameNotFoundException("Username Not Found : "+userDTO.getUsername());
		}
		
				
	}


}