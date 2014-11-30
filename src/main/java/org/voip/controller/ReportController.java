package org.voip.controller;

import java.beans.PropertyEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.voip.model.Country;
import org.voip.model.Customer;
import org.voip.model.SalesRep;
import org.voip.model.Service;
import org.voip.service.CountryService;
import org.voip.service.CustomerService;
import org.voip.service.SalesRepService;
import org.voip.service.ServiceService;
import org.voip.service.report.CallRateReport;
import org.voip.service.report.MonthlyBillReport;
import org.voip.service.report.ReportManager;
import org.voip.service.report.SalesCommissionReport;

/**
 * Report controller
 * 
 * @author malalanayake
 *
 */
@Controller
@RequestMapping("/report/")
public class ReportController {

	@Autowired
	ReportManager reportManager;
	@Autowired
	ServiceService serviceService;
	@Autowired
	CountryService countryService;
	@Autowired
	CustomerService customerService;
	@Autowired
	SalesRepService salesRepService;

	@RequestMapping(method = RequestMethod.POST, value = "call-rates/pdf")
	public ModelAndView generateCallRatePdfReport(ModelAndView modelAndView,
			@RequestParam("country") int countryCode,
			@RequestParam("service") int serviceCode) {
		Country country = countryService.getCountry(countryCode);
		Service service = serviceService.getService(serviceCode);
		CallRateReport callRateReport = new CallRateReport(country, service);
		modelAndView = reportManager.getReportView(callRateReport);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "monthly-bill/pdf")
	public ModelAndView generateMonthlyBillPdfReport(ModelAndView modelAndView, 
			@RequestParam("date")String sDate, @RequestParam("customer") long customerID, BindingResult result) {
		Customer customer = customerService.getCustomerById(customerID);
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		MonthlyBillReport monthlyBillReport = new MonthlyBillReport(date,customer);
	    modelAndView = reportManager.getReportView(monthlyBillReport);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "sales-commission/pdf")
	public ModelAndView generateSalesCommissionPdfReport(
			ModelAndView modelAndView,@RequestParam("salesRep") int salesRepID,@RequestParam("date") String sDate) {
		SalesRep salesRep = salesRepService.getSalesRepById(salesRepID);
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SalesCommissionReport monthlyBillReport = new SalesCommissionReport(
				salesRep, date);
		modelAndView = reportManager.getReportView(monthlyBillReport);

		return modelAndView;
	}

}
