package junitTestCases;

import static org.junit.jupiter.api.Assertions.*;
import roster.*;
import org.junit.jupiter.api.Test;

class EmployeeTest {
	Employee makeTestCase() {
		Employee test = new SystemAdmin("sue", "bob", "540-333-3333", "suebob@mail.com", new LoginAccount("suebob", "pwd", 0));
		return test;
	}
	
	@Test
	void testGetNameFirst() {
		
		Employee test = makeTestCase();
		assertEquals(test.getNameFirst(), "sue");
		
	}

	@Test
	void testGetNameLast() {
		Employee test = makeTestCase();
		assertEquals(test.getNameLast(), "bob");
	}

	@Test
	void testGetPhoneNumber() {
		Employee test = makeTestCase();
		assertEquals(test.getPhoneNumber(), "540-333-3333");
	}

	@Test
	void testGetEmail() {
		Employee test = makeTestCase();
		assertEquals(test.getEmail(), "suebob@mail.com");
	}

/*	@Test
	void testGetLoginInfo() {
		Employee test = makeTestCase();
		assertEquals(test.getLoginInfo().getUsername(), "suebob");
	}

	@Test
	void testGetAll() {
		Employee test = makeTestCase();
		assertEquals(test.getAll(), ("sue", "bob", "540-333-3333", "suebob@mail.com", new LoginAccount("suebob", "pwd", 0)));
	}*/

}
