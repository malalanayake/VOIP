package org.voip.service.report;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sales Commission report generator
 *
 * @author malalanayake
 */
public class SalesCommissionReport implements CustomReport{

    @Override
    public ModelAndView getReportTemplate() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

	@Override
	public void wireBeans(BeanFactory beanFactory) {

	}

}
