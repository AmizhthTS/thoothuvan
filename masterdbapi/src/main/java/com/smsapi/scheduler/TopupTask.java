package com.smsapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.constant.TopupStatus;
import com.smsapi.dao.CreditDao;
import com.smsapi.dao.TopupDao;
import com.smsapi.dao.TopupLogDao;
import com.smsapi.model.CreditBalanceModel;
import com.smsapi.model.CreditModel;
import com.smsapi.model.TopupHistoryModel;
import com.smsapi.model.TopupLogModel;


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
