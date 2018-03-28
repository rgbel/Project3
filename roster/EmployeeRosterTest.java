package roster;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import application.*;
import roster.*;

class EmployeeRosterTest {
	
	EmployeeRoster getTestCase() {
		ArrayList<Employee> register = new ArrayList<Employee>();
		register.add(new SystemAdmin("Default","Admin","555-555-5555","admin@bikeparts.org",new LoginAccount("admin","minda",0)));
		SalesAsso asd =  new SalesAsso("Sales","Associate","111-111-1111","sa@bikeparts.org", new LoginAccount("sales","associate",1), Main.programFleet.getFleet().get(0));
		BikePart part1 = new BikePart("a",123,4,3,true,5);
		SalesInvoice meh = new SalesInvoice("sue's shop","sue",new ArrayList<BikePart>(),asd);
		meh.partsSold.add(part1);
		asd.invoices.add(meh);
		System.out.println(asd.getLoginInfo().getPermission());
		register.add(asd);
		return new EmployeeRoster(register);
	}

	@Test
	void testFileToRoster() {
		fail("Not yet implemented");
	}

	@Test
	void testRosterToFile() {
		EmployeeRoster aaa = getTestCase();
		aaa.rosterToFile(aaa,"employeeDB.txt","associateDB.txt");
		EmployeeRoster someoa = EmployeeRoster.fileToRoster("employeeDB.txt", "associateDB.txt");
		for(int i = 0; i < someoa.roster.size();i++)		
			System.out.println(someoa.getRoster().get(i).getAll());
	}

}
