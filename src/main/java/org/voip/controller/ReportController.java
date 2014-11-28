package org.voip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.voip.service.report.CallRateReport;
import org.voip.service.report.MonthlyBillReport;
import org.voip.service.report.ReportManager;

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


	@RequestMapping(method = RequestMethod.GET, value = "call-rates/pdf")
	public ModelAndView generateCallRatePdfReport(ModelAndView modelAndView) {
		CallRateReport callRateReport = new CallRateReport(1l,1l);
		modelAndView = reportManager.getReportView(callRateReport);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "monthly-bill/pdf")
	public ModelAndView generateMonthlyBillPdfReport(ModelAndView modelAndView) {
		MonthlyBillReport monthlyBillReport = new MonthlyBillReport();
		modelAndView = reportManager.getReportView(monthlyBillReport);

		return modelAndView;
	}

}
