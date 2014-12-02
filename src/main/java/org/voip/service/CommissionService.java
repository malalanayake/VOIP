package org.voip.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.SalesCommissionDAO;
import org.voip.model.report.SalesCommissionTotalReport;

@Service
public class CommissionService {
	@Autowired
	SalesCommissionDAO salesCommissionDao;
	public SalesCommissionTotalReport getTotalReport(Date dateforMonth, long salesRepCode){
		return salesCommissionDao.getSalesReport(dateforMonth, salesRepCode);
	}
}
