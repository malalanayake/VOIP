package org.voip.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.dao.CallDetailDAO;
import org.voip.dao.CallRateDAO;
import org.voip.dao.CountryDAO;
import org.voip.dao.CustomerDAO;
import org.voip.dao.SalesCustomerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test-data.xml" })
@Transactional
public class DataGeneratorInTest {
	@Autowired
	DataManager dataManager;
	
	@Autowired
	CountryDAO countryDAO;
	
	@Test
	@Rollback(false)
	public void processCountryData() {

		URL resource = getClass().getResource("/Calling_Codes.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new CountryDataProcessor(fileInputStream));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		assertEquals(countryDAO.count(),205);

	}
	@Autowired
	CustomerDAO customerDAO;
	@Test
	@Rollback(false)
	public void processCustomerData(){
		long prev=customerDAO.count();
		URL resource = getClass().getResource("/Customer.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new CustomerDataProcessor(fileInputStream));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		assertEquals(3+prev, customerDAO.count());

	}
	
	@Autowired
	CallRateDAO callRateDAO;
	@Test
	@Rollback(false)
	public void processCallRateData(){
		URL resource = getClass().getResource("/Rates_20130901.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new CallRatesDataProcessor(fileInputStream, new Date()));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}

	}
	
	@Autowired
	SalesCustomerDAO salesCustomerDAO;
	@Test
	@Rollback(false)
	public void processSalesRepData(){
		URL resource = getClass().getResource("/SalesRep.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new SalesCustomerDataProcessor(fileInputStream));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
	
	@Autowired
	CallDetailDAO callDetailDAO;
	@Test
	@Rollback(false)
	public void processCallDetails(){
		URL resource = getClass().getResource("/Calls_Test.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new CallsDataProcessor(fileInputStream));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
	
	
}
