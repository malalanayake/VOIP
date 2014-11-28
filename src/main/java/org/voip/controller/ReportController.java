package org.voip.controller;

import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.voip.service.report.CallRateReport;
import org.voip.service.report.ReportManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ModelAndView generatePdfReport(ModelAndView modelAndView) {
		CallRateReport callRateReport = new CallRateReport();
		modelAndView = reportManager.getReportView(callRateReport);

		return modelAndView;
	}

}
