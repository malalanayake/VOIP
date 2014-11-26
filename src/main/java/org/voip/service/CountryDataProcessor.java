package org.voip.service;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voip.dao.CountryDAO;
import org.voip.model.Country;

/**
 * Process country data
 * 
 * @author malalanayake
 *
 */
@Service
public class CountryDataProcessor implements ProcessData {

	@Autowired
	private CountryDAO countryDAO;

	@Override
	public boolean process(FileInputStream inputStream) {

		try {

			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
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
				Iterator<Cell> cellIterator = row.cellIterator();
				Country country = new Country();
				while (cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						country.setName(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "n");
						country.setCode((int)cell.getNumericCellValue());
						break;

					}
				}
				countryDAO.save(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
