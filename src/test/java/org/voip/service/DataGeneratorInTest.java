package org.voip.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
	
	
	
	@Test
	public void FileExistence() {
		URL resource = getClass().getResource("/Calling_Codes.xls");
		File file;
		try {
			file = new File(resource.toURI());
			FileInputStream input = new FileInputStream(file);
			assertNotNull(input);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
		
		
	}
	@Test
	public void processFile(){
		URL resource = getClass().getResource("/Calling_Codes.xls");
		try
        {
			File file = new File(resource.toURI());
            FileInputStream inputFile = new FileInputStream(file);
 
            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(inputFile);
 
            //Get first/desired sheet from the workbook
           HSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            boolean firstRow=true;
            while (rowIterator.hasNext())
            {
            	Row row = rowIterator.next();
            	if(firstRow){
					firstRow=false;
					continue;
				}
               
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
