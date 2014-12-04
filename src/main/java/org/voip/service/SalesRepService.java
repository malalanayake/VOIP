package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.SalesRepDAO;
import org.voip.model.SalesRep;

@Service
public class SalesRepService {
	@Autowired
	SalesRepDAO salesRepDao;
	
	public SalesRep getSalesRepById(long phoneNumber){
		return salesRepDao.findOne(phoneNumber);
	}
	
	public SalesRep createSalesRep(SalesRep salesRep){
		return salesRepDao.save(salesRep);
	}
}
