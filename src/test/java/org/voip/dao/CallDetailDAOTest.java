package org.voip.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.CallDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test.xml" })
@Transactional
public class CallDetailDAOTest {
	@Autowired
	CallDetailDAO callDetailsDAO;

	@Test
	public void createCallDetails() {
		
	}

}
