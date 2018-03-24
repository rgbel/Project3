package roster;
import java.util.ArrayList;
import java.util.Date;

import application.Warehouse;

public class SalesAsso extends Employee {
	
	ArrayList<SalesInvoice> invoices;
	Warehouse van;
	
	public SalesAsso (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo, ArrayList<SalesInvoice> invoices, Warehouse van) {

		super(nameFirst, nameLast, phoneNumber, email, loginInfo);
		this.invoices = invoices;
		this.van = van;
		this.getLoginInfo().changePermission(1);

	}
	
	public SalesAsso() {}
	
	public ArrayList<SalesInvoice> getDatesBetween(Date start, Date end){
		// Needs to be implemented
		
		
	}

}

	
