package roster;

import java.util.ArrayList;

import java.util.Date;



import application.Warehouse;



public class SalesAsso extends Employee {

	

	ArrayList<SalesInvoice> invoices;

	Warehouse van;

	static final double comRate = .15;

	

	public SalesAsso (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo, Warehouse van) {



		super(nameFirst, nameLast, phoneNumber, email, loginInfo);

		this.van = van;

		this.getLoginInfo().changePermission(1);

		invoices = new ArrayList<SalesInvoice>();



	}

	

	public SalesAsso() {}

	

	public Warehouse getVan() { return van; }

	public void setVan(Warehouse van) { this.van = van; }

	

	public void addInvoice(SalesInvoice newInvoice) { invoices.add(newInvoice); }

	public ArrayList<SalesInvoice> getInvoices() { return invoices; }

	

	public ArrayList<SalesInvoice> getDatesBetween(Date start, Date end){

		ArrayList<SalesInvoice> between = new ArrayList<SalesInvoice>();

		for(SalesInvoice temp : invoices) {

			Date tempDate = temp.getDate();

			if(tempDate.after(start) && tempDate.before(end)) {

				between.add(temp);

			}

		}

		return between;

	}

	

	public double getComBetween(Date start, Date end) {

		ArrayList<SalesInvoice> between = getDatesBetween(start, end);

		double commission = 0;

		for(SalesInvoice tempInvoice : between) {

			commission += tempInvoice.getProfit();

		}

		return commission * comRate;

	}

	 

	



}