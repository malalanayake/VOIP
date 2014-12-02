package org.voip.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.voip.dao.CustomerDAO;
import org.voip.model.Customer;
@Service
public class CronService {
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Scheduled(cron="0 0 23 * * ?")
	public void sendMonthlyReportToCustomers(){
		Iterable<Customer> customers = customerDAO.findAll();
		Date date = new Date();
		for(Customer customer: customers){
			//PDF GENERATION MODULE
			sendEmailToCustomer(customer);
		}
		
	}
	public void sendEmailToCustomer(Customer customer){
		//Email Sending Module
		System.out.println("Monthly Billing Report Sent to Customer:"+customer.getName());
	}

}
