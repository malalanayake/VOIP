package org.voip.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.MonthlyTrafficDAO;
import org.voip.model.report.MonthlyTraffic;

@Service
public class MonthlyTrafficService {
	@Autowired
	MonthlyTrafficDAO monthlyTrafficDao;
	
	public List<MonthlyTraffic> getAllMonthlyTraffic(Date dateforMonth){
		return monthlyTrafficDao.getMonthlyTraffic(dateforMonth);
	}
}
