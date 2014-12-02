package org.voip.service.report;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.voip.dao.CountryDAO;
import org.voip.model.Country;
@Service
public class SampleExcelSheetFactory {
	@Autowired
	private CountryDAO countryDAO;
	
	public ModelAndView getSampleCallingCodeExcel(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Calling_Codes");
		
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("Country Name");
		row0.createCell(1).setCellValue("Country Code");
		
		Map<String, HSSFWorkbook> parameterMap = new HashMap<String, HSSFWorkbook>();
		parameterMap.put("excelBook", workbook);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(parameterMap);
		return modelAndView;
	}
	
	public ModelAndView getSampleCallDetailExcel(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Calling_Codes");
		
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("from_code");
		row0.createCell(1).setCellValue("to_code");
		row0.createCell(2).setCellValue("from_tel");
		row0.createCell(3).setCellValue("to_tel");
		row0.createCell(4).setCellValue("duration");
		row0.createCell(5).setCellValue("call_date");
		row0.createCell(6).setCellValue("call_time");
		
		Map<String, HSSFWorkbook> parameterMap = new HashMap<String, HSSFWorkbook>();
		parameterMap.put("excelBook", workbook);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(parameterMap);
		return modelAndView;
	}
	
	public ModelAndView getSampleCallRateExcel(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Service_Country");
		
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("dest");
		row0.createCell(1).setCellValue("peakRate");
		row0.createCell(2).setCellValue("offpeak");
		Iterable<Country> countries = countryDAO.findAll();
		int rowNo=1;
		for(Country country:countries){
			int code=country.getCode();
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(code);
		}
		
		Map<String, HSSFWorkbook> parameterMap = new HashMap<String, HSSFWorkbook>();
		parameterMap.put("excelBook", workbook);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(parameterMap);
		return modelAndView;
	}
	
	
}
