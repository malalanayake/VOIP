package org.voip.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.voip.config.Constants;
import org.voip.model.Country;
import org.voip.model.Customer;
import org.voip.model.Service;
import org.voip.service.GeneralService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	GeneralService generalService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/rates", method = RequestMethod.GET)
	public String rates() {

		return "rates";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authorize(HttpServletRequest req, final RedirectAttributes attr,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		if(username.equals("admin")&&password.equals("admin")){
			req.getSession().setAttribute(Constants.USERNAME, username);
			attr.addFlashAttribute(Constants.SUCCESS, "Login successfully");
			return "redirect:/";
		}
		attr.addFlashAttribute(Constants.ERROR, "Wrong username or password");
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute(Constants.USERNAME);
		return "login";
	}
}
