package org.voip.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.CallRateDAO;
import org.voip.model.CallRate;

/**
 * Call Rate report generator
 *
 * @author malalanayake
 */
public class CallRateReport implements CustomReport {

    @Override
    public ModelAndView getReportTemplate() {
    	 Map<String,Object> parameterMap = new HashMap<String,Object>();
    	 CallRate callRate = new CallRate();
    	 callRate.setId(1);
         List<CallRate> usersList = new ArrayList<CallRate>();
         usersList.add(callRate);
         
         JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);
  
         parameterMap.put("datasource", JRdataSource);
         parameterMap.put("header", "Call Rate Report");
         
         ModelAndView modelAndView = new ModelAndView("callRatePdfReport", parameterMap);
         return modelAndView;
    }

}
