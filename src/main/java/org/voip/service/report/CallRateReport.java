package org.voip.service.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.CallRateDAO;
import org.voip.dao.CountryServiceDAO;
import org.voip.model.CallRate;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;

/**
 * Call Rate report generator
 *
 * @author malalanayake
 */
public class CallRateReport implements CustomReport {
	private Service service;
	private Country country;

	@Autowired
	private CallRateDAO callRateDAO;

	@Autowired
	private CountryServiceDAO countryServiceDAO;

	public CallRateReport(Country country, Service service) {
		this.country = country;
		this.service = service;
	}

	@Override
	public ModelAndView getReportTemplate() {
		CountryService countryService = countryServiceDAO
				.findByCountryAndService(country, service);
		List<CallRate> callRates = callRateDAO.findLatestRate(countryService
				.getId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRates);

		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "RATE SHEET");
		parameterMap.put("country", countryService.getCountry().getName());
		parameterMap.put("service", countryService.getService().getName());
		parameterMap.put("peakTime", countryService.getCountry().getPeakTime());
		parameterMap.put("offPeakTime", countryService.getCountry()
				.getOffPeakTime());

		ModelAndView modelAndView = new ModelAndView("callRatePdfReport",
				parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		callRateDAO = beanFactory.getBean(CallRateDAO.class);
		countryServiceDAO = beanFactory.getBean(CountryServiceDAO.class);
	}

}
