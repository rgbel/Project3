package roster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Main;
import application.BikePart;

/**
 * EmployeeRoster keeps a running register of all employees created. Different types of Employees can be stored as long as they use the abstract Employee class.
 * 
 * @author 
 * Last updated 3/23/18
 */
public class EmployeeRoster {
	
	ArrayList<Employee> roster;
	
	public EmployeeRoster() { }
	public EmployeeRoster(ArrayList<Employee> roster) { this.roster = roster; }
	
	public ArrayList<Employee> getRoster() { return roster; }
	
	/**
	 * Takes information from a file of Employees and creates a roster based on the values.
	 * If the file is empty, a roster is created with a default admin to add additional users.
	 * @param filename - File to be read from (in .txt format).
	 * @return roster with all employees.
	 */
	public EmployeeRoster fileToRoster(String filename) {
		EmployeeRoster register = new EmployeeRoster();
		File warehouseFile = new File(filename);
		Employee newest;
		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			@SuppressWarnings("resource")
			Scanner in = new Scanner(warehouseFile);
			String info[];
			SalesAsso lastSA;
			if(in.hasNextLine()) {
				info = (in.nextLine()).split(",");
				newest = new SystemAdmin(info[0],info[1],info[2],info[3], new LoginAccount(info[4],info[5],Integer.parseInt(info[6])));
			}
			else {
				newest = new SystemAdmin("John","Doe","123-540-6789","admin@bikeparts.org",new LoginAccount("admin","minda",0));
				register.roster.add(newest);
				return register;
			}
			register.roster.add(newest);
			while(in.hasNextLine()) {
				
				info = (in.nextLine()).split(",");
				if(info[6].equals("0")) {
					newest = parseLineToEmployee(info, new SystemAdmin());
				} else if (info[6].equals("2")) {
					newest = parseLineToEmployee(info, new OfficeMan());
				} else if (info[6].equals("3")) {
					newest = parseLineToEmployee(info, new WHMan());
				} else if (info[6].equals("1")) {
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
					boolean firstLoop = true;
					SalesInvoice activeInvoice = new SalesInvoice();
					while(in.hasNextLine()) {
						info = in.nextLine().split(",");
						if(firstLoop) {
							if(info[0] == "*"){
								activeInvoice = new SalesInvoice(info[1],info[2],new ArrayList<BikePart>(),lastSA, Long.parseLong(info[3]));
								firstLoop = false;
							}
							// else, ***, exit loop
							else {
								break;
							}
						}
						else if(info[0] == "*") {
							lastSA.addInvoice(activeInvoice);
							activeInvoice = new SalesInvoice(info[1],info[2],new ArrayList<BikePart>(),lastSA, Long.parseLong(info[3]));
						}
						else if(info[0] == "**") {
							//Add part to activeInvoice
						} else { break; }
						
					}
				}
				register.roster.add(newest);
			}
		} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		// Codes goes here
		return register;
	}
	
	/**
	 * Takes an occupied roster and print it into a .txt file.
	 * @param eRoster - The roster to be added into the file.
	 * @param filename - The File where the roster should be added to.
	 * @return true if file is found and information is printed to it, false if printing information to file fails
	 */
	public boolean rosterToFile(EmployeeRoster eRoster, String filename) {
		// Code goes here
		return true;
	}
	
	public static Employee parseLineToEmployee(String[] info, Employee newEmployee) {
		
		newEmployee.setNameFirst(info[0]);
		newEmployee.setNameLast(info[1]);
		newEmployee.setPhoneNumber(info[2]);
		newEmployee.setEmail(info[3]);
		newEmployee.setLoginInfo(new LoginAccount(info[4],info[5],Integer.parseInt(info[6])));
		return newEmployee;
	}

}
