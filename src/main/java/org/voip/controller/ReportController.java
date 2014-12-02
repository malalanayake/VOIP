package org.voip.controller;

import java.beans.PropertyEditor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.voip.service.CountryServiceService;
import org.voip.service.CustomerService;
import org.voip.service.SalesRepService;
import org.voip.service.ServiceService;
import org.voip.service.report.CallRateExcelReport;
import org.voip.service.report.CallRateReport;
import org.voip.service.report.MonthlyBillReport;
import org.voip.service.report.MonthlyTrafficReport;
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
	
	@Autowired
	CountryServiceService countryServiceService;

	@RequestMapping(method = RequestMethod.POST, value = "call-rates/pdf")
	public ModelAndView generateCallRatePdfReport(ModelAndView modelAndView,
			@RequestParam("countryService") int countryServiceCode, @RequestParam("date") String sDate) {
		org.voip.model.CountryService countrySer = countryServiceService.getCountryService(countryServiceCode);
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sDate);
		CallRateReport callRateReport = new CallRateReport(countrySer.getCountry(), countrySer.getService(), date);
		modelAndView = reportManager.getReportView(callRateReport);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "call-rates/excel")
	public void generateCallRateExcelReport(HttpServletResponse res,
			@RequestParam("country") int countryCode,@RequestParam("service") int serviceCode, @RequestParam("date") String sDate) {
		
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sDate);
		Country country = countryService.getCountry(countryCode);
		Service service = serviceService.getService(serviceCode);
		CallRateExcelReport callRateExcelReport = new CallRateExcelReport(service, country, date);
		ModelAndView mav = reportManager.getReportView(callRateExcelReport);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.ms-excel"));
	    String name= String.format("%s_%s", country.getName(),service.getName());
	    res.setHeader("Content-disposition", "attachment; filename=" + name+".xls");
	    HSSFWorkbook book = (HSSFWorkbook)mav.getModel().get("excelBook");
	    try {
			book.write(res.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	@RequestMapping(method = RequestMethod.POST, value = "monthly-traffic/excel")
	public void generateMonthlyTrafficExelReport(@RequestParam("date")String sDate,HttpServletRequest req,
			HttpServletResponse res) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		MonthlyTrafficReport monthlyBillReport = new MonthlyTrafficReport(date);
	    ModelAndView mav = reportManager.getReportView(monthlyBillReport);
	    
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.ms-excel"));
	    res.setHeader("Content-disposition", "attachment; filename=" + date.toString()+".xls");
	    HSSFWorkbook book = (HSSFWorkbook)mav.getModel().get("excelBook");
	    try {
			book.write(res.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
