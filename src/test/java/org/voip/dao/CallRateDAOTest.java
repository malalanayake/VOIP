package org.voip.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class CallRateDAOTest {
	@Autowired
	CallRateDAO callRateDAO;
	
	@Test
	public void testStoredProcedures(){
	 	System.out.println(callRateDAO.findLatestRate(1,new Date()).size());
	}

}
