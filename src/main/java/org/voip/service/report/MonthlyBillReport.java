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
import org.voip.dao.CustomerMonthlyReportDAO;
import org.voip.model.Customer;
import org.voip.model.report.CustomerMonthlyReport;

/**
 * Monthly billing report generator
 *
 * @author malalanayake
 */
public class MonthlyBillReport implements CustomReport {
	private Date month;
	private Customer customer;

	@Autowired
	private CustomerMonthlyReportDAO customerMonthlyReportDAO;

	public MonthlyBillReport(Date month, Customer customer) {
		this.month = month;
		this.customer = customer;
	}

	@Override
	public ModelAndView getReportTemplate() {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<CustomerMonthlyReport> listOfRecords = customerMonthlyReportDAO
				.getReportByMonthACustomer(month, customer.getPhoneNumber());
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				listOfRecords);

		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "RATE SHEET");
		parameterMap.put("month", this.month);
		parameterMap.put("customer", this.customer.getName());
		parameterMap.put("service", this.customer.getCountryService()
				.getService().getName());

		ModelAndView modelAndView = new ModelAndView("monthlyBillPdfReport",
				parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		customerMonthlyReportDAO = beanFactory.getBean(CustomerMonthlyReportDAO.class);
	}

}
