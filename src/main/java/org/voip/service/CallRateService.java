package org.voip.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CallRateDAO;
import org.voip.model.CallRate;

@Service
public class CallRateService {
	@Autowired
	CallRateDAO callRateDao;
	public List<CallRate> gellCurrentCallRates(int countryServiceID, Date date){
		return callRateDao.findLatestRate(countryServiceID, date);
	}
}
