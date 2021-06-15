package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.dbclasses.Bank;

import java.net.URL;
import java.util.ResourceBundle;
/**
 *klasa zawiera metode wyswietlajaca w nowym okienku informacje o banku
 */
public class BankInfoController implements Initializable {
    //elementy interfejsu graficznego
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
        Label_Name.setText("Nazwa: "+Bank.getName());
        Label_Phone.setText("Numer telefonu: "+Bank.getPhone());
        Label_Email.setText("Email: "+Bank.getEmail());
        Label_Adress.setText("Adres:"+Bank.getAdress());
        Label_Website.setText("Adres www: "+Bank.getWebsite());
        Label_PostalCode.setText("Kod pocztowy: "+Bank.getPostalCode());
        Label_BankDate.setText("Aktualna czas: "+Bank.getShortStringCurrentDateTime());
    }
}

