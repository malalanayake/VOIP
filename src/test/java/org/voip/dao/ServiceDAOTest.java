package org.voip.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.Country;
import org.voip.model.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class ServiceDAOTest {
	@Autowired
	ServiceDAO sericeDAO;

	@Test
	public void createService() {
		Service service = new Service();
		service.setId(1);
		service.setName("Test Service");

		Service serviceAfter = sericeDAO.save(service);
		assertEquals(service.getId(), serviceAfter.getId());
		assertEquals(service.getName(), serviceAfter.getName());
	}

}
