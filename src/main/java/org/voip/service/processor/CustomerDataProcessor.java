package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CountryServiceDAO;
import org.voip.dao.CustomerDAO;
import org.voip.model.CountryService;
import org.voip.model.Customer;

/**
 * Process the customer data
 * 
 * @author malalanayake
 *
 */

public class CustomerDataProcessor implements DataProcessor {
	private CountryServiceDAO countryServiceDAO;
	private CustomerDAO customerDAO;
	
	private FileInputStream fileInputStream;
	
	public CustomerDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	/**
	 * manually wire DAO beans
	 */
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		countryServiceDAO = beanFactory.getBean(CountryServiceDAO.class);
		customerDAO = beanFactory.getBean(CustomerDAO.class);
	}
	
	@Override
	public boolean process() {
		try {
			List<Customer> customerList = new ArrayList<Customer>();
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
				
				Customer customer = new Customer();
				
				customer.setName(row.getCell(0).getStringCellValue());
				customer.setPhoneNumber((long)row.getCell(1).getNumericCellValue());
				int countryCode = (int) row.getCell(6).getNumericCellValue();
				String serviceName = row.getCell(7).getStringCellValue();
				customer.setStreet(row.getCell(2).getStringCellValue());
				customer.setCity(row.getCell(3).getStringCellValue());
				customer.setState(row.getCell(4).getStringCellValue());
				customer.setZip((int)row.getCell(5).getNumericCellValue());
				CountryService countryService = countryServiceDAO.getCountryServiceByPK(countryCode, serviceName);
				customer.setCountryService(countryService);
				
				customerList.add(customer);
				
			}
			
			customerDAO.save(customerList);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}


	

}
