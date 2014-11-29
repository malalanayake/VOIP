package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CountryDAO;
import org.voip.dao.CustomerDAO;
import org.voip.model.Country;
import org.voip.model.Customer;

@Service
public class CountryService {
	
	@Autowired
	private CountryDAO countryDao;
	
	public Country getCountry(int countryId){
		return countryDao.findOne(countryId);
	}
}
