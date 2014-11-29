package org.voip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="report")
public class ReportController_Ramesh {
	
	@RequestMapping(value="/callrates", method=RequestMethod.GET)
	public String reportForCallRate(Model model){
		System.out.println("Forwarding to Call rate report Page");
		return "reports/call-rate";
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

