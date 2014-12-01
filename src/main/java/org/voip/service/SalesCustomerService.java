package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.SalesCustomerDAO;
import org.voip.model.SalesCustomer;

@Service
public class SalesCustomerService {

	@Autowired
	private SalesCustomerDAO salesCustomerDao;
	
	public void saveSalesCustomer(SalesCustomer salesCustomer){
		salesCustomerDao.save(salesCustomer);
	}
}
