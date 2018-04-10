package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import roster.Employee;
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
    private Tab ResetTab;

    @FXML
    private Button ResetButton;

    @FXML
    void ChangePassword(ActionEvent event) {

    }

    @FXML
    void CreateAccount(ActionEvent event) {

    }

    @FXML
    void DeleteAccount(ActionEvent event) {

    }

    @FXML
    void DisableQuantOption(ActionEvent event) {

    }

    @FXML
    void EnableQuantOptions(ActionEvent event) {

    }

    @FXML
    void EnterParts(ActionEvent event) {

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
    	for(Employee possible : Main.whf.programRoster.getRoster()) {
    		if(inputUser.getText().equals(possible.getLoginInfo().getUsername()) && inputPwd.getText().equals(possible.getLoginInfo().getPassword())) {
    			Main.activeUser = possible;
    			switch(possible.getLoginInfo().getPermission()) {
    			case(0):
    				CreateTab.setDisable(false);
    				DeleteTab.setDisable(false);
    				ResetTab.setDisable(false);
    			case(1):
    				SellTab.setDisable(false);
    				TransferTab.setDisable(false);
    			case(2):
    				InvoicesTab.setDisable(false);
    				SearchTab.setDisable(false);
    			case(3):
    				EnterTab.setDisable(false);
    				ReadTab.setDisable(false);
    				SearchTab.setDisable(false);
    			}
				LoginButton.setDisable(true);
				LogoutButton.setDisable(false);
    		}
    	}
    }

    @FXML
    void LogoutToHome(ActionEvent event) {
    	CreateTab.setDisable(false);
		DeleteTab.setDisable(false);
		ResetTab.setDisable(false);
		EnterTab.setDisable(false);
		ReadTab.setDisable(false);
		SearchTab.setDisable(false);
		InvoicesTab.setDisable(false);
		SellTab.setDisable(false);
		TransferTab.setDisable(false);
		LoginButton.setDisable(false);
		LogoutButton.setDisable(true);
		Main.activeUser = null;
    }

    @FXML
    void ReadFileAction(ActionEvent event) {

    }

    @FXML
    void SearchParts(ActionEvent event) {

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
