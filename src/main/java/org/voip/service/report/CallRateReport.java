package org.voip.service.report;

import org.springframework.web.servlet.ModelAndView;

/**
 * Call Rate report generator
 *
 * @author malalanayake
 */
public class CallRateReport implements CustomReport {

    @Override
    public ModelAndView getReportTemplate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
