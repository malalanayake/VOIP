package org.voip.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.voip.model.CountryService;
import org.voip.model.Customer;
import org.voip.model.SalesCustomer;
import org.voip.model.SalesRep;
import org.voip.service.CountryServiceService;
import org.voip.service.CustomerService;
import org.voip.service.GeneralService;
import org.voip.service.SalesCustomerService;
import org.voip.service.SalesRepService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private GeneralService generalService;
	
	@Autowired
	CountryServiceService countryServiceService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SalesCustomerService salesCustomerService;
	
	@Autowired
	SalesRepService salesRepService;
	
	@InitBinder
	public void initBindet(WebDataBinder binder){
		binder.registerCustomEditor(CountryService.class, new PropertyEditorSupport(){
			@Override
			public void setAsText(String val){
				CountryService countryService= countryServiceService.getCountryService(Integer.parseInt(val));
				setValue(countryService);
			}
		});
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String cutomerHome(Model model){
		System.out.println("Passing to customer");
		model.addAttribute("customer", new Customer());
		model.addAttribute("salesRepList", generalService.getAllSalesRep());
		model.addAttribute("countryServiceList", generalService.getAllCountryService());
		return "customers/customers";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCustomer(Model model, @ModelAttribute("customer") Customer customer, 
			@RequestParam("commision") float commision,	@RequestParam("salesRep") int salesRepId, BindingResult result){
		System.out.println("Customer Adding in process");
		//customer.
		
		SalesCustomer salesCustomer = new SalesCustomer();
		SalesRep salesRep = salesRepService.getSalesRepById(salesRepId);
		salesCustomer.setCustomer(customer);
		salesCustomer.setSalesRep(salesRep);
		salesCustomer.setCommission(commision);
		
		
		customerService.saveCustomer(customer);
		salesCustomerService.saveSalesCustomer(salesCustomer);
		return "redirect:list";
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
