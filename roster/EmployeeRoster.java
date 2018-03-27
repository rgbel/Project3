package roster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import application.Main;
import application.Warehouse;
import application.BikePart;

/**
 * EmployeeRoster keeps a running register of all employees created. Different types of Employees can be stored as long as they use the abstract Employee class.
 * 
 * @author 
 * Last updated 3/23/18
 */
public class EmployeeRoster {
	
	ArrayList<Employee> roster;
	
	public EmployeeRoster() { roster = new ArrayList<Employee>(); }
	public EmployeeRoster(ArrayList<Employee> roster) { this.roster = roster; }
	
	public ArrayList<Employee> getRoster() { return roster; }
	
	/**
	 * Takes information from a file of Employees and creates a roster based on the values.
	 * If the file is empty, a roster is created with a default admin to add additional users.
	 * @param filename - File to be read from (in .txt format).
	 * @return roster with all employees.
	 */
	public static EmployeeRoster fileToRoster(String filename) {
		// !,customerStore,customerEmployee,SaleDate
		// !!,partName,partNum,partListPrice,partSalesPrice,partOnSale,partQuan
		// ..
		// !,...
		// !!! signifies the end of the invoices
		EmployeeRoster register = new EmployeeRoster();
		SalesInvoice activeInvoice = new SalesInvoice();
		File warehouseFile = new File(filename);
		Employee newest = new SystemAdmin("John","Doe","123-540-6789","admin@bikeparts.org",new LoginAccount("admin","minda",0));
		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			@SuppressWarnings("resource")
			Scanner in = new Scanner(warehouseFile);
			String info[];
			SalesAsso lastSA = new SalesAsso("John","Doe","123-540-6789","admin@bikeparts.org",new LoginAccount("admin","minda",1),new Warehouse());
			boolean firstInvoice = true;
			if(!in.hasNextLine()) {
				register.roster.add(newest);
				return register;
			}
			while(in.hasNextLine()) {
				info = in.nextLine().split(",");
				if(info[0].equals("!")) {
					if(!firstInvoice) {
						lastSA.addInvoice(activeInvoice);	
					}
					String store = info[1];
					String employee = info[2];
					Long day = Long.parseLong(info[3]);
					SalesInvoice newInvoice = new SalesInvoice(store, employee, new ArrayList<BikePart>(), lastSA, day);
					activeInvoice = newInvoice;
					firstInvoice = false;
				}
				else if(info[0].equals("!!")) {
				// !!,partName,partNum,partListPrice,partSalesPrice,partOnSale,partQuan	
					String name = info[1];
					int number = Integer.parseInt(info[2]);
					double price = Double.parseDouble(info[3]);
					double salesprice = Double.parseDouble(info[4]);
					boolean onsale = Boolean.parseBoolean(info[5]);
					int stock = Integer.parseInt(info[6]);
					BikePart newPart = new BikePart(name,number,price,salesprice,onsale,stock);
					activeInvoice.getParts().add(newPart);
				}
				else if(info[0].equals("!!!")) {
					firstInvoice = true;
					lastSA.addInvoice(activeInvoice);
					register.getRoster().add(lastSA);
				}
				else if(info[6].equals("0")) {
					newest = parseLineToEmployee(info, new SystemAdmin());
					register.getRoster().add(newest);
				}
				else if(info[6] == "2") {
					newest = parseLineToEmployee(info, new OfficeMan());
					register.getRoster().add(newest);
				}
				else if(info[6] == "3") {
					newest = parseLineToEmployee(info, new WHMan());
					register.getRoster().add(newest);
				}
				else if(info[6] == "1") {
					lastSA = new SalesAsso();
					lastSA.setNameFirst(info[0]);
					lastSA.setNameLast(info[1]);
					lastSA.setPhoneNumber(info[2]);
					lastSA.setEmail(info[3]);
					lastSA.setLoginInfo(new LoginAccount(info[4],info[5],Integer.parseInt(info[6])));
					lastSA.setVan(Main.programFleet.getFleet().get(Main.programFleet.isWarehouse(info[7])));
				}
			}
			} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	return register;
	}
		
		/*		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			@SuppressWarnings("resource")
			Scanner in = new Scanner(warehouseFile);
			String info[];
			SalesAsso lastSA;
			if(!in.hasNextLine()) {
				register.roster.add(newest);
				return register;
			}
			boolean firstLoop = true;
			while(in.hasNextLine()) {
				// Format for all NORMAL employees:
				// nameF,nameL,phone,email,username,password,permissionL
				// Format for Sales Associates:
				// nameF,nameL,phone,email,username,password,permissionL,van
				info = (in.nextLine()).split(",");
				System.out.println(info[0]);
				if(info[0] == "!") {
					if(firstLoop) {
						
					}
					else {
						
					}
				}
				else if(info[0] == "**") {
					
				}
				else if(info[0] == "***") {
					
				}
				else if(info[6] == "0") {
					newest = parseLineToEmployee(info, new SystemAdmin());
				}
				else if(info[6] == "2") {
					newest = parseLineToEmployee(info, new OfficeMan());
				}
				else if(info[6] == "3") {
					newest = parseLineToEmployee(info, new WHMan());
				}
				else if(info[6] == "1") {
					lastSA = new SalesAsso();
					lastSA.setNameFirst(info[0]);
					lastSA.setNameLast(info[1]);
					lastSA.setPhoneNumber(info[2]);
					lastSA.setEmail(info[3]);
					lastSA.setLoginInfo(new LoginAccount(info[4],info[5],Integer.parseInt(info[6])));
					lastSA.setVan(Main.programFleet.getFleet().get(Main.programFleet.isWarehouse(info[7])));
					// *,customerStore,customerEmployee,SaleDate
					// **,partName,partNum,partListPrice,partSalesPrice,partOnSale,partQuan
					// ..
					// *,...
					// *** signifies the end of the invoices
					firstLoop = true;
					boolean looping = true;
					SalesInvoice activeInvoice = new SalesInvoice();
					while(looping) {
						info = in.nextLine().split(",");
						System.out.println("	" + info[0]);
						if(firstLoop) {
							if(info[0] == "*"){
								activeInvoice = new SalesInvoice(info[1],info[2],new ArrayList<BikePart>(),lastSA, Long.parseLong(info[3]));
								firstLoop = false;
							}
							// else, ***, exit loop
							else if(info[0] == "***"){
								break;
							}
						}
						else if(info[0] == "*") {
							lastSA.addInvoice(activeInvoice);
							activeInvoice = new SalesInvoice(info[1],info[2],new ArrayList<BikePart>(),lastSA, Long.parseLong(info[3]));
						}
						else if(info[0] == "**") {
							activeInvoice.addPart(new BikePart(info[1],
									Integer.parseInt(info[2]),
									Double.parseDouble(info[3]),
									Double.parseDouble(info[4]),
									Boolean.parseBoolean(info[5]),
									Integer.parseInt(info[6])));
						// Else, assume *** and go back to normal loop
						} 
						else if(info[0] == "***") { 
						lastSA.addInvoice(activeInvoice);
						break; 
						}
						
					}
					newest = lastSA;
				}
				register.roster.add(newest);
			}
		} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		return register;
	}*/
	
	/**
	 * Takes an occupied roster and print it into a .txt file.
	 * @param mainRoster - The roster to be added into the file.
	 * @param filename - The File where the roster should be added to.
	 * @return true if file is found and information is printed to it, false if printing information to file fails
	 */
	public boolean rosterToFile(EmployeeRoster mainRoster, String filename) {
		FileWriter empWriter;
		try {
			empWriter = new FileWriter(filename);
			for(Employee loop : this.roster) {
				if(loop.getLoginInfo().getPermission() != 1) {
					empWriter.write(loop.getAll() + "\n");
				} else {
					SalesAsso notLoop = (SalesAsso) loop;
					empWriter.write(notLoop.getAll() + "," + notLoop.getVan().getName() + "\n");
					for(SalesInvoice invoice : notLoop.getInvoices()) {
						ArrayList<String> invoiceTS = invoice.toStringCondensed();
						empWriter.write("!," + invoiceTS.get(0) + "\n");
						for(int i = 1; i < invoiceTS.size();i++) {
							empWriter.write("!!," + invoiceTS.get(i) + "\n");
						}
					}
					empWriter.write("!!!\n");
				}
			}
			empWriter.close();
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		return true;
	}
	/**
	 * Takes an array of Employee String characteristics and creates an Employee based on them.
	 * @param info - A String array of 7 different indices.
	 * @param newEmployee - The employee to be given these fields
	 * @return The employee with all the new fields
	 */
	private static Employee parseLineToEmployee(String[] info, Employee newEmployee) {
		
		newEmployee.setNameFirst(info[0]);
		newEmployee.setNameLast(info[1]);
		newEmployee.setPhoneNumber(info[2]);
		newEmployee.setEmail(info[3]);
		newEmployee.setLoginInfo(new LoginAccount(info[4],info[5],Integer.parseInt(info[6])));
		return newEmployee;
	}
	/**
	 * Find an employee and delete it from the current roster.
	 * If employee is a Sales Associate, return their van's parts to the main warehouse before deleting.
	 * @param deleteEmp - Reference to employee to be deleted
	 * @return Returns true if employee is deleted, returns false if employee is not found.
	 */
	public boolean deleteAccount(Employee deleteEmp) {
		// Finds the index of the employee in the roster; if it returns -1 they are not an employee
		int index = this.getRoster().indexOf(deleteEmp);
		if(index == -1) {
			return false;
		} else {
			if(deleteEmp.getLoginInfo().getPermission() != 1) {
				this.getRoster().remove(index);
			} else {
				// Since Sales Associates have to empty their sales van, they have to empty their van into the main warehouse
				SalesAsso notDeleteEmp = (SalesAsso) deleteEmp;
				Warehouse van = notDeleteEmp.getVan();
				// For each part, we are going to check if it already exists in the main warehouse.
				// If it does, just increase the stock.
				// Otherwise, add a new part.
				for(BikePart part : van.getInv()) {
					Warehouse mainWare = Main.programFleet.getFleet().get(0);
					int indexMain = mainWare.getPart(part.getName());
					if(indexMain != -1) {
						// Updating part stock
						BikePart mainPart = mainWare.getInv().get(indexMain);
						mainPart.setStock(mainPart.getStock() + part.getStock());
					} else {
						// Adding new part
						mainWare.addPart(part);
					}
				}
			this.getRoster().remove(index);
			}
		}
		
		return true;
	}
	/**
	 * Create a list of all employee's invoices that involve a specified customer.
	 * @param customerName - Customer to be searched in each invoice.
	 * @return An ArrayList of all matching invoices.
	 */
	public ArrayList<SalesInvoice> getInvoiceByCustomer(String customerName){
		ArrayList<SalesInvoice> invoices = new ArrayList<SalesInvoice>();
		for(Employee loop : this.roster) {
			if(loop.getLoginInfo().getPermission() == 1) {
				SalesAsso notLoop = (SalesAsso) loop;
				for(SalesInvoice testInvoice : notLoop.getInvoices()) {
					if(testInvoice.getCustomer().equals(customerName)) {
						invoices.add(testInvoice);
					}
				}
			}
		}
		return invoices;
	}
	/**
	 * Searches the current roster for all invoices that both:
	 *  - Involve a specified customer.
	 *  - Are between the two supplied dates.
	 * @param customerName
	 * @param before
	 * @param after
	 * @return
	 */
	public ArrayList<SalesInvoice> getInvoiceByCustomerBetweenDates(String customerName, Date start, Date end){
		ArrayList<SalesInvoice> invoices = new ArrayList<SalesInvoice>();
		for(Employee loop : this.roster) {
			if(loop.getLoginInfo().getPermission() == 1) {
				SalesAsso notLoop = (SalesAsso) loop;
				for(SalesInvoice testInvoice : notLoop.getDatesBetween(start, end)) {
					if(testInvoice.getCustomer().equals(customerName)) {
						invoices.add(testInvoice);
					}
				}
			}
		}
		return invoices;
	}


}
