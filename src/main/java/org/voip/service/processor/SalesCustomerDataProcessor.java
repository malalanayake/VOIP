package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CustomerDAO;
import org.voip.dao.SalesCustomerDAO;
import org.voip.dao.SalesRepDAO;
import org.voip.model.Customer;
import org.voip.model.SalesCustomer;
import org.voip.model.SalesRep;

public class SalesCustomerDataProcessor implements DataProcessor{
	private FileInputStream fileInputStream;
	
	private SalesRepDAO salesRepDAO;
	private SalesCustomerDAO salesCustomerDAO;
	private CustomerDAO customerDAO;
	
	public SalesCustomerDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		salesRepDAO = beanFactory.getBean(SalesRepDAO.class);
		salesCustomerDAO = beanFactory.getBean(SalesCustomerDAO.class);
		customerDAO = beanFactory.getBean(CustomerDAO.class);
		
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
				SalesCustomer salesCustomer = new SalesCustomer();
				
				long customerTel = (long) row.getCell(0).getNumericCellValue();
				int salesRepCode = (int) row.getCell(1).getNumericCellValue();
				float commission = (float) row.getCell(2).getNumericCellValue();
				SalesRep salesRep=salesRepDAO.getSalesRepByPK(salesRepCode);
				if(salesRep==null){
					salesRep= new SalesRep();
					salesRep.setCode(salesRepCode);
					salesRep=salesRepDAO.save(salesRep);
				}
				// salesRep=salesRepDAO.getSalesRepByPK(salesRepCode);
				Customer customer = customerDAO.findOne(customerTel);
				
				salesCustomer.setCommission(commission);
				salesCustomer.setCustomer(customer);
				salesCustomer.setSalesRep(salesRep);
				
				salesCustomerDAO.save(salesCustomer);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	

}
