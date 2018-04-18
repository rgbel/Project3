package roster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.Scanner;

import application.BikePart;
import application.Main;
import application.Warehouse;



public class SalesAsso extends Employee implements Serializable {

	

	ArrayList<SalesInvoice> invoices;

	Warehouse van;

	static final double comRate = .15;

	

	public SalesAsso (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo, Warehouse van) {



		super(nameFirst, nameLast, phoneNumber, email, loginInfo);

		this.van = van;
		Main.whf.getProgramFleet().getFleet().add(this.van);
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

	

	public static double getComBetween(String name, Date start, Date end) {
		SalesAsso employ = null;
		for(Employee pos : Main.whf.getProgramRoster().getRoster()) {
			if(pos.getLoginInfo().getPermission() == 1) {
				if(name.equals(pos.getNameFirst()) || name.equals(pos.getNameLast())) {
					employ = (SalesAsso)pos;
					break;
				}
			}
		}
		if(employ!=null) {
			ArrayList<SalesInvoice> between = employ.getDatesBetween(start, end);

			double commission = 0;

			for(SalesInvoice tempInvoice : between) {

				commission += tempInvoice.getProfit();

			}

			return Math.round(commission * comRate);
		}
		return 0;
	}
	public boolean sellFromFile(String fileName) {
		// File format:
		// vanName
		// store,customer
		// partName,partNumber,partLP,partSP,partState,partQuanToSell
		// ...
		File partFile = new File(fileName);
		try {
			Scanner in = new Scanner(partFile);
			if(in.nextLine().equalsIgnoreCase(this.van.getName())) {
				String[] info = in.nextLine().split(",");
				SalesInvoice newIV = new SalesInvoice(info[0],info[1],this);
				while(in.hasNextLine()) {
					String[] bpInfo = in.nextLine().split(",");
					// If part found in van, sell it and add to invoice
					for(BikePart possible : this.getVan().getInv()) {
						if(possible.getName().equals(bpInfo[0])) {
							newIV.getParts().add(new BikePart(bpInfo[0],
												Integer.parseInt(bpInfo[1]),
												Double.parseDouble(bpInfo[2]),
												Double.parseDouble(bpInfo[3]),
												Boolean.parseBoolean(bpInfo[4]),
												Integer.parseInt(bpInfo[5])));
							possible.setStock(possible.getStock() - Integer.parseInt(bpInfo[5]));
							break;
						}
					}
				}
			this.addInvoice(newIV);
			return true;
			}
			else {
				return false;
			}
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	 

	



}