package roster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.BikePart;

public class SalesInvoice {

	String customerStore;
	String customerReceived;
	ArrayList<BikePart> partsSold;
	SalesAsso employeeSold;
	Date sellDate;
	
	// This will format any printed invoices' date.
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd 'at' hh:mm:ss a zzz");
	
	public SalesInvoice(String customerStore, String customerReceived, ArrayList<BikePart> partsSold, SalesAsso employeeSold) {
		
		this.customerStore = customerStore;
		this.customerReceived = customerReceived;
		this.partsSold = partsSold;
		this.employeeSold = employeeSold;
		this.sellDate = new Date();
		
	}
	
	public SalesInvoice(String customerStore, String customerReceived, ArrayList<BikePart> partsSold, SalesAsso employeeSold, long date) {
		
		this.customerStore = customerStore;
		this.customerReceived = customerReceived;
		this.partsSold = partsSold;
		this.employeeSold = employeeSold;
		this.sellDate = new Date(date);
		
	}
	public SalesInvoice() {}
	
	public String getStore() { return customerStore; }
	public String getCustomer() { return customerReceived; }
	public ArrayList<BikePart> getParts() { return partsSold; }
	public SalesAsso getEmployee() { return employeeSold; }
	public Date getDate() { return sellDate; }
	
	public String toString() {
		String output = "";
		output += ("Sales Invoice for " + this.customerStore + ", " + dateFormat.format(sellDate) + "\n");
		output += String.format("%-14s %-16s %-10s %-14s %-16s\n","Part Name","Part Number","Price","Quantity","Total");
		for(BikePart loop : partsSold) {
			output += String.format("%-14s %-16s %-10s %-14s %-16s\n",loop.getName(),loop.getNumber(),loop.getActivePrice(),loop.getStock(),loop.getTotalCost());
		}
		output += String.format("%-14s %-16s %-10s %-14s %-16s\n","Total Cost:","","","",this.getProfit());
		output += ("Sold by: " + this.getEmployee().getNameFirst() + " " + this.getEmployee().getNameLast() + "\n");
		output += ("Received by: " + this.customerReceived);
		return output;
	}
	
	public double getProfit() {
		double commission = 0;
		for(BikePart tempPart : this.getParts()) {
			commission += tempPart.getTotalCost();
		}
		return commission;
	}
	
	
	
}
