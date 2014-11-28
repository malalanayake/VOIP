package org.voip.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class CountryServiceDAOTest {
	@Autowired
	CountryServiceDAO countryServiceDAO;
	@Autowired
	CountryDAO countryDao;
	@Autowired
	ServiceDAO sericeDAO;
	


	@Test
	public void createCountryService() {
		Country country = new Country();
		country.setCode(1);
		country.setName("USA");
		country.setOffPeakTime(1230);
		country.setPeakTime(0130);

		Country countryAfter = countryDao.save(country);

		assertEquals(country.getCode(), countryAfter.getCode());
		assertEquals(country.getName(), countryAfter.getName());
		assertEquals(country.getOffPeakTime(), countryAfter.getOffPeakTime());
		assertEquals(country.getPeakTime(), countryAfter.getPeakTime());

		Service service = new Service();
		service.setName("Test Service");

		Service serviceAfter = sericeDAO.save(service);
		assertEquals(service.getName(), serviceAfter.getName());

		CountryService countryService = new CountryService();
		countryService.setCountry(country);
		countryService.setService(service);

		CountryService countryServiceAfter = countryServiceDAO
				.save(countryService);
	}
	//@Test
	public void testStoredProcedure(){
		CountryService countryService = countryServiceDAO.getCountryServiceByPK(1, "Spectra");
		System.out.println("Cname:"+countryService.getCountry().getName());
		System.out.println("Sname:"+countryService.getService().getName());
	}
}
