package org.voip.service.report;

import java.util.Date;
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
	private Date month;

	@Autowired
	private CallRateDAO callRateDAO;

	@Autowired
	private CountryServiceDAO countryServiceDAO;

	public CallRateReport(Country country, Service service, Date month) {
		this.country = country;
		this.service = service;
		this.month = month;
	}

	@Override
	public ModelAndView getReportTemplate() {
		CountryService countryService = countryServiceDAO
				.findByCountryAndService(country, service);
		List<CallRate> callRates = callRateDAO.findLatestRate(countryService
				.getId(),month);
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRates);

		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "RATE SHEET");
		parameterMap.put("month", month);
		parameterMap.put("country", countryService.getCountry().getName());
		parameterMap.put("service", countryService.getService().getName());
		parameterMap.put("peakTime",getTimeStampString(countryService.getCountry().getPeakTime()));
		parameterMap.put("offPeakTime", getTimeStampString(countryService.getCountry()
				.getOffPeakTime()));

		ModelAndView modelAndView = new ModelAndView("callRatePdfReport",
				parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		callRateDAO = beanFactory.getBean(CallRateDAO.class);
		countryServiceDAO = beanFactory.getBean(CountryServiceDAO.class);
	}
	
	public String getTimeStampString(int time){
		String s = String.valueOf(time);
		StringBuilder timeStampBuilder=new StringBuilder();
		int length = s.length();
		if(length<=2){
			timeStampBuilder.append("00:");
			if(s.length()==1)
				timeStampBuilder.append("0");
			timeStampBuilder.append(s);
		}
		else if(length>2&&length<=4){
			String min= s.substring(length-2, length);
			String hr = s.substring(0,length-2);
			if(hr.length()==1)
				timeStampBuilder.append("0");
			timeStampBuilder.append(hr);
			timeStampBuilder.append(":"+min);
		}
		
		return timeStampBuilder.toString();
	}
}