package com.smsapi.masterdbapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.masterdbapi.dao.CreditDao;
import com.smsapi.masterdbapi.dao.TopupDao;
import com.smsapi.masterdbapi.dao.TopupLogDao;
import com.smsapi.masterdbapi.model.CreditBalanceModel;
import com.smsapi.masterdbapi.model.CreditModel;
import com.smsapi.masterdbapi.model.TopupHistoryModel;
import com.smsapi.masterdbapi.model.TopupLogModel;
import com.smsapi.masterdbapiconstant.TopupStatus;


@Service
@Transactional
public class TopupTask {

	@Autowired
	private TopupDao topupDao;
	
	@Autowired
	private CreditDao creditDao;
	
	@Autowired
	private TopupLogDao topuptaskDaoUpdate;
	
	@Scheduled(fixedDelay=1000)
	public void doProcess() {
	    
		List<TopupHistoryModel> topuplist=topupDao.findByStatusEquals(TopupStatus.INITIATED);


		
		for(int i=0;i<topuplist.size();i++) {
			
			TopupHistoryModel topup=topuplist.get(i);			
			topup.setModifiedDateTime();
			TopupLogModel entity=new TopupLogModel();
			entity.setTopupmodel(topup);
			
			CreditModel topupuser=creditDao.findByUsernameEquals(topup.getUsername());

			CreditBalanceModel before=new CreditBalanceModel();
			before.setBalance(topupuser.getTotalbalance());
			before.setType("before");
			
			topupuser.setTotalbalance(topupuser.getTotalbalance()+topup.getTopupvalue());
			
			CreditBalanceModel after=new CreditBalanceModel();
			after.setBalance(topupuser.getTotalbalance());
			after.setType("after");
			entity.setBefore(before);
			entity.setAfter(after);
			topup.setStatus(TopupStatus.DONE);
			topuptaskDaoUpdate.save(entity);
			
		}
	}
}
