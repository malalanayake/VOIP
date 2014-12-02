package org.voip.service.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.CustomerMonthlyDAO;
import org.voip.model.Customer;
import org.voip.model.report.CustomerMonthly;
import org.voip.model.report.CustomerMonthlyTotalReport;

/**
 * Monthly billing report generator
 *
 * @author malalanayake
 */
public class MonthlyBillReport implements CustomReport {
	private Date month;
	private Customer customer;

	@Autowired
	private CustomerMonthlyDAO customerMonthlyReportDAO;

	public MonthlyBillReport(Date month, Customer customer) {
		this.month = month;
		this.customer = customer;
	}

	@Override
	public ModelAndView getReportTemplate() {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		CustomerMonthlyTotalReport customerMonthlyReport = customerMonthlyReportDAO
				.getReportByMonthACustomer(month, customer.getPhoneNumber());
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				customerMonthlyReport.getAllCustomerMonthly());

		DateFormat df =new SimpleDateFormat("MMMM, yyyy");
		String dateString = df.format(month);
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "MONTHLY BILL");
		parameterMap.put("month", dateString);
		parameterMap.put("customer", this.customer.getName());
		parameterMap.put("address", this.customer.toString());
		parameterMap.put("service", this.customer.getCountryService()
				.getService().getName());
		parameterMap.put("total", customerMonthlyReport.getTotalCost());

		ModelAndView modelAndView = new ModelAndView("monthlyBillPdfReport",
				parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		customerMonthlyReportDAO = beanFactory.getBean(CustomerMonthlyDAO.class);
	}

}
