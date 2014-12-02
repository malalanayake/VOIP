package org.voip.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.voip.config.Constants;
import org.voip.model.Country;
import org.voip.model.Service;
import org.voip.service.GeneralService;
import org.voip.service.processor.CallRatesDataProcessor;
import org.voip.service.processor.CallsDataProcessor;
import org.voip.service.processor.CountryDataProcessor;
import org.voip.service.processor.DataManager;
import org.voip.service.report.MonthlyTrafficReport;
import org.voip.service.report.SampleExcelSheetFactory;

@Controller
public class RateController {
	@Autowired
	GeneralService generalService;
	
	@Autowired
	DataManager dataManager;
	
	SampleExcelSheetFactory sesf = new SampleExcelSheetFactory();
	
	@RequestMapping(value = "/create-rate-sheet", method = RequestMethod.GET)
	public String createRateSheet(Model model) {
		List<Country> countries = generalService.getAllCountries();
		model.addAttribute("countries", countries);
		List<Service> services = generalService.getAllServices();
		model.addAttribute("services", services);
		return "rates/create-rate-sheet";
	}
	
	@RequestMapping(value = "/output-rates", method = RequestMethod.GET)
	public String ouputRates(Model model) {
		List<Country> countries = generalService.getAllCountries();
		model.addAttribute("countries", countries);
		List<Service> services = generalService.getAllServices();
		model.addAttribute("services", services);
		return "rates/output-rates";
	}
	
	@RequestMapping(value = "/process-call-file", method = RequestMethod.GET)
	public String processCallFile() {
		
		return "rates/process-call-file";
	}
	
	@RequestMapping(value = "/process-call-file", method = RequestMethod.POST)
	public String uploadCallFile(@RequestParam("calls") MultipartFile calls, final RedirectAttributes attr) {
		try {
			File temp = new File(calls.getOriginalFilename());
			calls.transferTo(temp);
			FileInputStream fis = new FileInputStream(temp);
			boolean result = dataManager.executeDataProcessor(new CallsDataProcessor(fis));
			if(result){
				attr.addFlashAttribute(Constants.SUCCESS, "Process calls successfully!");
			}else{
				attr.addFlashAttribute(Constants.ERROR, "Can not process calls!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			attr.addFlashAttribute(Constants.ERROR, "Can not process calls!");
		}
		return "redirect:/process-call-file";
	}
	
	@RequestMapping(value = "/update-calling-code", method = RequestMethod.GET)
	public String updateCallingCode() {
		
		return "rates/update-calling-code";
	}
	
	@RequestMapping(value = "/update-calling-code", method = RequestMethod.POST)
	public String uploadCallingCode(@RequestParam("calls") MultipartFile calls, final RedirectAttributes attr) {
		try {
			File temp = new File(calls.getOriginalFilename());
			calls.transferTo(temp);
			FileInputStream fis = new FileInputStream(temp);
			boolean result = dataManager.executeDataProcessor(new CountryDataProcessor(fis));
			if(result){
				attr.addFlashAttribute(Constants.SUCCESS, "update calling code successfully!");
			}else{
				attr.addFlashAttribute(Constants.ERROR, "Can not update calling code!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			attr.addFlashAttribute(Constants.ERROR, "Can not update calling code!");
		}
		return "redirect:/update-calling-code";
	}
	
	@RequestMapping(value = "/update-rates", method = RequestMethod.POST)
	public String uploadRates(@RequestParam("rates") MultipartFile rates, final RedirectAttributes attr) {
		try {
			File temp = new File(rates.getOriginalFilename());
			rates.transferTo(temp);
			FileInputStream fis = new FileInputStream(temp);
			boolean result = dataManager.executeDataProcessor(new CallRatesDataProcessor(fis,new Date()));
			if(result){
				attr.addFlashAttribute(Constants.SUCCESS, "Update rates successfully!");
			}else{
				attr.addFlashAttribute(Constants.ERROR, "Can not update rates!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			attr.addFlashAttribute(Constants.ERROR, "Can not update rates!");
		}
		return "redirect:/update-rates";
	}
	
	@RequestMapping(value = "/update-rates", method = RequestMethod.GET)
	public String updateRates() {
		
		return "rates/update-rates";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getSampleCallRateExcel")
	public void getSampleCallRateExcel(HttpServletRequest req,
			HttpServletResponse res) {
	    ModelAndView mav = sesf.getSampleCallRateExcel();
	    
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.ms-excel"));
	    res.setHeader("Content-disposition", "attachment; filename=" + "SampleCallRate.xls");
	    HSSFWorkbook book = (HSSFWorkbook)mav.getModel().get("excelBook");
	    try {
			book.write(res.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}