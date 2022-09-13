package com.smsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.constant.Role;
import com.smsapi.dao.CreditDao;
import com.smsapi.dao.TopupDao;
import com.smsapi.dao.TopupLogDao;
import com.smsapi.dao.UserDao;
import com.smsapi.exception.AdminNameMissMatched;
import com.smsapi.exception.BilltypeNotPrepaid;
import com.smsapi.exception.UserNotRegisteredForCredit;
import com.smsapi.exception.UsernameNotExsist;
import com.smsapi.model.CreditModel;
import com.smsapi.model.RoleCache;
import com.smsapi.model.TopupHistoryModel;
import com.smsapi.model.TopupLogModel;
import com.smsapi.model.UserAloneModel;
import com.smsapi.model.UserModel;

@Service
@Transactional
public class CreditService {
	
	@Autowired @Qualifier("rolecache") private RoleCache rolecache;

	@Autowired private TopupDao topupDao;

	@Autowired private CreditDao creditDao;
	
	@Autowired private UserDao userDao;
	
	@Autowired private TopupLogDao topuplogDao;
	
	public List<String> getUserList(){
		
		List<CreditModel> credituserlist=creditDao.findAll();
		
		UserAloneModel bean=new UserAloneModel();
		
		bean.setUsers(credituserlist);
		
		return bean.getUsernamelist();
	}
	
	public CreditModel addUser(CreditModel createDTO)   {

		UserModel authModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel==null) {
			
			throw new UsernameNotExsist("Username Not Exsist");
			
		}else {
			
	
			if(!authModel.getBilltype().equals("prepaid")) {
				
				throw new BilltypeNotPrepaid("User not prepaid Bill Type");
			}
			
			if(!authModel.getAdmin().equals(createDTO.getAdmin())) {
				
				throw new AdminNameMissMatched("Admin Name Miss Matched");
			}
			
			
			CreditModel credituser = creditDao.findByUsernameEquals(createDTO.getAdmin());
			
			if(credituser==null) {
				
				UserModel admin=userDao.findByUsernameEquals(createDTO.getAdmin());
				
				
				if(admin==null||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.MASTER)||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.ADMIN)||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.USER)) {
					
					
					throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");

				}
			}

			creditDao.saveAndFlush(createDTO);
			
			return creditDao.findByUsernameEquals(createDTO.getUsername());

			
			

		}
						
	}
	
	
	
	public List<TopupHistoryModel> topupCredit(TopupHistoryModel createDTO)   {

		CreditModel authModel = creditDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel==null) {
			
			throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");
			
		}else {
			
	
			
			if(!authModel.getAdmin().equals(createDTO.getAdmin())) {
				
				throw new AdminNameMissMatched("Admin Name Miss Matched");
			}
			
			
			
			authModel = creditDao.findByUsernameEquals(createDTO.getAdmin());
			
			if(authModel==null) {
				
				UserModel admin=userDao.findByUsernameEquals(createDTO.getAdmin());
				
				
				if(admin==null||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.MASTER)||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.ADMIN)||rolecache.getRole(admin.getRoleid()).getRole().equals(Role.USER)) {
					
					throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");

				}
			}
			
			topupDao.saveAndFlush(createDTO);
			
			return topupDao.findByAdminEquals(createDTO.getAdmin());

			
			

		}
						
	}

	
	
	
	public List<TopupHistoryModel> getTopuphistory()   {

		return topupDao.findAll();
	}
						
	public List<TopupLogModel> getTopuplog()   {

		return topuplogDao.findAll();
	}


}