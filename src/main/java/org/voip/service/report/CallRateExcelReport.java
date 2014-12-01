package org.voip.service.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.CallRateDAO;
import org.voip.dao.CountryServiceDAO;
import org.voip.model.CallRate;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;

public class CallRateExcelReport implements CustomReport {
	private Service service;
	private Country country;
	private Date month;
	
	private CallRateDAO callRateDAO;
	private CountryServiceDAO countryServiceDAO;
	
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		callRateDAO = beanFactory.getBean(CallRateDAO.class);
		countryServiceDAO = beanFactory.getBean(CountryServiceDAO.class);
	}
	
	public CallRateExcelReport(Service service, Country country, Date month) {
		this.service = service;
		this.country = country;
		this.month = month;
	}

	@Override
	public ModelAndView getReportTemplate() {
		
		CountryService countryService = countryServiceDAO
				.findByCountryAndService(country, service);
		List<CallRate> callRates = callRateDAO.findLatestRate(countryService
				.getId(),month);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Monthly Traffic Report");

		int rownum = 0;
		Row row0 = sheet.createRow(rownum++);
		row0.createCell(0).setCellValue("Country");
		row0.createCell(1).setCellValue("Peak Rate");
		row0.createCell(2).setCellValue("Off Peak Rate");

		for (CallRate callRate : callRates) {
			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(callRate.getDestCountry().getName());
			row.createCell(1).setCellValue(callRate.getPeakRate());
			row.createCell(2).setCellValue(callRate.getOffPeakRate());

		}
		
		Map<String, HSSFWorkbook> parameterMap = new HashMap<String, HSSFWorkbook>();
		parameterMap.put("excelBook", workbook);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(parameterMap);
		return modelAndView;
	}

}
