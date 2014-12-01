package org.voip.service.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.SalesCommissionDAO;
import org.voip.model.SalesRep;
import org.voip.model.report.SalesCommissionTotalReport;

/**
 * Sales Commission report generator
 *
 * @author malalanayake
 */
public class SalesCommissionReport implements CustomReport {
	private SalesRep salesRep;
	private Date date;

	@Autowired
	SalesCommissionDAO salesCommissionDao;

	public SalesCommissionReport(SalesRep salesRep, Date date) {
		this.salesRep = salesRep;
		this.date = date;
	}

	@Override
	public ModelAndView getReportTemplate() {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		SalesCommissionTotalReport salesCommissionReport = salesCommissionDao
				.getSalesReport(date, salesRep.getCode());
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				salesCommissionReport.getSalesCommissionList());

		DateFormat df =new SimpleDateFormat("MMMM dd, yyyy");
		String dateString = df.format(this.date);
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "SALES COMMISSION");
		parameterMap.put("month", dateString);
		parameterMap.put("salesrep", this.salesRep.getCode());
		parameterMap.put("total", salesCommissionReport.getTotalCommission());

		ModelAndView modelAndView = new ModelAndView(
				"salesCommissionPdfReport", parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		salesCommissionDao = beanFactory.getBean(SalesCommissionDAO.class);
	}

}
