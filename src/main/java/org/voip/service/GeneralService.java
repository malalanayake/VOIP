package org.voip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.voip.dao.CountryDAO;
import org.voip.dao.CountryServiceDAO;
import org.voip.dao.CustomerDAO;
import org.voip.dao.ServiceDAO;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Customer;
import org.voip.model.Service;

@org.springframework.stereotype.Service
public class GeneralService {
	
	@Autowired
	CountryDAO countryDao;
	
	@Autowired
	ServiceDAO serviceDao;
	
	@Autowired
	CustomerDAO customerDao;
	
	@Autowired
	CountryServiceDAO countryServiceDAO;
	
	public List<Country> getAllCountries(){
		List<Country> result;
		result = (List<Country>) countryDao.findAll();
		return result;
	}
	
	public List<Service> getAllServices(){
		List<Service> result;
		result = (List<Service>)serviceDao.findAll();
		return result;
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers;
		customers = (List<Customer>) customerDao.findAll();
		return customers;
	}
	
	public List<org.voip.model.CountryService> getAllCountryService(){
		List<org.voip.model.CountryService> countryServices ;
		countryServices =  (List<CountryService>) countryServiceDAO.findAll();
		return countryServices;
	}
}
