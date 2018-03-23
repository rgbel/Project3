package application;

import java.io.File;
import java.util.Optional;
import javafx.stage.FileChooser; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;

public class Controller {
	
	// Initializes the fleet where all warehouses will be stored from the database
	WarehouseFleet programFleet = Main.programFleet;

	// Search and sell tab elements begin
    @FXML
    private TextField searchSellInput;

    @FXML
    private RadioButton searchRadio;

    @FXML
    private ToggleGroup searchSell;

    @FXML
    private RadioButton sellRadio;

    @FXML
    private Button SSButton;

    @FXML
    private TextArea SSTB;

    // Update and move tab elements begin
    @FXML
    private TextField updateMoveInput;

    @FXML
    private RadioButton updateRadio;

    @FXML
    private ToggleGroup updateMove;

    @FXML
    private RadioButton moveRadio;

    @FXML
    private Button UMButton;

    @FXML
    private TextArea UMTB;

    @FXML
    private Button fileButton;

    // Van and part creation tab begins
    @FXML
    private TextField vanPartInput;

    @FXML
    private RadioButton vanRadio;

    @FXML
    private ToggleGroup vanPart;

    @FXML
    private RadioButton partRadio;

    @FXML
    private Button VPButton;

    @FXML
    private TextArea VPTB;

    // Sort tab elements begin
    @FXML
    private TextField sortInput;

    @FXML
    private RadioButton numButton;

    @FXML
    private ToggleGroup sortGroup;

    @FXML
    private RadioButton alphaButton;

    @FXML
    private Button SortButton;

    @FXML
    private TextArea SortTB;

    @FXML
    void chooseFile(ActionEvent event) {
    	
    }
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
     * Upon pressing the corresponding button, the method decides whether to make an alphabetical or numerical sort of the inventory based on the radio button selection
     * @param event - The user clicks SortButton
     */
    void decideAlphaOrNum(ActionEvent event) {
    	String input = sortInput.getText();
    	//String warehouseChoice = JOptionPane.showInputDialog("Enter warehouse choice (or 'all'):");
    	if(input != null) {
    		if(alphaButton.isSelected()) {
    			SortTB.setText(WarehouseFleet.listArray(programFleet.alphaSort(input)));
    		} else {
    			SortTB.setText(WarehouseFleet.listArray(programFleet.numSort(input)));
    		}
    	}
    	else {
    		SortTB.setText("No warehouse selection made.\nPlease enter in a valid warehouse in the space provided, or 'all' to sort all warehouses.");
    	}
    }

    @FXML
    /**
     * Upon pressing the corresponding button, the method decides between either searching or selling a bike part based on the radio button selection.
     * If either is done successfully, information will be provided about the operation, otherwise an error message will be output.
     * @param event - The user clicks SSButton
     */
    void decideSearchOrSell(ActionEvent event) {
    	String input = searchSellInput.getText();
    	boolean isFound = false;
    	if(searchRadio.isSelected()) {
    		for(Warehouse loopW : programFleet.getFleet()) {
    			BikePart tempP = loopW.searchPart(input);
    			if (tempP != null) {
    				SSTB.setText("Part found. "
    						+ "\nPart name: " + tempP.getName() 
    						+ "\nPart number: " + tempP.getNumber()
    						+ "\nPart price(active): " + tempP.getActivePrice()
    						+ "\nPart quantity: " + tempP.getStock()
    						+ "\nFirst instance found in: " + loopW.getName());
    				isFound = true;
    				break;

    			}

    		} 
    		if(!isFound)
    			SSTB.setText("Part not located in any warehouse.\nCheck the entered name and try again.");
    	}
    	else {
    		String warehouseChoice = textInputDialog("Warehouse Name","Select Warehouse to Sort","Select Warehouse Dialog","Enter warehouse choice (or 'all'):");
    		if (warehouseChoice.equalsIgnoreCase("all")) 
    			SSTB.setText(programFleet.sellFromAll(input));
    		else 
    			SSTB.setText(programFleet.sellFromOne(input, warehouseChoice));
    	}
    }

    @FXML
    /**
     * After pressing the corresponding button, the method decides if the file is either a update or move order depending on the radio buttons.
     * A success message is posted if it proceeds, otherwise an error message will be displayed.
     * @param event - The user clicks the UMButton
     */
    void decideUpdateOrMove(ActionEvent event) {
    	UMTB.setText("Update or Move start.\n");
    	WarehouseFleet programFleet = Main.programFleet;
    	if(moveRadio.isSelected()) {
    		String filename = getFileName("Open File");
    		if(filename != null) {
    			programFleet.transferParts(filename);
    			UMTB.appendText("Transfer complete.");
    		} else
    			UMTB.appendText("Invalid file: file is null or does not exist.");
    	} else {
    		String warehouseChoice = textInputDialog("Warehouse Name","Select Warehouse to Update","Select Warehouse Dialog","Enter warehouse name to update:");
    		if(warehouseChoice != null) {
    			int indexW = programFleet.isWarehouse(warehouseChoice);
    			if (indexW > -1) {
    				String filename = getFileName("Open File");
    				if(filename != null) {
    					UMTB.appendText(filename + "\n");
    					programFleet.getFleet().get(indexW).updateInventory(filename);
    					UMTB.appendText("Update complete.");
    				} else
    					UMTB.appendText("No file selected.\n");
    			}
    			else
    				UMTB.appendText("Warehouse choice not found in fleet.\n");
    		}
    		else
    			UMTB.appendText("No warehouse name entered.\n");
    	}
    }

    @FXML
    /**
     * After using the corresponding button, the method adds a new van or part based on the radio button selected.
     * If a new part is made, the rest of the features of the part are interactively added before being placed in the selected warehouse.
     * @param event - The user clicks the VPButton
     */ 
    void decideVanOrPart(ActionEvent event) {
    	String input = vanPartInput.getText();
    	if(vanRadio.isSelected()) {
    		if(!input.equals("")) {
    			programFleet.newVan(input);
    			VPTB.setText("New van added to fleet with name: " + input);
    		} else {
    			VPTB.setText("Van attempted to be made with blank name; attempt failed.");
    		}
    	}
    	else {
    		int numberC = Integer.parseInt(simpleTextInputDialog("Enter part number:"));
    		double priceC = Double.parseDouble(simpleTextInputDialog("Enter part list price:"));
    		double salepriceC = Double.parseDouble(simpleTextInputDialog("Enter part sale price:"));
    		boolean onsaleC = Boolean.parseBoolean(simpleTextInputDialog("Enter part on sale indicator:"));
    		int quantityC = Integer.parseInt(simpleTextInputDialog("Enter part quantity:"));
    		String warehouseC = simpleTextInputDialog("Enter destination warehouse for new part:");
    		int indexW = programFleet.isWarehouse(warehouseC);
    		if (indexW > -1) {
    			BikePart tempPart = new BikePart(input, numberC, priceC, salepriceC, onsaleC, quantityC);
    			programFleet.getFleet().get(indexW).addPart(tempPart);
    			VPTB.setText("New part added to " + programFleet.getFleet().get(indexW).getName()
    							+ "\nNew part name: " + input
    							+ "\nNew part number: " + numberC
    							+ "\nNew part list price: " + priceC
    							+ "\nNew part sale price: " + salepriceC
    							+ "\nNew part on sale status: " + onsaleC
    							+ "\nNew part quantity: " + quantityC);
    		} else {
    			UMTB.setText("Warehouse choice not found in fleet.");
    		}
    	}
    }

}
