package roster;

import java.util.ArrayList;
import java.util.Date;

import application.BikePart;

public class SalesInvoice {

	String customerStore;
	String customerReceived;
	ArrayList<BikePart> partsSold;
	SalesAsso employeeSold;
	Date sellDate;
	
	public SalesInvoice(String customerStore, String customerReceived, ArrayList<BikePart> partsSold, SalesAsso employeeSold, Date sellDate) {
		
		this.customerStore = customerStore;
		this.customerReceived = customerReceived;
		this.partsSold = partsSold;
		this.employeeSold = employeeSold;
		this.sellDate = sellDate;
		
	}
	
	public String getStore() { return customerStore; }
	public String getCustomer() { return customerReceived; }
	public ArrayList<BikePart> getParts() { return partsSold; }
	public SalesAsso getEmployee() { return employeeSold; }
	public Date getDate() { return sellDate; }
	
	public String toString() {
		String output = "";
		output += ("Sales Invoice for " + customerStore + ", " + sellDate + "\n");
		output += ("Part Name		Part Number		Price	Sales Price		Quantity	Total Cost\n");
		for(BikePart loop : partsSold) {
			output += (loop.getAll());
		}
		
		return output;
	}
	
	
	
}
