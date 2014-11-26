package org.voip.service;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.voip.dao.CountryDAO;
import org.voip.dao.CustomerDAO;
import org.voip.model.CallDetail;
import org.voip.model.Country;

/**
 * Process calls data
 * 
 * @author malalanayake
 *
 */
public class CallsDataProcessor implements ProcessData {
	
	private CustomerDAO customerDAO;
	
	private CountryDAO countryDAO;
	@Override
	public boolean process(FileInputStream inputStream) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				CallDetail call = new CallDetail();
				while (cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						//country.setName(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "n");
						//country.setCode((int)cell.getNumericCellValue());
						break;

					}
				}
				//countryDAO.save(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
