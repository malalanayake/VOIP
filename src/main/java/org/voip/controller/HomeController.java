package org.voip.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.voip.model.Customer;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/rates", method = RequestMethod.GET)
	public String rates() {
		
		return "rates";
	}
	
	@RequestMapping(value = "/create-rate-sheet", method = RequestMethod.GET)
	public String createRateSheet() {
		
		return "rates/create-rate-sheet";
	}
	
	@RequestMapping(value = "/output-rates", method = RequestMethod.GET)
	public String ouputRates() {
		
		return "rates/output-rates";
	}
	
	@RequestMapping(value = "/process-call-file", method = RequestMethod.GET)
	public String processCallFile() {
		
		return "rates/process-call-file";
	}
	
	@RequestMapping(value = "/update-rates", method = RequestMethod.GET)
	public String updateRates() {
		
		return "rates/update-rates";
	}
}
