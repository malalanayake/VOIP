package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CountryDAO;
import org.voip.dao.CountryServiceDAO;
import org.voip.dao.ServiceDAO;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;


public class CountryServiceDataProcessor implements DataProcessor{
	private CountryServiceDAO countryServiceDAO;
	private ServiceDAO serviceDAO;
	private CountryDAO countryDAO;
	
	private FileInputStream fileInputStream;
	
	public CountryServiceDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	/**
	 * manually wire the DAO beans
	 */
	
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		countryServiceDAO= beanFactory.getBean(CountryServiceDAO.class);
		serviceDAO= beanFactory.getBean(ServiceDAO.class);
		countryDAO= beanFactory.getBean(CountryDAO.class);
	}

	@Override
	public boolean process() {
		

		try {

			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			boolean firstRow=true;
			while (rowIterator.hasNext()) {
				
				Row row = rowIterator.next();
				if(firstRow){
					firstRow=false;
					continue;
				}
				
				// For each row, iterate through all the columns
				
				
				String countryName =row.getCell(0).getStringCellValue();
				Country country =countryDAO.getCountryByName(countryName);
				
				String serviceName = row.getCell(1).getStringCellValue();
				Service service = new Service();
				service.setName(serviceName);
				service=serviceDAO.save(service);
				
				int peakTime = (int) row.getCell(2).getNumericCellValue();
				int offPeakTime = (int) row.getCell(3).getNumericCellValue();
				country.setPeakTime(peakTime);
				country.setOffPeakTime(offPeakTime);
				country=countryDAO.save(country);
				
				CountryService countryService = new CountryService();
				countryService.setCountry(country);
				countryService.setService(service);
				countryServiceDAO.save(countryService);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
