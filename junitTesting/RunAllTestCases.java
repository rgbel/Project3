package junitTestCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

	@RunWith(Suite.class)

	@SuiteClasses({SalesInvoiceTest.class,LoginAccountTest.class,EmployeeTest.class})

public class RunAllTestCases {	

		//Running this class will run all TestCases, as long as they are in the SuiteClasses definition

		

}