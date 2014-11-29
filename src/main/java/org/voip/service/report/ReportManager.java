package org.voip.service.report;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manage the reporting tasks
 *
 * @author malalanayake
 */

@Service
public class ReportManager implements BeanFactoryAware {
	private BeanFactory beanFactory;
	public ModelAndView getReportView(CustomReport customReport) {
		customReport.wireBeans(beanFactory);
		return customReport.getReportTemplate();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory =beanFactory;	
	}
	
}
