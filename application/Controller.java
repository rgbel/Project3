package application;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;

import javafx.scene.control.RadioButton;

import javafx.scene.control.Tab;

import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.scene.control.TextInputDialog;

import javafx.scene.control.ToggleGroup;

import javafx.stage.FileChooser;

import roster.*;



import java.io.File;

import java.util.ArrayList;

import java.util.Date;

import java.util.GregorianCalendar;

import java.util.Optional;



import application.Main.*;





public class Controller {



    @FXML

    private Tab HomeTab;



    @FXML

    private TextField inputUser;



    @FXML

    private TextField inputPwd;



    @FXML

    private Button LoginButton;



    @FXML

    private Button LogoutButton;



    @FXML

    private Tab EnterTab;



    @FXML

    private TextField inputEnterListPrice;



    @FXML

    private TextField inputEnterSalesPrice;



    @FXML

    private TextField inputEnterOnSale;



    @FXML

    private TextField inputEnterQuant;



    @FXML

    private TextField inputEnterNum;



    @FXML

    private TextField inputEnterName;



    @FXML

    private Button EnterButton;



    @FXML

    private Tab ReadTab;



    @FXML

    private Button ReadButton;



    @FXML

    private TextField inputWH;

    

    @FXML

    private TextArea ReadTB;



    @FXML

    private Tab SearchTab;



    @FXML

    private RadioButton PartNameRadio;



    @FXML

    private ToggleGroup SearchRadioToggle;



    @FXML

    private RadioButton PartNumberRadio;



    @FXML

    private RadioButton QuantityRadio;



    @FXML

    private TextArea SortedTextArea;



    @FXML

    private Button SearchButton;



    @FXML

    private Button SortNameButton;



    @FXML

    private Button SortNumberButton;



    @FXML

    private RadioButton EqualQuantRadio;



    @FXML

    private ToggleGroup QuantToggleGroup;



    @FXML

    private RadioButton GreaterQuantRadio;



    @FXML

    private RadioButton LesserRadioQuant;



    @FXML

    private Tab InvoicesTab;



    @FXML

    private TextField inputStart;



    @FXML

    private TextField inputEnd;



    @FXML

    private Button GenerateCommission;



    @FXML

    private TextField SearchInvoicesBy;



    @FXML

    private RadioButton SalesAssoRadio;



    @FXML

    private ToggleGroup SearchInvoices;



    @FXML

    private RadioButton CustomerRadio;

    

    @FXML

    private TextArea SalesTB;



    @FXML

    private ToggleGroup Sea;



    @FXML

    private Tab SellTab;



    @FXML

    private Button SellButton;



    @FXML

    private Tab TransferTab;



    @FXML

    private Button TransferButton;

    

    @FXML

    private TextArea TransferTextArea;



    @FXML

    private Tab CreateTab;



    @FXML

    private TextField inputFirstName;



    @FXML

    private TextField inputLastName;



    @FXML

    private TextField inputPhone;



    @FXML

    private TextField inputEmail;



    @FXML

    private TextField inputUsername;



    @FXML

    private TextField inputPassword;



    @FXML

    private TextField inputPermission;



    @FXML

    private Button CreateAccountButton;



    @FXML

    private Tab DeleteTab;



    @FXML

    private Button DeleteButton;



    @FXML

    private TextField inputUserName;



    @FXML

    private TextField inputDelName;

    

    @FXML

    private Tab ResetTab;



    @FXML

    private Button ResetButton;



    /**

     * Allows a user to select a file from any location on their computer through an interactive prompt.

     * @param title - Modifies the top label of the file selection window.

     * @return The String file path if selected, or null otherwise.

     */

    String getFileName(String title) {

    	FileChooser fChooser = new FileChooser();

    	fChooser.setTitle(title);

    	File fileChoice = fChooser.showOpenDialog(null);

    	if (fileChoice != null)

    		return fileChoice.getAbsolutePath();

    	else

    		return null;

    }

    /**

     * Opens a window that allows the user to enter in a String input

     * @param dianame - The String to be assigned to the window

     * @param title  - The String to be set as the box's title

     * @param header - The String to set as the header's text

     * @param prompt - The String message to prompt the user what to enter

     * @return The user's String response if entered, null if left blank.

     */

    String textInputDialog(String dianame, String title, String header, String prompt) {

    	TextInputDialog dialogBox = new TextInputDialog(dianame);

    	dialogBox.setTitle(title);

    	dialogBox.setHeaderText(header);

    	dialogBox.setContentText(prompt);

    	Optional<String> result = dialogBox.showAndWait();

    	if (result.isPresent())

    		return result.get();

    	else

    		return null;

    }

    

    /**

     * A simplified version of textInputDialog that preemptively sets the dianame, title, and header values to reduce duplication.

     * @param prompt - The String message to prompt the user what to enter.

     * @return The user's String response if entered, null if left blank.

     */

    String simpleTextInputDialog(String prompt) {

    	return textInputDialog("Enter Part", "Enter Part Information", "Enter Part Dialog", prompt);

    }

    

    @FXML

    /**

     * When the ResetButton is pressed, the username provided is searched and, if it's found, a new password is asked for and set.

     * This method is intended for the SystemAdmin class.

     * @param event - ResetButton is pressed in Reset Password tab

     */

    void ChangePassword(ActionEvent event) {

    	//tested

    	// Loop through list of employees
    	
    	if(inputUserName.getText() != null) {
    		
        	for(Employee possible : Main.whf.programRoster.getRoster()) {

        		// Check each employee for if they match the password

        		if(inputUserName.getText().equals(possible.getLoginInfo().getUsername())) {

        			String newPassword = textInputDialog("Replace Password","Replace User Password","Enter New Password","Enter this user's new password:");

        			possible.getLoginInfo().resetPassword(newPassword);
        			
        			Alert alert = new Alert(AlertType.INFORMATION);
        			
        			alert.setTitle("Success");
        	
        			alert.setHeaderText("User password reset");
        	
        			alert.setContentText("Password for '" + inputUserName.getText() + "' has been successfully reset.");
        	
        			alert.showAndWait();
        			break;

        		}

        	}

		
    	} //if text fields full
    	else {
    	
    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Failure");

    		alert.setHeaderText("User password not reset");

    		alert.setContentText("Password for user, '" + inputUsername.getText() + "' has not been reset. Please check that current roster of employees.");

    		alert.showAndWait();
    		
    	}
    }
    
    @FXML

    /**

     * Information provided by the user is used to create a new Employee and add it to the existing roster.

     * This method is intended for the SystemAdmin class

     * @param event - CreateAccountButton is pressed in the Create Account tab.

     */

    void CreateAccount(ActionEvent event) {

    	boolean found = false;
    	if (!inputFirstName.getText().isEmpty() && !inputLastName.getText().isEmpty() && !inputPhone.getText().isEmpty() && !inputEmail.getText().isEmpty() && !inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty() && !inputPermission.getText().isEmpty() ) {
        	for(Employee possible : Main.whf.programRoster.getRoster()) {
        		if(inputUsername.getText().equals(possible.getLoginInfo().getUsername())) {
        			Alert alert = new Alert(AlertType.INFORMATION);

            		alert.setTitle("Failure");

            		alert.setHeaderText("User not entered");

            		alert.setContentText("New user has not been created. Username already exists. Please check that current roster of employees.");

            		alert.showAndWait();
            		return;
        		}
        	}
    		switch(inputPermission.getText()) {

        	case("0"):

        		Main.whf.getProgramRoster().getRoster().add(new SystemAdmin(inputFirstName.getText(),inputLastName.getText(),inputPhone.getText(),inputEmail.getText(),new LoginAccount(inputUsername.getText(),inputPassword.getText(),Integer.parseInt(inputPermission.getText()))));

        		break;

        	case("2"):

        		Main.whf.getProgramRoster().getRoster().add(new OfficeMan(inputFirstName.getText(),inputLastName.getText(),inputPhone.getText(),inputEmail.getText(),new LoginAccount(inputUsername.getText(),inputPassword.getText(),Integer.parseInt(inputPermission.getText()))));

        		break;

        	case("3"):

        		Main.whf.getProgramRoster().getRoster().add(new WHMan(inputFirstName.getText(),inputLastName.getText(),inputPhone.getText(),inputEmail.getText(),new LoginAccount(inputUsername.getText(),inputPassword.getText(),Integer.parseInt(inputPermission.getText()))));

        		break;

        	case("1"):

        		String newVan = textInputDialog("Employee Van","New Van","Create New Van","Enter the sale's associate's van name:");

        		Main.whf.getProgramRoster().getRoster().add(new SalesAsso(inputFirstName.getText(),inputLastName.getText(),inputPhone.getText(),inputEmail.getText(),new LoginAccount(inputUsername.getText(),inputPassword.getText(),Integer.parseInt(inputPermission.getText())),new Warehouse(newVan)));

        		break;

        	}


    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Success");

    		alert.setHeaderText("User added");

    		alert.setContentText(inputFirstName.getText() + " " + inputLastName.getText() + " has been successfully added to the employee roster.");

    		alert.showAndWait();

    		
    	} //if text fields full
    	
    	
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Failure");

    		alert.setHeaderText("User not entered");

    		alert.setContentText("New user has not been created. Please check that all fields are completed.");

    		alert.showAndWait();
    	}
    	
    	//for everyone in roster


    }



    @FXML

    /** 

     * Iterate through the current roster of employees and remove the one that matches the indicated username, if found

     * @param event - DeleteButton is pressed in Delete tab.

     */

    void DeleteAccount(ActionEvent event) {
    	
    	
    	if (inputDelName.getText() != null && !inputDelName.getText().equals("admin")) { //change 'admin' to currently logged in admin username!!
        	int index = 0;

        	// Loop through list of employees

        	boolean found = false;
    		for(Employee possible : Main.whf.programRoster.getRoster()) {

    			// Check each employee for if they match the password

    			if(inputDelName.getText().equals(possible.getLoginInfo().getUsername())) { //fix because still gives success window when user is not present in roster

    				Main.whf.programRoster.getRoster().remove(index);
    				found = true;
    				break;

    			}

    			index++;

    		}

        	if(found) {
    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Success");

    		alert.setHeaderText("User removed");

    		alert.setContentText("'" + inputDelName.getText() + "' has been successfully removed from the employee roster.");

    		alert.showAndWait();
        	} else { //if not an employee
        		Alert alert = new Alert(AlertType.INFORMATION);

        		alert.setTitle("Failure");

        		alert.setHeaderText("User not removed");

        		alert.setContentText("User, '" + inputDelName.getText() + "' has not been removed. Please check that current roster of employees.");

        		alert.showAndWait();
        	}
    		
    	}
    	else { //if admin
    		
    		
    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Failure");

    		alert.setHeaderText("User not removed");

    		alert.setContentText("User, '" + inputDelName.getText() + "' has not been removed. Please check that current roster of employees.");

    		alert.showAndWait();
    		
    	}

    }



    @FXML

    /**

     * The radio buttons for Quantity options are disabled.

     * @param event - The Quantity radio button is de-selected.

     */

    void DisableQuantOption(ActionEvent event) {

    	EqualQuantRadio.setDisable(true);

    	GreaterQuantRadio.setDisable(true);

    	LesserRadioQuant.setDisable(true);

    }



    @FXML

    /**

     * The radio buttons for Quantity options are enabled.

     * @param event - The Quantity radio button is selected.

     */

    void EnableQuantOptions(ActionEvent event) {

    	EqualQuantRadio.setDisable(false);

    	GreaterQuantRadio.setDisable(false);

    	LesserRadioQuant.setDisable(false);

    }



    @FXML

    /**

     * A part is added to the main warehouse using information provided by the user, if all text boxes contain some data.

     * @param event - EnterButton is selected

     */

    void EnterParts(ActionEvent event) {

    	if(inputEnterName.getText() != null && inputEnterName.getText() != null && inputEnterListPrice != null && inputEnterSalesPrice.getText() != null && inputEnterOnSale.getText() != null && inputEnterQuant.getText() != null) {

    		Main.whf.getProgramFleet().getFleet().get(0).getInv().add(new BikePart(inputEnterName.getText(),Integer.parseInt(inputEnterNum.getText()),Double.parseDouble(inputEnterListPrice.getText()),Double.parseDouble(inputEnterSalesPrice.getText()),Boolean.parseBoolean(inputEnterOnSale.getText()),Integer.parseInt(inputEnterQuant.getText())));

    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Success");

    		alert.setHeaderText("Part added");

    		alert.setContentText("Part has been successfully added to the main warehouse.");

    		alert.showAndWait();

    	}

    }

    @FXML

    void EqualToQuant(ActionEvent event) {



    }



    @FXML

    void GenerateCommission(ActionEvent event) {

    	ArrayList<SalesInvoice> invoiceList;

    	String[] startData = inputStart.getText().split("-");

    	Date startDate = new GregorianCalendar(Integer.parseInt(startData[0]),Integer.parseInt(startData[1]), Integer.parseInt(startData[2])).getTime();

    	

    	String[] endData = inputEnd.getText().split("-");

		Date endDate =  new GregorianCalendar(Integer.parseInt(endData[0]),Integer.parseInt(endData[1]),Integer.parseInt(endData[2])).getTime();



    	if(SalesAssoRadio.isSelected()) {

    		SalesTB.setText("Commission for total sales invoices between specified dates: " + SalesAsso.getComBetween(SearchInvoicesBy.getText(),startDate,endDate) + "0");

    		SalesTB.appendText("\n==================================================================================");

    		invoiceList = Main.whf.getProgramRoster().getInvoiceBySalesAssoBetweenDates(SearchInvoicesBy.getText(),startDate,endDate);

    	}else  
    		SalesTB.setText("");
    		invoiceList = Main.whf.getProgramRoster().getInvoiceByCustomerBetweenDates(SearchInvoicesBy.getText(), startDate, endDate);

    	for(SalesInvoice loopInvoice : invoiceList) {

    		SalesTB.appendText(loopInvoice.toString());
    		SalesTB.appendText("\n");

    	}

    	if(invoiceList.isEmpty())

        	SalesTB.setText("No invoices match current date.\n");

    		

    }



    @FXML

    void GreaterThanQuant(ActionEvent event) {



    }



    @FXML

    void LessThanQuant(ActionEvent event) {



    }



    @FXML

    /**

     * Check the user's login credentials and search for which employee they correspond to. 

     * If they exist, allow them access to all the tabs that an employee of their type should be able to see.

     * Then, disable the login button and enable the logout button.

     * @param event - LoginButton is selected.

     */

    void LoginCheckUser(ActionEvent event) {

    	if(LoginButton.isDisabled()) {

    		LogoutToHome(event);

    	}

    	// Loop through list of employees

    	for(Employee possible : Main.whf.programRoster.getRoster()) {

    		// Check each employee for if they match the password

    		if(inputUser.getText().equals(possible.getLoginInfo().getUsername()) && inputPwd.getText().equals(possible.getLoginInfo().getPassword())) {

    			Main.activeUser = possible;

    			// Enable tabs that correspond with the active user

    			switch(Main.activeUser.getLoginInfo().getPermission()) {

    			case(0):

    				CreateTab.setDisable(false);

    				DeleteTab.setDisable(false);

    				ResetTab.setDisable(false);

    				break;

    			case(1):

    				SellTab.setDisable(false);

    				TransferTab.setDisable(false);

    				break;

    			case(2):

    				InvoicesTab.setDisable(false);

    				SearchTab.setDisable(false);

    				break;

    			case(3):

    				EnterTab.setDisable(false);

    				ReadTab.setDisable(false);

    				SearchTab.setDisable(false);

    				break;

    			}

    			// Enable logout, disable login

			LoginButton.setDisable(true);

			LogoutButton.setDisable(false);

			break;

    		}

    	}

    }



    @FXML

    /**

     * All tabs except Home are disabled, the active user is removed, and the login button is re-activated.

     * @param event - LogoutButton is selected

     */

    void LogoutToHome(ActionEvent event) {

    	// Disable all tabs, enable login

    	CreateTab.setDisable(true);

		DeleteTab.setDisable(true);

		ResetTab.setDisable(true);

		EnterTab.setDisable(true);

		ReadTab.setDisable(true);

		SearchTab.setDisable(true);

		InvoicesTab.setDisable(true);

		SellTab.setDisable(true);

		TransferTab.setDisable(true);

		LoginButton.setDisable(false);

		LogoutButton.setDisable(true);

		Main.activeUser = null;

    }



    @FXML

    void ReadFileAction(ActionEvent event) {

    	// This should use the read method from project 2

    	String warehouseChoice = inputWH.getText();

		if(warehouseChoice != null) {

			int indexW = Main.whf.programFleet.isWarehouse(warehouseChoice);

			if (indexW > -1) {

				String filename = getFileName("Open File");

				if(filename != null) {

					ReadTB.appendText(filename + "\n");

					Main.whf.programFleet.getFleet().get(indexW).updateInventory(filename);

					ReadTB.appendText("Update complete.\n");

				} else

					ReadTB.appendText("No file selected.\n");

			}

			else

				ReadTB.appendText("Warehouse choice not found in fleet.\n" + indexW);

		}

		else

			ReadTB.appendText("No warehouse name entered.\n");

	}

    

    /**

     * Using an entered input, the entire warehouse/van fleet is searched for the indicated part.

     * Depending on the radio button selected, different search options are used.

     * If quantity is selected, the radio buttons indicating =,<,> to choose what range to use.

     * If found, pertinent information is printed about the part as well as which warehouse it was first encountered.

     * Otherwise, an error message is returned.

     * @param event - The SearchButton is selected.

     */

    @FXML

    void SearchParts(ActionEvent event) {

    	SortedTextArea.setText("");

    	// Method branches into 5 possibilities:

    	//	1: Part name search.

    	//	2: Part number search.

    	// 	3: Part quantity search with...

    	//		A: Equals sign chosen.

    	//		B: Greater sign chosen.

    	//		C: Lesser sign chosen.

    	

    	// Part name search

    	boolean found = false;

    	if(PartNameRadio.isSelected()) {

    		String searchValue = textInputDialog("Part Name","Search Part Name","Enter Part Name","Enter the part name to search:");

    		for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {

    			int index = loopWH.getPart(searchValue);

    			if(index > -1) {

    				BikePart loopPart = loopWH.getInv().get(index);

    					found = true;

    					SortedTextArea.setText("Part found.\n");

    					SortedTextArea.appendText("Part number: " + loopPart.getNumber() + "\n");

    					SortedTextArea.appendText("Part price: " + loopPart.getActivePrice() + "\n");

    					SortedTextArea.appendText("Part quantity: " + loopPart.getStock() + "\n");

    					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");

    					break;

    				}

    			

    		} if(!found)

    			SortedTextArea.setText("Part not found in fleet.\n");

    	}

    	// Part number search

    	else if(PartNumberRadio.isSelected()) {

        		int searchValue = Integer.parseInt(textInputDialog("Part Number","Search Part Number","Enter Part Number","Enter the part number to search:"));

        		for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {

        			for(BikePart loopPart : loopWH.getInv()) {

        				if(searchValue == loopPart.getNumber()) {

        					found = true;

        					SortedTextArea.setText("Part found.\n");

        					SortedTextArea.appendText("Part name: " + loopPart.getName() + "\n");

        					SortedTextArea.appendText("Part price: " + loopPart.getActivePrice() + "\n");

        					SortedTextArea.appendText("Part quantity: " + loopPart.getStock() + "\n");

        					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");

        					break;

        				}

        			}

        		}

        		 if(!found)

         			SortedTextArea.setText("Part not found in fleet.\n");

    	}

    	// Quantity selected

    	else if(QuantityRadio.isSelected()) {

    		int searchValue = Integer.parseInt(textInputDialog("Part Quantity","Search Part Quantity","Enter Part Quantity","Enter the part quantity to search:"));

    		if(searchValue > -1) {

    			// Equals selected

    			if(EqualQuantRadio.isSelected()) {

    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {

            			for(BikePart loopPart : loopWH.getInv()) {

            				if(searchValue == loopPart.getStock()) {

            					found = true;

            					SortedTextArea.appendText(loopPart.getAll() + "\n");

            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");

            				}

            			}

    				}

    				 if(!found)

    	         			SortedTextArea.setText("Matching part not found in fleet.\n");

    			}

    			// Greater selected

    			else if(GreaterQuantRadio.isSelected()) {

    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {

            			for(BikePart loopPart : loopWH.getInv()) {

            				if(searchValue > loopPart.getStock()) {

            					found = true;

            					SortedTextArea.appendText(loopPart.getAll() + "\n");

            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");

            				}

            			}

    				}

    				if(!found)

	         			SortedTextArea.setText("Matching part not found in fleet.\n");

    			}

    			// Lesser selected

    			else if(LesserRadioQuant.isSelected()) {

    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {

            			for(BikePart loopPart : loopWH.getInv()) {

            				if(searchValue < loopPart.getStock()) {

            					found = true;

            					SortedTextArea.appendText(loopPart.getAll() + "\n");

            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");

            				}

            			}

    				}

    				if(!found)

	         			SortedTextArea.setText("Matching part not found in fleet.\n");

    			}

    		}

    	}

    }



    @FXML

    /**

     * A file is selected, and the contents are read in order to create a SalesInvoice, assuming that the sales van is correct and the van has teh proper contents.

     * @param event - SellButton is selected

     */

    void SellPart(ActionEvent event) {

    	String fileName = getFileName("Select part sale file.");

    	if(((SalesAsso) Main.activeUser).sellFromFile(fileName)) {

    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Success");

    		alert.setHeaderText("Parts solds");

    		alert.setContentText("Parts have been sold from indicated van.");

    		alert.showAndWait();

    	} else {

    		Alert alert = new Alert(AlertType.INFORMATION);

    		alert.setTitle("Failure");

    		alert.setHeaderText("Parts not sold");

    		alert.setContentText("Parts have not been sold. Please check that current sales associate has ownership of van being sold from.");

    		alert.showAndWait();

    	}

    }



    @FXML

    /**

     * The user is prompted with a choice of what warehouse to sort or all.

     * The indicated warehouse(s) is sorted alphabetically by name and printed to the SortedTextArea.

     * @param event - SortNameButton is selected

     */

    void SortNameParts(ActionEvent event) {

    	String searchValue = textInputDialog("Make Choice","Choose Search Option","Enter Search Option","Choose to either search (all) or enter a warehouse/van name:");

    	ArrayList<BikePart> alphaArray = Main.whf.getProgramFleet().alphaSort(searchValue);
    	
    	if(alphaArray.isEmpty()) {
    		SortedTextArea.setText("Incorrect or empty van: please check that parts or van has been added.");
    		return;
    	}


    	SortedTextArea.setText(" === Alphabetical Part Sort === \n");

    	for(BikePart loop : alphaArray) {

    		SortedTextArea.appendText(loop.getAll() + "\n");

    	}

    }



    @FXML

    /**

     * The user is prompted with a choice of what warehouse to sort or all.

     * The indicated warehouse(s) is sorted numerically by ID number and printed to the SortedTextArea.

     * @param event - SortNumberButton is selected

     */

    void SortNumberParts(ActionEvent event) {

    	String searchValue = textInputDialog("Make Choice","Choose Search Option","Enter Search Option","Choose to either search (all) or enter a warehouse/van name:");

    	ArrayList<BikePart> numArray = Main.whf.getProgramFleet().numSort(searchValue);
    	
    	if(numArray.isEmpty()) {
    		SortedTextArea.setText("Incorrect or empty van: please check that parts or van has been added.");
    		return;
    	}

    	SortedTextArea.setText(" === Numerical Part Sort === \n");

    	for(BikePart loop : numArray) {

    		SortedTextArea.appendText(loop.getAll() + "\n");

    	}

    }



    @FXML

    /** The user is prompted to select a file and, if valid, parts will be transferred between two vans.

     *  This transfer will also only happen if the sales employee owns one of the two vans.

     * @param event - TransferButton is pressed.

     */

    void TransferFile(ActionEvent event) {

    	String fileName = getFileName("Select transfer file.");

		if(fileName != null) {

			if(Main.whf.programFleet.transferParts(fileName, ((SalesAsso)Main.activeUser))) {

				TransferTextArea.setText("Transfer complete");

			}

			else {

				TransferTextArea.setText("Transfer failed: sales associate does not own either van.");

			}

		} else {

			TransferTextArea.setText("Transfer failed: file not selected.");

		}

    }



}
