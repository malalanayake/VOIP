package org.voip.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.voip.config.Constants;
import org.voip.model.Country;
import org.voip.model.Service;
import org.voip.service.CallRatesDataProcessor;
import org.voip.service.CallsDataProcessor;
import org.voip.service.DataManager;
import org.voip.service.GeneralService;

@Controller
public class RateController {
	@Autowired
	GeneralService generalService;
	
	@Autowired
	DataManager dataManager;
	
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
	
	@RequestMapping(value = "/update-rates", method = RequestMethod.GET)
	public String updateRates() {
		
		return "rates/update-rates";
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
}