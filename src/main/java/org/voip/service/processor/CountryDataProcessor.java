package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CountryDAO;
import org.voip.model.Country;

/**
 * Process country data
 * 
 * @author malalanayake
 *
 */
public class CountryDataProcessor implements DataProcessor {
	
	private CountryDAO countryDAO;
	
	private FileInputStream fileInputStream;
	
	public CountryDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	/**
	 * manually wire the DAO beans
	 */
	
	@Override
	public void wireBeans(BeanFactory beanFactory) {
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
				
				Country country = new Country();
				country.setName(row.getCell(0).getStringCellValue());
				country.setCode((int) row.getCell(1).getNumericCellValue());
				Cell cell=row.getCell(2);
				if(cell!=null)
					country.setPeakTime((int) cell.getNumericCellValue());
				cell = row.getCell(3);
				if(cell!=null)
					country.setOffPeakTime((int) cell.getNumericCellValue());
				countryDAO.save(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}




}
