package org.voip.service.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.MonthlyTrafficDAO;
import org.voip.model.report.MonthlyTraffic;


/**
 * Monthly Traffic report generator
 *
 * @author malalanayake
 */
public class MonthlyTrafficReport implements CustomReport{
	private Date monthDate;
	
	private MonthlyTrafficDAO monthlyTraffictDAO;
	
	public MonthlyTrafficReport(Date monthDate) {
		this.monthDate=monthDate;
	}
	
    @Override
    public ModelAndView getReportTemplate() {
    	List<MonthlyTraffic> monthlyTrafficReports =monthlyTraffictDAO.getMonthlyTraffic(monthDate);
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	HSSFSheet sheet = workbook.createSheet("Monthly Traffic Report");
    	 
    	int rownum = 0;
    	Row row0 = sheet.createRow(rownum++);
    	row0.createCell(0).setCellValue("Service");
    	row0.createCell(1).setCellValue("Source Country");
    	row0.createCell(2).setCellValue("Destination Country");
    	row0.createCell(3).setCellValue("Total Call Minutes");
    	
    	for (MonthlyTraffic mt : monthlyTrafficReports) {
    	    Row row = sheet.createRow(rownum++);
    	    row.createCell(0).setCellValue(mt.getServiceName());
        	row.createCell(1).setCellValue(mt.getFromCountry());
        	row.createCell(2).setCellValue(mt.getToCountry());
        	row.createCell(3).setCellValue(mt.getMinutesOfCalls());
    	    
    	}
    
    	 
    	try {
    	    FileOutputStream out = 
    	            new FileOutputStream(new File("MothlyReport.xls"));
    	    workbook.write(out);
    	    out.close();
    	    Map<String, FileOutputStream> parameterMap = new HashMap<String, FileOutputStream>();
    	    parameterMap.put("excelFile", out);
    	    ModelAndView modelAndView = new ModelAndView();
    	    modelAndView.addAllObjects(parameterMap);
    	    return modelAndView;
    	     
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	return null;
    }

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		monthlyTraffictDAO=beanFactory.getBean(MonthlyTrafficDAO.class);
	}

}
