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

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
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
		List<Customer> customerList = new ArrayList<Customer>();
		for(int i = 1; i <10;i++)
		{
			Customer c = new Customer();
			c.setName("Name"+i);
			c.setPhoneNumber(i*111111111);
			c.setZip(33+i);
			c.setCity("City"+i);
			c.setStreet("Street"+i);
			customerList.add(c);
		}
		model.addAttribute("customerList", customerList);
		return "customers/CustomerList";
	}
}
