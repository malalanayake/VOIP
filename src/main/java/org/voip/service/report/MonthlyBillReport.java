package org.voip.service.report;

import java.io.File;
import org.springframework.web.servlet.ModelAndView;

/**
 * Monthly billing report generator
 *
 * @author malalanayake
 */
public class MonthlyBillReport implements CustomReport {

    @Override
    public ModelAndView getReportTemplate() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
