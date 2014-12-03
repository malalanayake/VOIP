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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.voip.model.CallRate;
import org.voip.model.Country;
import org.voip.model.Customer;
import org.voip.model.SalesRep;
import org.voip.model.Service;
import org.voip.model.report.CustomerMonthly;
import org.voip.model.report.MonthlyTraffic;
import org.voip.model.report.SalesCommission;
import org.voip.model.report.SalesCommissionTotalReport;
import org.voip.service.CallRateService;
import org.voip.service.CommissionService;
import org.voip.service.CountryService;
import org.voip.service.CountryServiceService;
import org.voip.service.CustomerService;
import org.voip.service.MonthlyBillService;
import org.voip.service.MonthlyTrafficService;
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
	
	@Autowired
	CallRateService callRateService;
	
	@Autowired
	MonthlyBillService monthlyBillService;
	
	@Autowired
	CommissionService commissionService;
	
	@Autowired
	MonthlyTrafficService monthlyTrafficService;
	
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
	
	@RequestMapping(value="callRateList/{countryServiceID}/{date}",method=RequestMethod.GET)
	public @ResponseBody String callRateList(@PathVariable int countryServiceID,
			@PathVariable String date){
		
		//org.voip.model.CountryService countrySer = countryServiceService.getCountryService(countryServiceID);
		Date sDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(countryServiceID+", :" + sDate);
		String retVal=null;
		for(CallRate rate : callRateService.gellCurrentCallRates(countryServiceID, sDate))
		{
			retVal += "<tr>";
			retVal += "<td>"+rate.getDestCountry().getName() +"</td>";
			retVal += "<td>"+rate.getOffPeakRate()+"</td>";
			retVal += "<td>"+rate.getPeakRate()+"</td>";
			retVal+="</tr>";
		}
		System.out.println("incoming service code is : "+countryServiceID);
		return retVal;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "call-rates/excel")
	public void generateCallRateExcelReport(HttpServletResponse res,
			@RequestParam("countryService") int countryServiceCode, @RequestParam("date") String sDate) {
		
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sDate);
		org.voip.model.CountryService countrySer = countryServiceService.getCountryService(countryServiceCode);
		CallRateExcelReport callRateExcelReport = new CallRateExcelReport(countrySer.getService(), countrySer.getCountry(), date);
		ModelAndView mav = reportManager.getReportView(callRateExcelReport);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.ms-excel"));
	    String name= String.format("%s_%s", countrySer.getCountry().getName(),countrySer.getService().getName());
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

	@RequestMapping(value="monthlyBill/list/{customerId}/{sDate}",method = RequestMethod.GET)
	public @ResponseBody String monthlyBillList(@PathVariable int customerId, @PathVariable String sDate ){
		Customer customer = customerService.getCustomerById(customerId);
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String retVal=null;
		for(CustomerMonthly report: monthlyBillService.getCustomerBils(date, customerId).getAllCustomerMonthly()){
			retVal += "<tr>";
			retVal+= "<td>"+report.getDate()+"</td>";
			retVal+= "<td>"+report.getTime() +"</td>";
			retVal+= "<td>"+report.getDuration() +"</td>";
			retVal+= "<td>"+report.getCountry() +"</td>";
			retVal+= "<td>"+report.getPhoneno() +"</td>";
			retVal+= "<td>"+report.getCost() +"</td>";
			retVal+="</tr>";
		}
		
		return retVal;
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
	
	@RequestMapping(value="salesCommision/list/{salesRepId}/{sDate}", method=RequestMethod.GET)
	public @ResponseBody String salesCommisionList(@PathVariable int salesRepId, @PathVariable String sDate){
		String retVal = "";
		System.out.println("Should show sales commision list");
		
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SalesCommissionTotalReport commisionReport = commissionService.getTotalReport(date, salesRepId);
		for(SalesCommission salesCom : commisionReport.getSalesCommissionList()){
			retVal+="<tr>";
			retVal+="<td>"+salesCom.getCustomer()+"</td>";
			retVal+="<td>"+salesCom.getCountryservice()+"</td>";
			retVal+="<td>"+salesCom.getCost()+"</td>";
			retVal+="<td>"+salesCom.getCommission()+"</td>";
			retVal+="</tr>";
		}
		retVal+="<tr><td></td><td></td><td></td><td>"+"Total commision ="+commisionReport.getTotalCommission()+"</td></tr>";
		System.out.println(retVal);
		return retVal;
	}
	@RequestMapping(method = RequestMethod.POST, value = "monthly-traffic/excel")
	public void generateMonthlyTrafficExelReport(@RequestParam("date")String sDate,HttpServletRequest req,
			HttpServletResponse res) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
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
	
	@RequestMapping(value="monthlyTraffic/list/{sDate}")
	public @ResponseBody String getMonthlyTrafficList(@PathVariable String sDate){
		String retVal="";
		
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Incoming date: "+date);
		for(MonthlyTraffic monthlyTraffic:monthlyTrafficService.getAllMonthlyTraffic(date)){
			retVal+="<tr>";
			retVal+="<td>"+monthlyTraffic.getServiceName()+"</td>";
			retVal+="<td>"+monthlyTraffic.getFromCountry()+"</td>";
			retVal+="<td>"+monthlyTraffic.getToCountry()+"</td>";
			retVal+="<td>"+monthlyTraffic.getMinutesOfCalls()+"</td>";
			retVal+="</tr>";
		}
		return retVal;
	}

}
