package org.voip.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.voip.dao.CountryServiceDAO;
import org.voip.dao.CustomerDAO;
import org.voip.dao.SalesCustomerDAO;
import org.voip.dao.ServiceDAO;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;
import org.voip.service.processor.CallRatesDataProcessor;
import org.voip.service.processor.CallsDataProcessor;
import org.voip.service.processor.CountryDataProcessor;
import org.voip.service.processor.CountryServiceDataProcessor;
import org.voip.service.processor.CustomerDataProcessor;
import org.voip.service.processor.DataManager;
import org.voip.service.processor.SalesCustomerDataProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test-data.xml" })
@Transactional
public class PopulateData {
	@Autowired
	DataManager dataManager;
	
	@Autowired
	CountryDAO countryDAO;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	CallRateDAO callRateDAO;
	
	@Autowired
	SalesCustomerDAO salesCustomerDAO;
	
	@Autowired
	CallDetailDAO callDetailDAO;
	
	@Autowired
	ServiceDAO serviceDAO;
	@Autowired
	private CountryServiceDAO countryServiceDAO;
	
	@Test
	@Rollback(false)
	public void populateData(){
		processCountryData();
		processCountryServiceData();
		processCustomerData();
		//generateCountryServiceData();
		//processCallRateData();
		//processCallRateData2();
		//processCallRateData3();
		//processCallRateData4();
		
		//processCallDetails();
		processSalesRepData();
		
	}
	
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
		//assertEquals(countryDAO.count(),205);

	}
	
	public void processCountryServiceData(){
		URL resource = getClass().getResource("/Country_Service.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Boolean value=dataManager.executeDataProcessor(new CountryServiceDataProcessor(fileInputStream));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		//assertEquals(countryDAO.count(),205);
	}
	
	public void processCustomerData(){
		//long prev=customerDAO.count();
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
		//assertEquals(3+prev, customerDAO.count());

	}

	public void processCallRateData(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		URL resource = getClass().getResource("/Rates_20140115.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Date date = formatter.parse("15/01/2014");
			Boolean value=dataManager.executeDataProcessor(new CallRatesDataProcessor(fileInputStream, date));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public void processCallRateData2(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		URL resource = getClass().getResource("/Rates_20140101.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Date date = formatter.parse("01/01/2014");
			Boolean value=dataManager.executeDataProcessor(new CallRatesDataProcessor(fileInputStream, date));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}catch (ParseException e) {
			e.printStackTrace();
		}

	}
	public void processCallRateData3(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		URL resource = getClass().getResource("/Rates_20131215.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Date date = formatter.parse("15/12/2013");
			Boolean value=dataManager.executeDataProcessor(new CallRatesDataProcessor(fileInputStream, date));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}catch (ParseException e) {
			e.printStackTrace();
		}

	}
	public void processCallRateData4(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		URL resource = getClass().getResource("/Rates_20130901.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			Date date = formatter.parse("01/09/2013");
			Boolean value=dataManager.executeDataProcessor(new CallRatesDataProcessor(fileInputStream, date));
			assertEquals(true, value);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertFalse(true);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
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
	
	public void generateCountryServiceData(){
		Service spectra = new Service();
		spectra.setName("Spectra");
		spectra=serviceDAO.save(spectra);
		
		Service voip = new Service();
		voip.setName("VOIP");
		voip=serviceDAO.save(voip);
		
		Service deluxe = new Service();
		deluxe.setName("Deluxe");
		deluxe=serviceDAO.save(deluxe);
		
		Country usa = countryDAO.getCountryByName("USA");
		
		CountryService usaSpectra = new CountryService();
		usaSpectra.setCountry(usa);
		usaSpectra.setService(spectra);
		countryServiceDAO.save(usaSpectra);
		
		CountryService usaVoip = new CountryService();
		usaVoip.setCountry(usa);
		usaVoip.setService(voip);
		countryServiceDAO.save(usaVoip);
		
		CountryService usaDeluxe = new CountryService();
		usaDeluxe.setCountry(usa);
		usaDeluxe.setService(deluxe);
		countryServiceDAO.save(usaDeluxe);
		
	}
	
}
