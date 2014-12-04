package org.voip.service.processor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.SalesRepDAO;
import org.voip.model.Customer;
import org.voip.model.SalesCustomer;
import org.voip.model.SalesRep;

/**
 * Sales Rep Data Processing
 * 
 * @author malalanayake
 *
 */
public class SalesRepDataProcessor implements DataProcessor {
	private FileInputStream fileInputStream;
	private SalesRepDAO salesRepDAO;

	public SalesRepDataProcessor(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	@Override
	public void wireBeans(BeanFactory beanFactory) {
		this.salesRepDAO = beanFactory.getBean(SalesRepDAO.class);
	}

	@Override
	public boolean process() {
		try {

			List<SalesRep> salesRepList = new ArrayList<SalesRep>();
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			boolean firstRow = true;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				if (firstRow) {
					firstRow = false;
					continue;
				}
				int salesRepCode = (int) row.getCell(0).getNumericCellValue();
				SalesRep salesRep = salesRepDAO.getSalesRepByPK(salesRepCode);
				if (salesRep == null) {
					salesRep = new SalesRep();
					salesRep.setCode(salesRepCode);
					salesRepList.add(salesRep);
				}

			}

			salesRepDAO.save(salesRepList);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
