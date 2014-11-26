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
	
	public boolean processData(DataType dataType, FileInputStream inputStream) {
		Boolean returnValue=false;
		switch(dataType){
		case COUNTRY:
			returnValue=countryDataProcessor.process(inputStream);
			break;
		case CUSTOMER:
			break;
		case SALES_REP:
			break;
		default:
			break;
		
		}
		return returnValue;
	}
}
