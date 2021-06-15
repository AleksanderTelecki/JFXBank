package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.*;
import utils.dbclasses.Bank;
import utils.dbclasses.BankUser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * klasa zawiera metody pozwalajace logowac sie klientom banku
 */
//TODO: usunac komentarz
public class LoginController implements Initializer, Initializable /*interfejs Initializable wywoluje sie w momencie tworzenie klasy, wczesniej od Initializer*/ {
    //elementy interfejsu graficznego
    @FXML
    private Pane Pane_LogIn;

    @FXML
    private TextField TextBox_Email;

    @FXML
    private PasswordField TextBox_Password;

    @FXML
    private TextField TextBox_BacN;

    @FXML
    private Button Button_Admin;

    @FXML
    private Button Button_LogIn;

    @FXML
    private Button Button_NewAccount;

    private WindowController starter = new WindowController();


    /**
     * metoda wyswietla okienko logowania dla pracownika banku
     * @param event
     */
    @FXML
    void AdminLogin(ActionEvent event) {
        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowController.windowType.AdminLogIn,null);
    }

    /**
     * metoda wyswietla okienko zalogowanego klienta, jezeli dane logowania sa poprawne
     * @param event
     */
    @FXML
    void LogIn(ActionEvent event) {
        BankUser bankUser = DBcontroller.getBankUser(TextBox_Email.getText(),TextBox_Password.getText(),TextBox_BacN.getText());
        if (bankUser!=null)
        {
            starter.Show((Stage) Pane_LogIn.getScene().getWindow(),WindowController.windowType.User,bankUser.getID());

        }

    }

    /**
     * metoda wyswietla okienko rejestracji nowego klienta banku
     * @param event
     */
    @FXML
    void NewAccount(ActionEvent event) {

        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowController.windowType.Registration);
    }

    @Override
    public void Initialize(Object object) {
        BankUser user = (BankUser) object;
    }

    //TODO:  do usuniecia razem z interfejsem javaFX Initializable
    /**
     * metoda wypelnia formularz logowania klienta by nie trzeba bylo robic tego recznie
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextBox_BacN.setText("c583-4bac-9fe9");
        TextBox_Email.setText("jbojega@gmail.com");
        TextBox_Password.setText("alendo1");
    }




}