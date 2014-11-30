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
import org.voip.dao.SalesCommissionDAO;
import org.voip.model.SalesRep;
import org.voip.model.report.SalesCommission;

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
		List<SalesCommission> listOfRecords = salesCommissionDao
				.getSalesReport(date, salesRep.getCode());
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				listOfRecords);

		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("header", "SALES COMMISSION");
		parameterMap.put("month", this.date);
		parameterMap.put("salesrep", this.salesRep.getCode());

		ModelAndView modelAndView = new ModelAndView(
				"salesCommissionPdfReport", parameterMap);
		return modelAndView;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		salesCommissionDao = beanFactory.getBean(SalesCommissionDAO.class);
	}

}
