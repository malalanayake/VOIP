package org.voip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.voip.dao.CustomerDAO;
import org.voip.model.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
//	@Autowired
//	private CustomerDAO customerDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String cutomerHome(Model model){
		System.out.println("Passing to customer");
		model.addAttribute("customer", new Customer());
		return "customers";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCustomer(Model model, Customer customer){
		System.out.println("Customer Adding in process");
		if(customer !=null )
		{
			System.out.println(customer.getName());
			System.out.println(customer.getPhoneNumber());
		}
		return "home";
	}
}
