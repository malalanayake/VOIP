package org.voip.service.report;

import java.io.File;
import org.springframework.web.servlet.ModelAndView;

/**
 * Monthly Traffic report generator
 *
 * @author malalanayake
 */
public class MonthlyTrafficReport implements CustomReport{

    @Override
    public ModelAndView getReportTemplate() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}