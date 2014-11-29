package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CustomerDAO;
import org.voip.model.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerDAO customerDAO;
	
	public Customer getCustomerById(long phoneNumber){
		return customerDAO.findOne(phoneNumber);
	}
	
	public void saveCustomer(Customer customer){
		customerDAO.save(customer);
	}
}
