package junitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import application.BikePart;
import application.Warehouse;
import roster.*;
import application.*;

class SalesInvoiceTest {

	SalesInvoice makeTestCase() {
		ArrayList<BikePart> bpAll = new ArrayList<BikePart>();
		bpAll.add(new BikePart("wheel07", 87654321, 40.99, 35.87, true, 15));
		bpAll.add(new BikePart("spoke", 12312312, 12.67, 11.07, false, 60));
		bpAll.add(new BikePart("popsicle", 404, 2.99, 2.98, true, 123));
		SalesInvoice test = new SalesInvoice("Sue's Shop","Sue", bpAll, new SalesAsso("John","Doe","404","404",new LoginAccount("username","password",1), new Warehouse()));
		return test;
	}


	
	
	@Test
	void testGetStore() {
		SalesInvoice test = makeTestCase();
		assertEquals(test.getStore(), "Sue's Shop");
	}

	@Test
	void testGetCustomer() {
		SalesInvoice test = makeTestCase();
		assertEquals(test.getCustomer(), "Sue");
	}

	@Test
	void testGetParts() {
		SalesInvoice test = makeTestCase();
		assertEquals(test.getParts().get(2).getName(),"popsicle");
	}

	@Test
	void testGetEmployee() {
		SalesInvoice test = makeTestCase();
		assertEquals(test.getEmployee().getNameLast(),"Doe");
	}

	@SuppressWarnings("deprecation")
	@Test
	void testGetDate() {
		SalesInvoice test = makeTestCase();
		assertEquals(test.getDate().getYear(),2018-1900);
	}

	@Test
	void testToString() {
		SalesInvoice test = makeTestCase();
		System.out.println(test.toString());
	}

}
