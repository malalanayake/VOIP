package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CountryServiceDAO;
import org.voip.model.CountryService;

@Service
public class CountryServiceService {

	@Autowired
	CountryServiceDAO countryServiceDAO;
	
	public CountryService getCountryService(int id){
		CountryService cService = countryServiceDAO.findOne((long) id);
		return cService;
	}
}
