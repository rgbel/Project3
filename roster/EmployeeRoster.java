package roster;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
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

public class EmployeeRoster implements Serializable{

	

	ArrayList<Employee> roster;

	

	public EmployeeRoster() { roster = new ArrayList<Employee>(); roster.add(new SystemAdmin("Default","Admin","555-555-5555","admin@bikeparts.org",new LoginAccount("admin","minda",0)));}

	public EmployeeRoster(ArrayList<Employee> roster) { this.roster = roster; }

public ArrayList<Employee> getRoster() { return roster; }
	
	/**
	 * Takes information from a file of Employees and creates a roster based on the values.
	 * If the file is empty, a roster is created with a default admin to add additional users.
	 * @param filename - File to be read from (in .txt format).
	 * @return roster with all employees.
	 */
	public static EmployeeRoster fileToRoster(String filename, String filename2) {
		
		// Access files
		// Read regular employees from file one and put them into the register
		// Read sales associates from file two and put them into the register
		// Return register
		File empFile = new File(filename);
		File assoFile = new File (filename2);
		String[] info;
		ArrayList<Employee> register = new ArrayList<Employee>();
		try {
			Scanner in = new Scanner(empFile);
			if(!in.hasNextLine()) {
				in.close();
				register.add(new SystemAdmin("Default","Admin","555-555-5555","admin@bikeparts.org",new LoginAccount("admin","minda",0)));
				return new EmployeeRoster(register);
				
			}
			while(in.hasNextLine()) {
				//nameFirst,nameLast,phoneNumber,email,username,password,permissionLevel
				info = in.nextLine().split(",");
				if(info[6].equals("0"))
					register.add(new SystemAdmin(info[0],info[1],info[2],info[3], new LoginAccount(info[4],info[5],Integer.parseInt(info[6]))));
				else if(info[6].equals("2"))
					register.add(new OfficeMan(info[0],info[1],info[2],info[3], new LoginAccount(info[4],info[5],Integer.parseInt(info[6]))));
				else if(info[6].equals("3"))
					register.add(new WHMan(info[0],info[1],info[2],info[3], new LoginAccount(info[4],info[5],Integer.parseInt(info[6]))));
				}
			in = new Scanner(assoFile);
			int index = -1;
			SalesAsso lastSA = new SalesAsso();
			while(in.hasNextLine()) {
				//nameFirst,nameLast,phoneNumber,email,username,password,permissionLevel,van
				//!,customerStore,customerEmployee,SaleDate
				//!!,partName,partNum,partListPrice,partSalesPrice,partOnSale,partQuan
				//!!!
				info = in.nextLine().split(",");
				if(info[0].equals("!")) {
					index++;
					lastSA.invoices.add(new SalesInvoice(info[1],info[2], new ArrayList<BikePart>(), lastSA, Long.parseLong(info[3])));
				}
				else if(info[0].equals("!!")) {
					BikePart newPart = new BikePart();
					newPart.setName(info[1]);
					newPart.setNumber(Integer.parseInt(info[2]));
					newPart.setPrice(Double.parseDouble(info[3]));
					newPart.setSalesPrice(Double.parseDouble(info[4]));
					newPart.setOnSale(Boolean.getBoolean(info[5]));
					newPart.setStock(Integer.parseInt(info[6]));
					lastSA.invoices.get(index).getParts().add(newPart);
				}
				else if(info[0].equals("!!!")) {
					index = -1;
					register.add(lastSA);
				}
				else {
					lastSA = new SalesAsso(info[0],info[1],info[2],info[3], new LoginAccount(info[4],info[5],Integer.parseInt(info[6])), Main.whf.getProgramFleet().getFleet().get(Main.whf.getProgramFleet().isWarehouse(info[7])));
				}
			}
			in.close();	
		} catch (FileNotFoundException e) {
			System.out.println("File one or two not found or are empty.");
			e.printStackTrace();
		}
		
	return new EmployeeRoster(register);
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

	 * Take a existing roster of employees.
	 * All SalesAssociates will be written to the second file. All remaining employees will be written to the first file
	 * We are separating the two as a way of mitigating errors revolving around invoices and bike parts attached to the sales associate
	 * @param mainRoster - The roster to be added into the file.
	 * @param filename and filename2 - The File where the roster should be added to.
	 * @return true if file is found and information is printed to it, false if printing information to file fails
	 */

	public boolean rosterToFile(EmployeeRoster mainRoster, String filename, String filename2) {

		FileWriter empWriter;
		FileWriter assoWriter;
		ArrayList<SalesAsso> list = new ArrayList<SalesAsso>();
		try {
			empWriter = new FileWriter(filename);
			assoWriter = new FileWriter("associateDB.txt");
			for(Employee loop : this.roster) {
				if(loop.getLoginInfo().getPermission() != 1) {
					empWriter.write(loop.getAll() + "\n");
				} else {
					/*System.out.println("A 1");
					SalesAsso notLoop = (SalesAsso) loop;
					assoWriter.write(notLoop.getAll() + "," + notLoop.getVan().getName() + "\n");
					for(SalesInvoice invoice : notLoop.getInvoices()) {
						ArrayList<String> invoiceTS = invoice.toStringCondensed();
						assoWriter.write("!," + invoiceTS.get(0) + "\n");
						for(int i = 1; i < invoiceTS.size();i++) {
							assoWriter.write("!!," + invoiceTS.get(i) + "\n");
						}
					}
					assoWriter.write("!!!\n");*/
				list.add((SalesAsso)loop);	
				}

				for(SalesAsso SA : list) {
					assoWriter.write(SA.getAll() + "," + SA.getVan().getName() + "\n");
					for(SalesInvoice invoice : SA.getInvoices()) {
						ArrayList<String> invoiceTS = invoice.toStringCondensed();
						assoWriter.write("!," + invoiceTS.get(0) + "\n");
						for(int i = 1; i < invoiceTS.size();i++) {
							assoWriter.write("!!," + invoiceTS.get(i) + "\n");
						}
					}
					assoWriter.write("!!!\n");
					
				}
			}
			assoWriter.close();
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
				Warehouse mainWare = Main.whf.getProgramFleet().getFleet().get(0);
				for(BikePart part : van.getInv()) {
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