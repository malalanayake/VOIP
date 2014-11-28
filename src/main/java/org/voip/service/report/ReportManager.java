package org.voip.service.report;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manage the reporting tasks
 *
 * @author malalanayake
 */

@Service
public class ReportManager {

	public ModelAndView getReportView(CustomReport customReport) {
		return customReport.getReportTemplate();
	}
}
