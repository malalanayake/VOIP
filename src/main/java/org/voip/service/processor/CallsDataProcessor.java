package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CallDetailDAO;
import org.voip.dao.CountryDAO;
import org.voip.dao.CustomerDAO;
import org.voip.model.CallDetail;
import org.voip.model.Country;
import org.voip.model.Customer;

/**
 * Process calls data
 * 
 * @author malalanayake
 *
 */

public class CallsDataProcessor implements DataProcessor {
	
	private CountryDAO countryDAO;
	private CustomerDAO customerDAO;
	private CallDetailDAO callDetailDAO;
	
	private FileInputStream fileInputStream;
	
	public CallsDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream =fileInputStream;
	}
	/**
	 * manually wire the DAO beans
	 */
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		customerDAO = beanFactory.getBean(CustomerDAO.class);
		callDetailDAO = beanFactory.getBean(CallDetailDAO.class);
		countryDAO = beanFactory.getBean(CountryDAO.class);
	}

	@Override
	public boolean process() {
		try {
			
			List<CallDetail> callDetailList = new ArrayList<CallDetail>();
			
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			boolean firstRow=true;
			while (rowIterator.hasNext()) {
				
				Row row = rowIterator.next();
				if(firstRow){ //ignore first row
					firstRow=false;
					continue;
				}
				
			
				CallDetail callDetail = new CallDetail();
				
				Country fromCountry = countryDAO.findOne((int)row.getCell(0).getNumericCellValue());
				Country toCountry = countryDAO.findOne((int)row.getCell(1).getNumericCellValue());
				Customer customer = customerDAO.findOne((long)row.getCell(2).getNumericCellValue());
				long toTel = (long)row.getCell(3).getNumericCellValue();
				long duration = (long)row.getCell(4).getNumericCellValue();
				Date callDate = row.getCell(5).getDateCellValue();
				int callTime = (int)row.getCell(6).getNumericCellValue();
				
				callDetail.setCallDate(callDate);
				callDetail.setCallTime(callTime);
				callDetail.setDuration(duration);
				callDetail.setFromCountry(fromCountry);
				callDetail.setToCountry(toCountry);
				callDetail.setToTel(toTel);
				callDetail.setFromCustomer(customer);
				
				callDetailList.add(callDetail);
			}
			callDetailDAO.save(callDetailList);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	

			
}
