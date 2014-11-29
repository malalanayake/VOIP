package org.voip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.voip.model.Country;
import org.voip.model.Service;
import org.voip.service.GeneralService;

@Controller
@RequestMapping(value="report")
public class ReportController_Ramesh {
	
	@Autowired
	GeneralService generalServices;
	
	@RequestMapping(value="/callrates", method=RequestMethod.GET)
	public String reportForCallRate(Model model){
		List<Country> countries = generalServices.getAllCountries();
		model.addAttribute("countries", countries);
		List<Service> services = generalServices.getAllServices();
		model.addAttribute("services", services);
		
		System.out.println("Forwarding to Call rate report Page");
		return "reports/call-rate";
	}
	
	@RequestMapping(value="/callrates/export", method=RequestMethod.POST)
	public String exportCallRateReport(Model model){
		System.out.println("Value generated");
		return "home";
	}
	
	@RequestMapping(value="monthlybills",method= RequestMethod.GET)
	public String reportForMonthlyBill(Model model){
		System.out.println("Forwading to Monthly billing report page");
		return "reports/monthly-bill";
	}
	
	@RequestMapping(value="monthlytraffic",method = RequestMethod.GET)
	public String reportForMonthlyTraffic(Model model){
		System.out.println("Forwarding to Monthly Traffic");
		return "reports/monthly-traffic";
	}
	
	@RequestMapping(value="salesrepcommision",method=RequestMethod.GET)
	public String reportForSalesRepCommision(Model model){
		System.out.println("Forwarding to Sales representative commision page");
		return "reports/sales-rep-commision";
	}
	
}

