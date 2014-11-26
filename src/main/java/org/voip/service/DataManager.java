package org.voip.service;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is going to manage the data processing strategy
 * 
 * @author malalanayake
 *
 */
@Service
public class DataManager {
	@Autowired
	private CountryDataProcessor countryDataProcessor;
	@Autowired
	private CustomerDataProcessor customerDataProcessor;
	@Autowired
	private CallsDataProcessor callDataProcessor;
	@Autowired
	private CallRatesDataProcessor callRateDataProcessor;
	
	public boolean processData(DataType dataType, FileInputStream inputStream) {
		Boolean returnValue=false;
		switch (dataType) {
		case CALL:
			returnValue=callDataProcessor.process(inputStream);
			break;
		case CALL_RATE:
			returnValue=callRateDataProcessor.process(inputStream);
			break;
		case COUNTRY:
			returnValue=countryDataProcessor.process(inputStream);
			break;
		case CUSTOMER:
			returnValue=customerDataProcessor.process(inputStream);
			break;
		case SALES_REP:
			break;
		default:
			break;
		
		}
		return returnValue;
	
	}
}
