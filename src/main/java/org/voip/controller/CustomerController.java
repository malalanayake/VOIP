package org.voip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.voip.dao.CustomerDAO;
import org.voip.model.Customer;
import org.voip.service.GeneralService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private GeneralService generalService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String cutomerHome(Model model){
		System.out.println("Passing to customer");
		model.addAttribute("customer", new Customer());
		return "customers/customers";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCustomer(Model model, Customer customer){
		System.out.println("Customer Adding in process");
		if(customer !=null )
		{
			//customerDAO.save(customer);
			System.out.println(customer.getName());
			System.out.println(customer.getPhoneNumber());
		}
		return "home";
	}
	
	@RequestMapping(value="bulkUpdate", method= RequestMethod.GET)
	public String bulkUpdatePage(Model model){
		System.out.println("Bulk Update mode");
		return "customers/bulkUpdate";
	}
	
	@RequestMapping(value="bulkUpdate", method= RequestMethod.POST)
	public String bulkUpdate(Model model){
		System.out.println("Bulk Updating value");
		return "customers/bulkUpdate";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listCustomers(Model model){
		model.addAttribute("customerList", generalService.getAllCustomers());
		return "customers/CustomerList";
	}
}
