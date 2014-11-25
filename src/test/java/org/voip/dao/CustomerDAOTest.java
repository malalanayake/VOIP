package org.voip.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class CustomerDAOTest {
	@Autowired
	CustomerDAO customerDAO;

	@Test
	public void createCustomer() {
		Customer customer = new Customer();
		customer.setName("Dinuka");
		customer.setPhoneNumber(94772508354l);
		customer.setCity("Ames");
		customer.setState("IA");
		customer.setZip(50010);

		Customer customerAfter = customerDAO.save(customer);
		assertEquals(customer.getName(), customerAfter.getName());
		assertEquals(customer.getPhoneNumber(), customerAfter.getPhoneNumber());
		assertEquals(customer.getCity(), customerAfter.getCity());
		assertEquals(customer.getState(), customerAfter.getState());
		assertEquals(customer.getZip(), customerAfter.getZip());
	}

}
