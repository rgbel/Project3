package application;
import java.io.File;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class Warehouse {
	
	String name;
	ArrayList<BikePart> partInventory = new ArrayList<BikePart>();
	
	/**
	 * 
	 * @param iname
	 * @param ipartInventory
	 */
	public Warehouse(String iname, ArrayList<BikePart> ipartInventory){
		name = iname;
		partInventory = ipartInventory;
	}
	public Warehouse() {	 }
	public Warehouse(String iname) {
		name = iname;
	}
	public String getName() {
		return name;
	}
	public ArrayList<BikePart> getInv(){
		return partInventory;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void addPart(BikePart part){
		partInventory.add(part);
	}
	// Does not work as intended; needs rework
	// This should:
	// Find part in gW, remove amount of said part, go to sW, search for part, if true, add quantity, if false, add part
	// This method already exists more fully in WarehouseFleet, useless method?
	public boolean transferPart(Warehouse getWarehouse, Warehouse setWarehouse, String partName){
		BikePart transferPart = BikePart.displayPart(getWarehouse.getInv(), partName);
		if(transferPart.getName() != null) {
			setWarehouse.addPart(transferPart);
			getWarehouse.getInv().remove(transferPart);
			return true;
		}
		else {
			return false;
		}
	}
	public void updateInventory(String fileName) {

		//String temp = JOptionPane.showInputDialog("Enter the file name: ");
		File partFile = new File(fileName);
		ArrayList<BikePart> fileList = new ArrayList<BikePart>();
		// Try / Catch in case of empty file or mistake on user's behalf (prevent full error-out)
		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			Scanner bikeScanner = new Scanner(partFile);
			while (bikeScanner.hasNextLine()) {
				String bpLine = bikeScanner.nextLine();
				// The Pattern class allows us to make sure that the file has the data in the way the coder wants
				// Here, we are checking that it is a string of letters and numbers (\\w) followed by a comma, followed by a number (\\d), and so on.
				// An 'or' must be used to make sure the final value is either true or false (cannot simply check for a word).
				if (Pattern.matches("\\w*,\\d*,\\d*.\\d*,\\d*.\\d*,false,\\d*", bpLine) || Pattern.matches("\\w*,\\d*,\\d*.\\d*,\\d*.\\d*,true,\\d*", bpLine)){
					// .split creates an array of Strings that is divided each time you encounter a certain character or String (comma, in our case)
					String[] partData = bpLine.split(",");

					//System.out.println(partData[4]);
					// BikePart would not let us properly parse Strings as other variable types when using the original constructor
					// Thus, we created the empty constructor and added in the values one at a time to solve this issue.
					BikePart tempPart = new BikePart(); 
					tempPart.setName(partData[0]);
					tempPart.setNumber(Integer.parseInt(partData[1]));
					tempPart.setPrice(Double.parseDouble(partData[2]));
					tempPart.setSalesPrice(Double.parseDouble(partData[3]));
					tempPart.setOnSale(Boolean.parseBoolean(partData[4]));
					tempPart.setStock(Integer.parseInt(partData[5]));
					//System.out.println(tempPart.getSalesPrice());
					fileList.add(tempPart);
				}
			}


		} catch (FileNotFoundException e) {
			System.out.println("File not found. Empty inventory created.");
		}
		// Search loop for an existing part
		boolean notFound = true;
		for(BikePart loopPart : fileList){
			notFound = true;
			for (BikePart testPart : this.getInv()){
				// If said part exists, modify the stock, prices, and getSale values.
				if ((loopPart.getName()).equals(testPart.getName())){
					testPart.setStock(testPart.getStock() + loopPart.getStock());
					testPart.setPrice(loopPart.getPrice());
					testPart.setSalesPrice(loopPart.getSalesPrice());
					testPart.setOnSale(loopPart.getOnSale());
					notFound = false;
					break;
				}
			}
			// Else, add the part to a next index of the array
			if (notFound)
				this.getInv().add(loopPart);
		}
	}
	public BikePart searchPart(String searchName) {
		for(BikePart testPart : this.getInv()) {
			if (testPart.getName().equalsIgnoreCase(searchName))
				return testPart;
		}
		return null; 
	}
	
		public int getPart(String name2) {
		int FOUNDINDEX = -1;
		for (int i = 0; i < this.partInventory.size(); i++) {
			if(this.partInventory.get(i).getName().equals(name2)) {
				FOUNDINDEX = i;
				break;
			}
		} // done searching
		return FOUNDINDEX; //only if part is found
	} //getPart
	
	
}

