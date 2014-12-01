package org.voip.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.voip.model.report.SalesCommissionTotalReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/root-context-test-data.xml" })
@Transactional
public class SalesCommissionDAOTest {
	@Autowired
	private SalesCommissionDAO salesCommissionDAO;
	@Test
	public void testStoredProcedure(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse("05/12/2014");
			SalesCommissionTotalReport total=salesCommissionDAO.getSalesReport(date, 23);
			
			System.out.println(total.getTotalCommission()+":size of List:"+total.getSalesCommissionList().size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
