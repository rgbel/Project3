package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    	// Loop through list of employees
    	for(Employee possible : Main.whf.programRoster.getRoster()) {
    		// Check each employee for if they match the password
    		if(inputUserName.getText().equals(possible.getLoginInfo().getUsername())) {
    			String newPassword = textInputDialog("Replace Password","Replace User Password","Enter New Password","Enter this user's new password:");
    			possible.getLoginInfo().resetPassword(newPassword);
    			break;
    		}
    	}
    }

    @FXML
    /**
     * Information provided by the user is used to create a new Employee and add it to the existing roster.
     * This method is intended for the SystemAdmin class
     * @param event - CreateAccountButton is pressed in the Create Account tab.
     */
    void CreateAccount(ActionEvent event) {
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
    }

    @FXML
    void DeleteAccount(ActionEvent event) {
    	int index = 0;
    	// Loop through list of employees
    	for(Employee possible : Main.whf.programRoster.getRoster()) {
    		// Check each employee for if they match the password
    		if(inputDelName.getText().equals(possible.getLoginInfo().getUsername())) {
    			Main.whf.programRoster.getRoster().remove(index);
    			break;
    		}
    	index++;
    	}
    }

    @FXML
    void DisableQuantOption(ActionEvent event) {
    	EqualQuantRadio.setDisable(true);
    	GreaterQuantRadio.setDisable(true);
    	LesserRadioQuant.setDisable(true);
    }

    @FXML
    void EnableQuantOptions(ActionEvent event) {
    	EqualQuantRadio.setDisable(false);
    	GreaterQuantRadio.setDisable(false);
    	LesserRadioQuant.setDisable(false);
    }

    @FXML
    void EnterParts(ActionEvent event) {
    	Main.whf.getProgramFleet().getFleet().get(0).getInv().add(new BikePart(inputEnterName.getText(),Integer.parseInt(inputEnterNum.getText()),Double.parseDouble(inputEnterListPrice.getText()),Double.parseDouble(inputEnterSalesPrice.getText()),Boolean.parseBoolean(inputEnterOnSale.getText()),Integer.parseInt(inputEnterQuant.getText())));
    }

    @FXML
    void EqualToQuant(ActionEvent event) {

    }

    @FXML
    void GenerateCommission(ActionEvent event) {

    }

    @FXML
    void GreaterThanQuant(ActionEvent event) {

    }

    @FXML
    void LessThanQuant(ActionEvent event) {

    }

    @FXML
    void LoginCheckUser(ActionEvent event) {
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
    		}
    	}
    }

    @FXML
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

    }

    @FXML
    void SearchParts(ActionEvent event) {
    	if(PartNameRadio.isSelected()) {
    		String searchValue = textInputDialog("Part Name","Search Part Name","Enter Part Name","Enter the part name to search:");
    		for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {
    			for(BikePart loopPart : loopWH.getInv()) {
    				if(searchValue.equalsIgnoreCase(loopPart.getName())) {
    					SortedTextArea.setText("Part found.\n");
    					SortedTextArea.appendText("Part number: " + loopPart.getNumber() + "\n");
    					SortedTextArea.appendText("Part price: " + loopPart.getActivePrice() + "\n");
    					SortedTextArea.appendText("Part quantity: " + loopPart.getStock() + "\n");
    					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");
    					break;
    				}
    			}
    		}
    		SortedTextArea.setText("Part not found in fleet.\n");
    	}
    	else if(PartNumberRadio.isSelected()) {
    		if(PartNameRadio.isSelected()) {
        		int searchValue = Integer.parseInt(textInputDialog("Part Number","Search Part Number","Enter Part Number","Enter the part number to search:"));
        		for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {
        			for(BikePart loopPart : loopWH.getInv()) {
        				if(searchValue == loopPart.getNumber()) {
        					SortedTextArea.setText("Part found.\n");
        					SortedTextArea.appendText("Part name: " + loopPart.getName() + "\n");
        					SortedTextArea.appendText("Part price: " + loopPart.getActivePrice() + "\n");
        					SortedTextArea.appendText("Part quantity: " + loopPart.getStock() + "\n");
        					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");
        					break;
        				}
        			}
        		}
        		SortedTextArea.setText("Part not found in fleet.\n");
        	}	
    	}
    	else if(QuantityRadio.isSelected()) {
    		int searchValue = Integer.parseInt(textInputDialog("Part Quantity","Search Part Quantity","Enter Part Quantity","Enter the part quantity to search:"));
    		if(searchValue > -1) {
    			if(EqualQuantRadio.isSelected()) {
    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {
            			for(BikePart loopPart : loopWH.getInv()) {
            				if(searchValue == loopPart.getStock()) {
            					SortedTextArea.appendText(loopPart.getAll() + "\n");
            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");
            				}
            			}
    				}
    			}
    			else if(GreaterQuantRadio.isSelected()) {
    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {
            			for(BikePart loopPart : loopWH.getInv()) {
            				if(searchValue > loopPart.getStock()) {
            					SortedTextArea.appendText(loopPart.getAll() + "\n");
            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");
            				}
            			}
    				}
    			}
    			else if(LesserRadioQuant.isSelected()) {
    				for(Warehouse loopWH : Main.whf.programFleet.getFleet()) {
            			for(BikePart loopPart : loopWH.getInv()) {
            				if(searchValue < loopPart.getStock()) {
            					SortedTextArea.appendText(loopPart.getAll() + "\n");
            					SortedTextArea.appendText("Found in warehouse/van: " + loopWH.getName() + "\n");
            				}
            			}
    				}
    			}
    		}
    	}
    }

    @FXML
    void SellPart(ActionEvent event) {

    }

    @FXML
    void SortNameParts(ActionEvent event) {

    }

    @FXML
    void SortNumberParts(ActionEvent event) {

    }

    @FXML
    void TransferFile(ActionEvent event) {

    }

}
