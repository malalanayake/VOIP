package org.voip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.voip.config.Constants;
import org.voip.dao.SalesRepDAO;
import org.voip.model.SalesRep;
import org.voip.service.SalesRepService;

@Controller
public class SaleRepController {
	
	@Autowired
	SalesRepService salesRepService;
	
	@RequestMapping(value="/add-sale-rep",method=RequestMethod.GET)
	public String addSaleRep(Model model){
		SalesRep salesRep = new SalesRep();
		model.addAttribute("salesRep", salesRep);
		return "salesrep/add-sales-rep";
	}
	
	@RequestMapping(value="/add-sale-rep",method=RequestMethod.POST)
	public String createNewSaleRep(@ModelAttribute SalesRep salesRep,final RedirectAttributes attr ){
		SalesRep result = salesRepService.createSalesRep(salesRep);
		if(result==null){
			attr.addFlashAttribute(Constants.ERROR,"Can not add sales rep!");
		}else{
			attr.addFlashAttribute(Constants.SUCCESS,"Add sales rep successfully.");
		}
		return "redirect:/add-sale-rep";
	}
}

