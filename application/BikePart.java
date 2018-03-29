implements Serializablepackage application;

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
import javax.swing.JOptionPane;


/** Allows the user to create and modify bike parts with the values of their name, number, price, sale price, and if they are on sale.
 * This class can also create arrays of part and search through those array for parts below a certain price.
 * @author - Matthew Pessolano
 * @version - 1.2 on 2/9/2018
 */

public class BikePart extends Part implements Serializable{
	
	// Creating object variables to store for each BikePart read in
	
	double price;
	double salesPrice;
	boolean onSale;
	int stock;
	
	/** Constructs the BikePart object 
	 * 
	 * @param partName			Sets the part name of the newly made bike part
	 * @param partNumber		Sets the numerical value assigned to the part (helpful if a part is given the wrong name)
	 * @param partPrice			Sets the default price of the part, used if onSale is "false"
	 * @param salesPartPrice	Sets the sale price of the part, used if onSale is "true"
	 * @param partOnSale		Sets the 'sale' status of the part as either true or false
	 * @param stock				Sets the stock of how many of these parts exist
	 * 
	 * */
	public BikePart(String partName, int partNumber, double partPrice, double salesPartPrice, boolean partOnSale, int partStock){
		
		super(partName, partNumber);
		price = partPrice;
		salesPrice = salesPartPrice;
		onSale = partOnSale;
		stock = partStock;
	}
	/** Due to a later issue with the array creation, the method was overloaded to allow for an empty BikePart to be made if needed*/
	public BikePart(){
		
		// Values created but not set, must be added with setters or the setAll() method.
		
		super();
		double price;
		double salesPrice;
		boolean onSale;
		int stock;
	}
	
	// All setters for BikePart objects
	
	
	
	/** Sets the default price value of the BikePart to the newPrice value 
	 * @param newPrice 		Chosen value to replace the existing price.
	 * */
	public void setPrice(double newPrice){
		price = newPrice;
	}
	/** Sets the sales price value of the BikePart to the newSalesPrice value 
	 * @param newSalesPrice 		Chosen value to replace the existing salesPrice.
	 * */
	public void setSalesPrice(double newSalesPrice){
		salesPrice = newSalesPrice;
	}
	/** Sets if a sale is currently going on for the BikePart to the true or false value of newOnSale
	 * @param newOnSale 		Chosen value to replace the existing onSale.
	 * */
	public void setOnSale(boolean newOnSale){
		onSale = newOnSale;
	}
	/** Sets how many of these parts exist.
	 * @param newStock 		Chosen value to replace the existing stock.
	 * */
	public void setStock(int newStock){
		stock = newStock;
	}
	/** Acts similar to the constructor, allowing the user to replace every single value if needed to an already existing part.
	 * 
	 * @param newName			Sets the part name of the existing bike part
	 * @param newNumber			Sets the numerical value assigned to the part
	 * @param newPrice			Sets the default price of the part, used if onSale is "false"
	 * @param newSalesPrice		Sets the sale price of the part, used if onSale is "true"
	 * @param newOnSale			Sets the 'sale' status of the part as either true or false
	 * @param newStock 			Sets how many of these parts exist.
	 * 
	 * */
	public void setAll(String newName, int newNumber, double newPrice, double newSalesPrice, boolean newOnSale, int newStock){
		name = newName;
		number = newNumber;
		price = newPrice;
		salesPrice = newSalesPrice;
		onSale = newOnSale;
		stock = newStock;
	}
	
	// All getters for BikePart object
	
	
	/** Outputs the price value of a bike part
	 *  @return price, pulled from the object.
	 * */
	public double getPrice(){
		return price;
	}
	
	/** Outputs the salesPrice value of a bike part
	 *  @return salesPrice, pulled from the object.
	 * */
	public double getSalesPrice(){
		return salesPrice;
	}
	
	/** Outputs the onSale value of a bike part
	 *  @return onSale, pulled from the object.
	 * */
	public boolean getOnSale(){ return onSale; }
	public int getStock(){ return stock; }
	/** Outputs all the values of a bike part, formatted correctly for our two methods.
	 *  @return A String that pulls all six values and separates them by commas.
	 * */
	public String getAll(){
		return(name + "," + number + "," + price + "," + salesPrice + "," + onSale + "," + stock);
	}
	/**
	 * Outputs the active price based on onSale value
	 * @return getPrice() or getSalesPrice()
	 */
	public double getActivePrice() {
		if (getOnSale())
			return getSalesPrice();
		return getPrice();
	}
	/** Takes an array of bike parts and searches for parts under a specified value, printing each to the console.
	 * 	This takes into account sale prices, so the loop checks for this each time before proceeding.
	 * 
	 *  @param partArray		The user-supplied array that will be searched for the proper price.
	 *  @param priceThreshold	The threshold for what a price needs to be lower than to output
	 *  
	 * */
	/*public static ArrayList<BikePart> getCheaperThan(ArrayList<BikePart> partArray, double priceThreshold){
		ArrayList<BikePart> tempArray = new ArrayList<BikePart>();
		// For loop will go through all elements of the array, automatically ends if it is empty
		for (BikePart partTest : partArray) {
			// Check if item is on sale to determine which value to use
			if (partTest.getOnSale() == true){
				// Check against user's price threshold. 
				// No need to check for improper values like 0 or lower, as this will just output nothing and not crash the code.
				if (partTest.getSalesPrice() < priceThreshold){
					tempArray.add(partTest);
				}
			} else {
				// Duplicate of if loop, except checks against getPrice()
				if (partTest.getPrice() < priceThreshold){
					tempArray.add(partTest);
				}
			}
		}
		return tempArray;
	}*/
	/** Creates an array of bike parts through user input, forcing the user to use the correct format and keeping count of how many are added.
	 * 
	 *  @return partArray, a BikePart array full of objects organized in order of their addition.
	 * */
	public static ArrayList<BikePart> fileToArrayList(String fileName){
		// Scanner created in order to read file.
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
			

		return fileList;
	}
	/**
	 * arraylistToFile iterates through an ArrayList and outputs to a supplied text file
	 * @param partArray - Received array the user wants to store in a file
	 * @param fileName - The name of the file the user wishes to store the values of the ArrayList in
	 */
/*	public static void arraylistToFile(ArrayList<BikePart> partArray, String fileName){
		FileWriter partWriter;
		try {
			partWriter = new FileWriter(fileName);
			for(BikePart tPart: partArray){
				partWriter.write(tPart.getAll() + "\n");
			}
			partWriter.close();
		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
			
		}
		
	}*/
	/*
	 * fileToLessFile combines multiple methods into a user-friendly overlay that leads them through the process of transferring data from one file to another, only keeping parts below a certain value.
	 */
	/*public static void fileToLessFile(){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter Input Filename:");
		String userResponse = userInput.nextLine();
		ArrayList<BikePart> tempArray = fileToArrayList(userResponse);
		
		System.out.println("Enter Max Cost:");
		int userMax = userInput.nextInt();
		userInput.nextLine();
		tempArray = getCheaperThan(tempArray, userMax);
		
		System.out.println("Enter Output Filename:");
		userResponse = userInput.nextLine();
		arraylistToFile(tempArray, userResponse);
		
		System.out.println(userResponse + " successfully processed.");
		}*/
	/** startup() is the default initialization method that guides the user through a highly-interactive way of creating and maintaining an inventory database
	 * 
	 */
/*	public static void startup(){
		//Scanner input = new Scanner(System.in);
		//System.out.println("Enter warehouse database file: ");
		//String userResponse = input.nextLine();
		
		// This database file name can be changed as needed, independently of the output file.
		String database = "warehouseDB.txt";
		ArrayList<BikePart> inventory = fileToArrayList(database);
		menu(inventory);
		
	}*/
	/** menu() is the main means of the user interacting with the program. A variety of options are offered and handled while keeping a persisting inventory through each call and return.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	/*public static void menu(ArrayList<BikePart> inventory){
		boolean quit = false;
		while(quit == false){
			// Shows possible options for user to input, loops infinitely until quit()
			System.out.println("=====================================================");
			System.out.println("Please select your option from the following menu: ");
			System.out.println("Read: Read an inventory delivery file");
			System.out.println("Enter: Enter a part	");
			System.out.println("Sell: Sell a part ");
			System.out.println("Display: Display a part");
			System.out.println("SortName: Sort parts by part name");
			System.out.println("SortNumber: Sort parts by part number");
			System.out.println("Quit: ");
			System.out.println("Enter your choice:");
			
			// To ask for input in a more user-friendly format, InputDialog boxes appear to prompt the user similar to how a Scanner would work.
			String res;
			res = JOptionPane.showInputDialog("Input choice:");
			//Scanner input = new Scanner(System.in);
			//res = input.nextLine();
			
			// Handles user response, ignoring capital letters
			if(res.equalsIgnoreCase("read"))
				readInventory(inventory);
			else if(res.equalsIgnoreCase("enter"))
				enterPart(inventory);
			else if(res.equalsIgnoreCase("sell"))
				sellPart(inventory);
			else if(res.equalsIgnoreCase("display"))
				displayPart(inventory);
			else if(res.equalsIgnoreCase("sortname"))
				sortInventoryName(inventory);
			else if(res.equalsIgnoreCase("sortnumber"))
				sortInventoryNumber(inventory);
			else if(res.equalsIgnoreCase("quit"))
				// A break; could be used here but a quit variable is preferred for later expansion
				quit = true;
			else
				// Code will automatically loop if incorrect, so an error message is displayed before the menu is re-printed.
				System.out.println("Invalid response. Try again, with no spaces or added characters.");
		}
		// Once the loop is broken by a 'quit' command, this line is read.
		quit(inventory);
	}*/
	/** displayPart() asks the user for a part name and iterates through the inventory looking for said part, returning information about it if it is found.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static BikePart displayPart(ArrayList<BikePart> inventory, String partName){
		//Scanner input = new Scanner(System.in);
		//System.out.println("Enter part name: ");
		//String partName = JOptionPane.showInputDialog("Enter part name:");
		//String partName = input.nextLine();
		
		//'error' keeps track of the status of the found part. It defaults to not being found and switches upon a successful 'if' statement.
		//boolean error = true;
		BikePart rPart = new BikePart();
		for (BikePart testPart : inventory){
			if (testPart.getName().equalsIgnoreCase(partName)){
				//System.out.println("Part found.");
				//System.out.println("Part name: " + testPart.getName());
				//if (testPart.getOnSale())
					//System.out.println("Part price: $" + testPart.getSalesPrice());
				//else
				//System.out.println("Part price: $" + testPart.getPrice());
				//error = false;
				rPart = testPart;
				break;
				}

		}
		//if(error)
			//System.out.println("Part not found.");
	return rPart;
	}
	/** enterPart() interactively asks the user information about the part and tries to use it to update an existing part. If the part does not exist, it will create it.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static void enterPart(ArrayList<BikePart> inventory){
		// The new part is created in an interactive window prompt.
		// The alternative Scanners have been left as comments in case reverting back is ever needed.
		BikePart tempPart = new BikePart();
		
		
		//Scanner input = new Scanner(System.in);
		//System.out.println("Enter the part name: ");
		tempPart.setName(JOptionPane.showInputDialog("Enter the part name:"));
		
		//System.out.println("Enter the part number: ");
		//String temp = input.nextLine();
		tempPart.setNumber(Integer.parseInt(JOptionPane.showInputDialog("Enter the part ID:")));
		
		//System.out.println("Enter the part list price: ");
		//temp = input.nextLine();
		tempPart.setPrice(Double.parseDouble(JOptionPane.showInputDialog("Enter the part list price:")));
		
		//System.out.println("Enter the part sale price: ");
		//temp = input.nextLine();
		tempPart.setSalesPrice(Double.parseDouble(JOptionPane.showInputDialog("Enter the part sale price:")));
		
		//System.out.println("Enter if part is on sale (true, false): ");
		//temp = input.nextLine();
		tempPart.setOnSale(Boolean.parseBoolean(JOptionPane.showInputDialog("Enter if part is on sale (true, false): ")));
		
		//System.out.println("Enter the part quantity: ");
		//temp = input.nextLine();
		tempPart.setStock(Integer.parseInt(JOptionPane.showInputDialog("Enter the part quantity: ")));
		
		// Found refers to if an already-existing part with the same name already exists
		// Defaults to false, changes to true when a successful search is made
		boolean found = false;
		for (BikePart testPart : inventory){
			if ((tempPart.getName()).equals(testPart.getName())){
				// Updates the stock, price, sales price, and on sale value based on what details the user input.
				testPart.setStock(testPart.getStock() + tempPart.getStock());
				testPart.setPrice(tempPart.getPrice());
				testPart.setSalesPrice(tempPart.getSalesPrice());
				testPart.setOnSale(tempPart.getOnSale());
				found = true;
				//Takes the user out of the for loop instead of wasting time by iterating through searching for an already found value
				break;
			}
		}
		// If the full loop is made without success, the part is added normally
		if(!found){
			inventory.add(tempPart);
		}
	}
	/** sellPart() iterates through the inventory using a part number, and returns a name, the proper price, and the time sold if found (decrements count by one and removes if now <= 1).
	 * If part is not found, returns error message.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static void sellPart(ArrayList<BikePart> inventory){
		//Scanner input = new Scanner(System.in);
		//System.out.println("Enter the part number: ");
		String temp = JOptionPane.showInputDialog("Enter the part number: ");
		int numberSearch = Integer.parseInt(temp);
		
		// Error indicates if the method should display a message at the end of loop
		// Error defaults to true and will be set to false upon finding the searched part number.
		boolean error = true;
		for (BikePart testPart : inventory){
			if (testPart.getNumber() == numberSearch){
				System.out.println("Part sold.");
				System.out.println("Part name: " + testPart.getName());
				// This if-else checks which value should be used for the price output (checks OnSale value)
				if (testPart.getOnSale())
					System.out.println("Part sold at sale price for: $" + testPart.getSalesPrice());
				else
					System.out.println("Part sold at list price for: $" + testPart.getPrice());
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = new Date();
				System.out.println("Sold on " + dateFormat.format(date));
				
				// Stock must be decremented by one and checked if the part is empty.
				// If empty, a message displays and the part is removed from stock.
				testPart.setStock(testPart.getStock() - 1);
				if (testPart.getStock() < 1){
					System.out.println("Part now out of stock. Removed from inventory.");
					inventory.remove(testPart);
				}
				error = false;
				break;
			}

		}
		if(error)
			System.out.println("Part not found.");
	}
	/** readInventory() iterates through a selected file and adds all BikeParts to the existing inventory (either adding to existing parts or creating new ones).
	 * @param - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static void readInventory(ArrayList<BikePart> inventory){
		//Scanner input = new Scanner(System.in);
		//System.out.println("Enter the file name: ");
		String temp = JOptionPane.showInputDialog("Enter the file name: ");
		File partFile = new File(temp);
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
			for (BikePart testPart : inventory){
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
				inventory.add(loopPart);
		}
	}
	/** sortInventoryName() prints out the existing inventory listing in alphabetical order.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static void sortInventoryName(ArrayList<BikePart> inventory) {
		ArrayList<BikePart> tempInv = new ArrayList<BikePart>();
		// Instead of sorting the entire array, this method focuses on simply printing the parts in order, leaving the original input order
		for(BikePart loop : inventory){
			// This section is mandatory for populating a new array with the same values as the existing array.
			// We do this in order to be able to delete the values that have already been printed before.
			// If a .clone() or any other reference to the existing values of the array are made, they point to the same place and delete values from both arrays.
			// As a workaround, completely new objects are made and deleted as needed.
			String tname = loop.getName();
			int tnumber = loop.getNumber();
			double tprice = loop.getPrice();
			double tsalesPrice = loop.getSalesPrice();
			boolean tonSale = loop.getOnSale();
			int tstock = loop.getStock();
			BikePart tempLoop = new BikePart();
			tempLoop.setAll(tname, tnumber, tprice, tsalesPrice, tonSale, tstock);
			tempInv.add(tempLoop);
		}
		BikePart topPart = new BikePart("xREPLACEx", 1, 1, 1, false, 1);
		// curIndex keeps track of where we are in the arraylist
		// This is necessary so we can keep track of the index of the highest alphabetical bike part and delete it at the end of the loop.
		int index = 0;
		int curIndex;
		while(tempInv.isEmpty() != true) {
			curIndex = 0;
			topPart.setAll("xREPLACEx", 1, 1, 1, false, 1);
			// Iterative search through the new arraylist.
			for(BikePart tempPart : tempInv) {
				// The new tempPart BikePart was made so that the top part will always be replaced by the 0 index part in the array
				if(topPart.getName().equals("xREPLACEx") || topPart.getName().toLowerCase().compareTo(tempPart.getName().toLowerCase()) > 0) {
					topPart = tempPart;
					index = curIndex;
			}
			curIndex++;
		}
		System.out.println(topPart.getAll());
		tempInv.remove(index);
	}
		
	}
	/** sortInventoryNumber prints out the existing array in numerical order.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
	public static void sortInventoryNumber(ArrayList<BikePart> inventory) {
		// Identical function to sortInventoryName() except what value it sorts by numerical
		ArrayList<BikePart> tempInv = new ArrayList<BikePart>();
		for(BikePart loop : inventory){
			// This section is mandatory for populating a new array with the same values as the existing array.
			// We do this in order to be able to delete the values that have already been printed before.
			// If a .clone() or any other reference to the existing values of the array are made, they point to the same place and delete values from both arrays.
			// As a workaround, completely new objects are made and deleted as needed.
			String tname = loop.getName();
			int tnumber = loop.getNumber();
			double tprice = loop.getPrice();
			double tsalesPrice = loop.getSalesPrice();
			boolean tonSale = loop.getOnSale();
			int tstock = loop.getStock();
			BikePart tempLoop = new BikePart();
			tempLoop.setAll(tname, tnumber, tprice, tsalesPrice, tonSale, tstock);
			tempInv.add(tempLoop);
		}
		BikePart topPart = new BikePart("xREPLACEx", Integer.MAX_VALUE, 1, 1, false, 1);
		int index = 0;
		int curIndex;
		while(tempInv.isEmpty() != true) {
			// The new tempPart BikePart was made so that the top part will always be replaced by the 0 index part in the array
			curIndex = 0;
			// A change was made to the bike part so that the default number would never be lowest
			topPart.setAll("xREPLACEx", Integer.MAX_VALUE, 1, 1, false, 1);
			for(BikePart tempPart : tempInv) {
				if(topPart.getNumber() > tempPart.getNumber()) {
				topPart = tempPart;
				index = curIndex;
				}
				curIndex++;
			}
			System.out.println(topPart.getAll());
			tempInv.remove(index);
		}
			
		
	}
	/** quit() is the final procedure the program runs when a user decided to quit out. It saves the running inventory into the 'database' file for retrieval next startup.
	 * @param inventory - The ArrayList of BikeParts that will be maintained through the entirety of the code.
	 */
/*	public static void quit(ArrayList<BikePart> inventory) {
		// This value can be edited if a new database file is needed
		// This value is not linked to the startup() database, allowing for separate input and output files.
		String database = "warehouseDB.txt";
		FileWriter partWriter;
		try {
			partWriter = new FileWriter(database);
			for(BikePart tPart: inventory){
				partWriter.write(tPart.getAll() + "\n");
			}
			partWriter.close();
	// In its current form, this catch will simply send the user back to the menu screen.
	// If later needed, this quit() method could be edited to allow user input of the file name, preventing an unendable program.
	} catch (IOException e) {
		System.out.println("File not found.");
		menu(inventory);
	}
		
	}
	
	public static void main(String[] args) {
		startup();
	}*/
	public double getTotalCost() {
		return (this.getActivePrice() * this.stock);
	}
}
