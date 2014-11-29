package org.voip.service;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.BeanFactory;
import org.voip.dao.CallRateDAO;
import org.voip.dao.CountryDAO;
import org.voip.dao.CountryServiceDAO;
import org.voip.model.CallRate;
import org.voip.model.Country;
import org.voip.model.CountryService;

/**
 * Process the call rate data
 * 
 * @author malalanayake
 *
 */

public class CallRatesDataProcessor implements DataProcessor {

	private CallRateDAO callRateDAO;
	private CountryDAO countryDAO;
	private CountryServiceDAO countryServiceDAO;

	private FileInputStream fileInputStream;
	private Date effectiveFrom;

	public CallRatesDataProcessor(FileInputStream fileInputStream,
			Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
		this.fileInputStream = fileInputStream;
	}

	/**
	 * manually wire DAO beans
	 */
	@Override
	public void wireBeans(BeanFactory beanFactory) {
		countryServiceDAO = beanFactory.getBean(CountryServiceDAO.class);
		callRateDAO = beanFactory.getBean(CallRateDAO.class);
		countryDAO = beanFactory.getBean(CountryDAO.class);
	}

	@Override
	public boolean process() {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			for (int sheetNo = 0; sheetNo < workbook.getNumberOfSheets(); sheetNo++) {
				HSSFSheet sheet = workbook.getSheetAt(sheetNo);
				String sheetName = sheet.getSheetName();
				String[] tokens = sheetName.split("_");
				String serviceName = tokens[0];
				String countryName = tokens[1];
				CountryService countryService = countryServiceDAO
						.getCountryServiceByPK(countryName, serviceName);
				
				if (countryService != null) {
					Iterator<Row> rowIterator = sheet.iterator();
					boolean firstRow = true;
					while (rowIterator.hasNext()) {

						Row row = rowIterator.next();
						if (firstRow) {
							firstRow = false;
							continue;
						}

						CallRate callRate = new CallRate();
						Country destCountry = countryDAO.findOne((int) row
								.getCell(0).getNumericCellValue());

						callRate.setCountryService(countryService);
						callRate.setDestCountry(destCountry);
						callRate.setPeakRate((float) row.getCell(1)
								.getNumericCellValue());
						callRate.setOffPeakRate((float) row.getCell(2)
								.getNumericCellValue());
						callRate.setEffectiveFrom(effectiveFrom);
						callRateDAO.save(callRate);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
