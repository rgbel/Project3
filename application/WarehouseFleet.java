package application;
import java.io.File;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * WarehouseFleet allows a user to control an arraylist of warehouses and manipulate or sort them as needed. On open, previous data is loaded from a .txt file, and on close this data is written back to the same file.
 * @author Matthew Pessolano
 *	Like the EmployeeRoster that will 
 */
public class WarehouseFleet implements Serializable {
	
	ArrayList<Warehouse> fleet;
	
	
	public WarehouseFleet(ArrayList<Warehouse> ifleet) {
		fleet = ifleet;
	}
	/**
	 * Get the existing fleet of Warehouses in a WHFleet object.
	 * @return an ArrayList of Warehouses
	 */
	public ArrayList<Warehouse> getFleet(){
		
		return fleet;
		
	}
	/**
	 * isWarehouse searches for a warehouse among the existing fleet.
	 * Works exactly like indexOf() but does not require an object input
	 * @param find - The name of the warehouse to be searched for.
	 * @return Returns index of warehouse in fleet if name matches, else returns -1.
	 */
	public int isWarehouse(String find) {
		int index = 0;
		for(Warehouse loopWare : fleet) {
			if (loopWare.getName().equals(find)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	/**
	 * Transform a file into a fleet of warehouses
	 * Expected file format:
	 * warehouseName
	 * partname,partID,partprice,partsaleprice,onsale,quantity
	 * ...
	 * warehouseName2
	 * etc. etc.
	 * @param fileName - where to find the file
	 * @return fileList - the transcribed ArrayList of warehouses
	 */
	public static ArrayList<Warehouse> fileToFleet(String fileName) {
		File warehouseFile = new File(fileName);
		ArrayList<Warehouse> fileList = new ArrayList<Warehouse>();
		
		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			@SuppressWarnings("resource")
			Scanner bikeScanner = new Scanner(warehouseFile);
			int index = 0;
			String mainWHname;
			if(bikeScanner.hasNextLine()) {
				mainWHname = bikeScanner.nextLine();
			}
			else {
				mainWHname = "mainWarehouse";
			}
			fileList.add(new Warehouse(mainWHname));
			Warehouse currentWarehouse = new Warehouse(mainWHname);
			currentWarehouse = fileList.get(index);
			index++;
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
					currentWarehouse.addPart(tempPart);
				}
				else {
					fileList.add(new Warehouse(bpLine));
					currentWarehouse = fileList.get(index);
					index++;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found or does not contain values. Empty inventory created.");
		}
		return fileList;
	}
	/**
	 * Take the existing WHFleet and transfer it to the indicated txt file.
	 * @param fileName, The String name of the output file.
	 */
	public void fleetToFile(String fileName) {
		// This value can be edited if a new database file is needed
		String database = fileName;
		FileWriter partWriter;
		try {
			partWriter = new FileWriter(database);
			for(Warehouse tWare : getFleet()){
				partWriter.write(tWare.getName() + "\n");
				for(BikePart tPart : tWare.getInv()) {
					partWriter.write(tPart.getAll() + "\n");
				}
			}
			partWriter.close();
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	/**
	 * This method sorts data in a singular warehouse, or all warehouses, using a bubble sort, sorting by alphabetical order.
	 * In order to do this without altering the arrangement of the actual array listing, a new temporary array was created.
	 * After "new" parts were added into this array (the parts added would depend on the choice), the bubble sort occurred.
	 * If multiple warehouses are chosen, the method checks for duplicate parts and combines them.
	 * Afterwards, a bubble sort is applied and the array is returned.
	 * @param choice, The choice of which warehouse to sort or "all" warehouses.
	 * @return A sorted ArrayList of BikeParts
	 */
	public ArrayList<BikePart> alphaSort(String choice){
		ArrayList<BikePart> displayArray = new ArrayList<BikePart>();
		if(choice.equals("all")) {
			for(Warehouse loopW : getFleet()) {
				for(BikePart loopP : loopW.getInv()) {
					BikePart tempP = new BikePart(); 

					tempP.setName(loopP.getName());
					tempP.setNumber(loopP.getNumber());
					tempP.setPrice(loopP.getPrice());
					tempP.setSalesPrice(loopP.getSalesPrice());
					tempP.setOnSale(loopP.getOnSale());
					tempP.setStock(loopP.getStock());
					
					boolean notFound = true;
					for(BikePart testPart : displayArray){
							// If said part exists, modify the stock, prices, and getSale values.
							if ((testPart.getName()).equals(tempP.getName())){
								testPart.setStock(testPart.getStock() + tempP.getStock());
								testPart.setPrice(tempP.getPrice());
								testPart.setSalesPrice(tempP.getSalesPrice());
								testPart.setOnSale(tempP.getOnSale());
								notFound = false;
								break;
							}
						}
					if(notFound)
						displayArray.add(tempP);
				}
			}
			return alphaBubbleSort(displayArray);

		}
		int wareIndex = isWarehouse(choice);
		if(wareIndex > -1) {
			Warehouse chosenWH = this.getFleet().get(wareIndex);
			for(BikePart loopP : chosenWH.getInv()) {
				BikePart tempP = new BikePart(); 

				tempP.setName(loopP.getName());
				tempP.setNumber(loopP.getNumber());
				tempP.setPrice(loopP.getPrice());
				tempP.setSalesPrice(loopP.getSalesPrice());
				tempP.setOnSale(loopP.getOnSale());
				tempP.setStock(loopP.getStock());
				
				boolean notFound = true;
				for(BikePart testPart : displayArray){
						// If said part exists, modify the stock, prices, and getSale values.
						if ((testPart.getName()).equals(tempP.getName())){
							testPart.setStock(testPart.getStock() + tempP.getStock());
							testPart.setPrice(tempP.getPrice());
							testPart.setSalesPrice(tempP.getSalesPrice());
							testPart.setOnSale(tempP.getOnSale());
							notFound = false;
							break;
						}
					}
				if(notFound)
					displayArray.add(tempP);
			}
			return alphaBubbleSort(displayArray);
		}
		
		
		return displayArray;
	}
	/**
	 * This method sorts data in a singular warehouse, or all warehouses, using a bubble sort, sorting by numerical order.
	 * In order to do this without altering the arrangement of the actual array listing, a new temporary array was created.
	 * After "new" parts were added into this array (the parts added would depend on the choice), the bubble sort occurred.
	 * If multiple warehouses are chosen, the method checks for duplicate parts and combines them.
	 * Afterwards, a bubble sort is applied and the array is returned.
	 * @param choice, The user's choice of warehouse or "all" warehouses.
	 * @return A sorted array of BikeParts
	 */
	public ArrayList<BikePart> numSort(String choice){
		ArrayList<BikePart> displayArray = new ArrayList<BikePart>();
		if(choice.equals("all")) {
			for(Warehouse loopW : getFleet()) {
				for(BikePart loopP : loopW.getInv()) {
					BikePart tempP = new BikePart(); 

					tempP.setName(loopP.getName());
					tempP.setNumber(loopP.getNumber());
					tempP.setPrice(loopP.getPrice());
					tempP.setSalesPrice(loopP.getSalesPrice());
					tempP.setOnSale(loopP.getOnSale());
					tempP.setStock(loopP.getStock());
					
					boolean notFound = true;
					for(BikePart testPart : displayArray){
							// If said part exists, modify the stock, prices, and getSale values.
							if ((testPart.getName()).equals(tempP.getName())){
								testPart.setStock(testPart.getStock() + tempP.getStock());
								testPart.setPrice(tempP.getPrice());
								testPart.setSalesPrice(tempP.getSalesPrice());
								testPart.setOnSale(tempP.getOnSale());
								notFound = false;
								break;
							}
						}
					if(notFound)
						displayArray.add(tempP);
				}
			}
			return numBubbleSort(displayArray);
		}
		int wareIndex = isWarehouse(choice);
		if(wareIndex > -1) {
			Warehouse chosenWH = this.getFleet().get(wareIndex);
			for(BikePart loopP : chosenWH.getInv()) {
				BikePart tempP = new BikePart(); 

				tempP.setName(loopP.getName());
				tempP.setNumber(loopP.getNumber());
				tempP.setPrice(loopP.getPrice());
				tempP.setSalesPrice(loopP.getSalesPrice());
				tempP.setOnSale(loopP.getOnSale());
				tempP.setStock(loopP.getStock());
				
				boolean notFound = true;
				for(BikePart testPart : displayArray){
						// If said part exists, modify the stock, prices, and getSale values.
						if ((testPart.getName()).equals(tempP.getName())){
							testPart.setStock(testPart.getStock() + tempP.getStock());
							testPart.setPrice(tempP.getPrice());
							testPart.setSalesPrice(tempP.getSalesPrice());
							testPart.setOnSale(tempP.getOnSale());
							notFound = false;
							break;
						}
					}
				if(notFound)
					displayArray.add(tempP);
			}
			return numBubbleSort(displayArray);
		}
		
		
		return displayArray;
	}
	/**
	 * Reads a file, pulling the warehouse names from the first line that will be giving and taking bike parts.
	 * It will then form a new array of the prospective bike parts and their quantities.
	 * First, it will remove quantities from the parts and delete them if they become zero.
	 * Next, it will take the array of bike parts and add them to the receive warehouse.
	 * @param fileName, The transfer file to be read.
	 */
	public void transferParts(String fileName) {
		
		File partFile = new File(fileName);
		// Try / Catch in case of empty file or mistake on user's behalf (prevent full error-out)
		Warehouse takeW = new Warehouse("empty");
		Warehouse receiveW = new Warehouse("empty");
		ArrayList<BikePart> takeArray = new ArrayList<BikePart>();
		try {
			// Set to scan the file, each time checking for an additional line before proceeding
			@SuppressWarnings("resource")
			Scanner bikeScanner = new Scanner(partFile);
			String bpLine = bikeScanner.nextLine();
			String[] warehouseData = bpLine.split(",");
			takeW = this.getFleet().get(this.isWarehouse(warehouseData[0]));
			receiveW = this.getFleet().get(this.isWarehouse(warehouseData[1]));
			
			// Handles part removal; puts part values into takeData<>
			while (bikeScanner.hasNextLine()) {
				bpLine = bikeScanner.nextLine();
				String[] takeData = bpLine.split(",");
				for(BikePart testPart : takeW.getInv()) {
					if(testPart.getName().equals(takeData[0])) {
						BikePart takePart = new BikePart(); 
						
						takePart.setName(takeData[0]);
						takePart.setNumber(testPart.getNumber());
						takePart.setPrice(testPart.getPrice());
						takePart.setSalesPrice(testPart.getSalesPrice());
						takePart.setOnSale(testPart.getOnSale());
						takePart.setStock(Integer.parseInt(takeData[1]));
						
						takeArray.add(takePart);
						testPart.setStock(testPart.getStock() - Integer.parseInt(takeData[1]));
						if (testPart.getStock() <= 0) {
							takeW.getInv().remove(testPart);
						}
						
					}
			
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		// Search loop for an existing part
		boolean notFound = true;
		for(BikePart loopPart : takeArray){
			notFound = true;
			for (BikePart testPart : receiveW.getInv()){
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
				receiveW.getInv().add(loopPart);
		}
	}
	/**
	 * This method is mostly used for internal testing by taking a fleet and printing out the current active Warehouses and their BikeParts.
	 * @return A string with all Warehouses and their BikeParts.
	 */
	public String listParts() {
		String returnString = "";
		for(Warehouse tWare : this.getFleet()){
			returnString += (tWare.getName() + "\n");
			for(BikePart tPart : tWare.getInv()) {
				returnString += (tPart.getAll() + "\n");
			}
		}
		return returnString;
		
	}
	/**
	 * Works similarly to listParts, but can be statically called on a singular array of BikeParts when returning a String of BikePart data.
	 * This prints the BikePart data in the same format that would be seen in a file (name,number,price,saleprice,onsale,quantity).
	 * @param array - The array to be printed
	 * @return
	 */
	public static String listArray(ArrayList<BikePart> array) {
		String returnString = "";
		for(BikePart bp : array){
			returnString += (bp.getAll() + "\n");
		}
		return returnString;
		
	}
	public void newVan(String vanName) {
		
		this.getFleet().add(new Warehouse(vanName));
		
	}
	/**
	 * Checks all warehouses in order for a part of a certain number, returning info about it if found and decrementing it.
	 * The decision between FromOne and FromAll is chosen by the Controller based on user input. This is also where we will receive the warehouse name (if it is FromOne).
	 * @param number - The value being searched for in each part
	 * @return Outputs either info about a part or an error message : String.
	 */
	public String sellFromAll(String number) {
		for(Warehouse sellW : this.getFleet()) {
			for(BikePart sellP : sellW.getInv()) {
				if(sellP.getNumber() == Integer.parseInt(number)) {
					sellP.setStock(sellP.getStock() - 1);
					String output = ("Part sold.\n");
					output += ("Part name: " + sellP.getName() + "\n");
					// A getActivePrice() method simplified this section; now a if-else is not needed.
					output += ("Part sold for: $" + sellP.getActivePrice() + "\n");
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date date = new Date();
					output += ("Sold on " + dateFormat.format(date) + "\n");
					if (sellP.getStock() < 1) {
						output+=("Part is now out of stock; removed from inventory of " + sellW.getName());
						sellW.getInv().remove(sellP);
					}
					return(output);
				}
			}
		}
	return("Part not found in any warehouse. Check your input ID number and try again");
	}
	
	/**
	 * Takes a singular warehouse from a fleet and searches for a part by number. If found, return name, price, and time sold, and decrements.
	 * The decision between FromOne and FromAll is chosen by the Controller based on user input. This is also where we will receive the warehouse name (if it is FromOne).
	 * @param number, The ID used to search for a part
	 * @param warehouse, The warehouse in which a part is searched for in.
	 * @return Returns an error message if the part is not found, or information about the part otherwise (String).
	 */
	public String sellFromOne(String number, String warehouse) {
		Warehouse searchW = this.getFleet().get(this.isWarehouse(warehouse));
			for(BikePart sellP : searchW.getInv()) {
				if(sellP.getNumber() == Integer.parseInt(number)) {
					sellP.setStock(sellP.getStock() - 1);
					String output = ("Part sold.\n");
					output += ("Part name: " + sellP.getName() + "\n");
					// This if-else checks which value should be used for the price output (checks OnSale value)
					output += ("Part sold for: $" + sellP.getActivePrice() + "\n");
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date date = new Date();
					output += ("Sold on " + dateFormat.format(date) + "\n");
					if (sellP.getStock() < 1) {
						output+=("Part is now out of stock; removed from inventory of " + searchW.getName());
						searchW.getInv().remove(sellP);
					}
					return(output);
				}
			}
		return("Part not found in any warehouse. Check your input ID number and try again");
	}
	/**
	 * Sorts an array of BikeParts by numerical order.
	 * @param inputArray - The array of parts to be sorted
	 * @return A sorted array of bike parts
	 */
	private ArrayList<BikePart> numBubbleSort(ArrayList<BikePart> inputArray){
		for (int count = 0; count < inputArray.size(); count++) {
	        for (int count2 = 1; count2 < inputArray.size() - count; count2++) {
	            if ((inputArray.get(count2 -1).getNumber()) > (inputArray.get(count2).getNumber())) {
	                BikePart temp = inputArray.get(count2 - 1);
	                inputArray.set(count2 - 1, inputArray.get(count2));
	                inputArray.set(count2, temp);
	            }
	        }
	    }
		return inputArray;
	}
	/**
	 * Sorts an array of BikeParts by alphabetical order.
	 * @param inputArray - The array of parts to be sorted
	 * @return A sorted array of bike parts
	 */
	private ArrayList<BikePart> alphaBubbleSort(ArrayList<BikePart> inputArray){
		for (int count = 1; count < inputArray.size(); count++) {
	        for (int count2 = 0; count2 < inputArray.size() - count; count2++) {
	            if (((inputArray.get(count2).getName()).compareToIgnoreCase((inputArray.get(count2 + 1).getName()))) > 0) {
	                BikePart temp = inputArray.get(count2);
	                inputArray.set(count2, inputArray.get(count2 + 1));
	                inputArray.set(count2 + 1, temp);
	            }
	        }
	    }
		return inputArray;		
	}
}

