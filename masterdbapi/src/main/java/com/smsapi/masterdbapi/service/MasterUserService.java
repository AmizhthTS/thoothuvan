package com.smsapi.masterdbapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.OldPasswordDao;
import com.smsapi.masterdbapi.dao.UserDao;
import com.smsapi.masterdbapi.dao.UserDaoUpdate;
import com.smsapi.masterdbapi.exception.AdminUserNotAvailable;
import com.smsapi.masterdbapi.exception.CreateUserNotAdmin;
import com.smsapi.masterdbapi.exception.PasswordMissMatch;
import com.smsapi.masterdbapi.exception.UsernameExsist;
import com.smsapi.masterdbapi.exception.UsernameNotExsist;
import com.smsapi.masterdbapi.exception.WrongPassword;
import com.smsapi.masterdbapi.model.ChangePasswordModel;
import com.smsapi.masterdbapi.model.RoleCache;
import com.smsapi.masterdbapi.model.UserCacheModel;
import com.smsapi.masterdbapi.model.UserModel;
import com.smsapi.masterdbapiconstant.Role;

@Service
@Transactional
public class MasterUserService {

	@Autowired @Qualifier("rolecache") private RoleCache rolecache;

	@Autowired @Qualifier("smsrolesid") private List<Integer> smsrolesid;
	
	@Autowired	private OldPasswordDao oldpasswordDao;
	
	@Autowired	private UserDao userDao;
	
	@Autowired	private UserDaoUpdate userUpdateDao;
	
	@Autowired @Qualifier("usercachemaster") private UserCacheModel usercachemaster;
	
	public UserModel passwordchange(ChangePasswordModel passwordDTO)   {


		if(!passwordDTO.getChangepassword().equals(passwordDTO.getConfirmpassword())) {
			
			throw new PasswordMissMatch("Passsword Mismatch");
		}
		
		
		UserModel authModel = userDao.findByUsernameEquals(passwordDTO.getUsername());
		
		if (authModel != null) {
			
			if(authModel.getPassword().equals(passwordDTO.getPassword())) {
				
				
				authModel.setPassword(passwordDTO.getChangepassword());
				
				authModel.setPasswordactivation(0);
				
				oldpasswordDao.save(authModel.getOldPasswordModel());
				
				userDao.saveAndFlush(authModel);
				
				return authModel;
				
				
				
			}else {
				
				throw new WrongPassword("Wrong Password");
			}
		}else {
			
			throw new UsernameNotFoundException("Username Not Found : "+passwordDTO.getUsername());
		}
		
				
	}
	
	
	
	public UserModel createUser(UserModel createDTO)   {

		UserModel authModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel!=null) {
			
			throw new UsernameExsist("Username Already Exsists");
		}else {
			
			authModel = userDao.findByUsernameEquals(createDTO.getAdmin());
			if(authModel!=null) {
				
			if(rolecache.getRole(authModel.getRoleid()).getRole().equals(Role.USER)) {
				
				throw new CreateUserNotAdmin("Creating User is not Admin");
			}
			
			
			}else {
				
				if(!rolecache.getRole(createDTO.getRoleid()).getRole().equals(Role.SYSTEM)) {

				throw new AdminUserNotAvailable("Admin User not Available");
				
				}

				
			}
			userDao.saveAndFlush(createDTO);
			
			return userDao.findByUsernameEquals(createDTO.getUsername());

			
			

		}
						
	}


	
	public UserModel editUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(userModel==null) {
			
			throw new UsernameNotExsist("UsernameNotExsists");
			
		}else {
			
			UserModel authModel = userDao.findByUsernameEquals(createDTO.getAdmin());
			if(authModel!=null) {
				
			if(rolecache.getRole(authModel.getRoleid()).getRole().equals("user")) {
				
				throw new CreateUserNotAdmin("Creating User is not Admin");
			}
			
		
			userModel.setRoleid(createDTO.getRoleid());
			
			userModel.setBilltype(createDTO.getBilltype());

			userModel.setProfitpercentage(createDTO.getProfitpercentage());
			
			userModel.setStatus(createDTO.getStatus());
			
			userModel.setModifiedDateTime();
			
			userModel.setPassword(createDTO.getPassword());
			
			
			userUpdateDao.save(userModel);
			
			return userDao.findByUsernameEquals(createDTO.getUsername());

			
			}else {
				
				throw new AdminUserNotAvailable("Admin User not Available");

				
			}

		}
						
	}
	
	
	public UserModel getUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(userModel==null) {
			
			throw new UsernameNotExsist("UsernameNotExsists");
			
		}
		
		return userModel;
						
	}
	
	
	
	public List<UserModel> listUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(userModel==null) {
			
			throw new UsernameNotExsist("UsernameNotExsists");
			
		}else {
			
			List<UserModel> authModel = userDao.findByAdminEquals(createDTO.getUsername());
			
			if(authModel!=null&&authModel.size()>0) {
				
				return authModel;

			
			}else {
				
				throw new AdminUserNotAvailable("Admin User not Available");

				
			}

		}
						
	}

	
	public void loadUserCache() {
		
		usercachemaster.updateUserModel(UUID.randomUUID().toString(), userDao.findByRoleidIn(smsrolesid));
	}



}