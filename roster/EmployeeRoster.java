package roster;

import java.util.ArrayList;

/**
 * EmployeeRoster keeps a running register of all employees created. Different types of Employees can be stored as long as they use the abstract Employee class.
 * 
 * @author mattpess
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
		EmployeeRoster roster = new EmployeeRoster();
		// Codes goes here
		return roster;
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

}
