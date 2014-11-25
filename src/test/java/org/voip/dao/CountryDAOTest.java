package org.voip.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class CountryDAOTest {
	@Autowired
	CountryDAO countryDao;

	@Test
	public void createCountry() {
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
	}

}
