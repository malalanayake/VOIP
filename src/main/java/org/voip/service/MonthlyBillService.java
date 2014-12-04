package org.voip.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CustomerMonthlyDAO;
import org.voip.dao.CustomerMonthlyReportDAOTest;
import org.voip.model.report.CustomerMonthlyTotalReport;

@Service
public class MonthlyBillService {
	@Autowired
	CustomerMonthlyDAO customerMonthlyDao;
	public CustomerMonthlyTotalReport getCustomerBils(Date date, long customerId){
		return customerMonthlyDao.getReportByMonthACustomer(date, customerId);
	}
}
