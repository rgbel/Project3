package junitTestCases;

import static org.junit.jupiter.api.Assertions.*;
import roster.*;
import org.junit.jupiter.api.Test;

class LoginAccountTest {
	LoginAccount makeTestCase() {

		LoginAccount test = new LoginAccount("username", "password", 3); //WHMan
		return test;
	}

	
	@Test
	void testGetUsername() {

		LoginAccount test = makeTestCase();
		assertEquals(test.getUsername(),"username");
		
	}

	@Test
	void testGetPermission() {
		LoginAccount test = makeTestCase();
		assertEquals(test.getPermission(),3);
	}

	@Test
	void testResetPassword() {
		LoginAccount test = makeTestCase();
		test.resetPassword("newpassword");
		assertEquals(test.getPassword(),"newpassword");
		
	}

	@Test
	void testGetPassword() {
		LoginAccount test = makeTestCase();
		assertEquals(test.getPassword(),"password");
	}
	
	/**
	 * 
	public boolean confirmUsername(String attemptUsername) { return(attemptUsername.equals(this.username)); }

	public boolean confirmPassword(String attemptPassword) { return(attemptPassword.equals(this.password)); }
	 */

	@Test
	void testConfirmUsername() {
		LoginAccount test = makeTestCase();
		assertEquals(test.confirmUsername("username"),true);
		
	}

	@Test
	void testConfirmPassword() {
		LoginAccount test = makeTestCase();
		assertEquals(test.confirmPassword("password"),true);
	}

}
