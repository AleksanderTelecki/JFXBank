package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.dbclasses.Bank;

import java.net.URL;
import java.util.ResourceBundle;

public class BankInfoController implements Initializable {

    @FXML
    private Label Label_Name;

    @FXML
    private Label Label_Phone;

    @FXML
    private Label Label_Email;

    @FXML
    private Label Label_Adress;

    @FXML
    private Label Label_Website;

    @FXML
    private Label Label_PostalCode;

    @FXML
    private Label Label_BankDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label_Name.setText("Name: "+Bank.getName());
        Label_Phone.setText("Phone: "+Bank.getPhone());
        Label_Email.setText("Email: "+Bank.getEmail());
        Label_Adress.setText("Address:"+Bank.getAdress());
        Label_Website.setText("Website: "+Bank.getWebsite());
        Label_PostalCode.setText("Postal Code: "+Bank.getPostalCode());
        Label_BankDate.setText("Date: "+Bank.getShortStringCurrentDateTime());
    }
}

