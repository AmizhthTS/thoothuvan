package com.smsapi.masterdbapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.OldPasswordDao;
import com.smsapi.masterdbapi.dao.UserDao;
import com.smsapi.masterdbapi.model.OldPasswordModel;
import com.smsapi.masterdbapi.model.UserAloneModel;
import com.smsapi.masterdbapi.model.UserModel;


@Service
@Transactional
public class OldPasswordCleanupTask {

	@Autowired
	private OldPasswordDao oldpasswordDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Scheduled(fixedDelay=90000)
	public void doProcess() {
	
		List<OldPasswordModel> passwordlist= oldpasswordDao.findAll();
		
		UserAloneModel userlistbean=new UserAloneModel();
		
		userlistbean.setOldPasswordModel(passwordlist);
		
		List<String> userlist=userlistbean.getUsernamelist();
		
		for(int i=0;i<userlist.size();i++) {
			
			UserModel usermodel =userDao.findByUsernameEquals(userlist.get(i));
			
			if(usermodel.getPasswordactivation()==1) {
				
				oldpasswordDao.deleteByUsername(usermodel.getUsername());
			}
		}
	}
}
