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

public class LoginController implements Initializer, Initializable {

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


    @FXML
    void AdminLogin(ActionEvent event) {
        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowController.windowType.AdminLogIn,null);

    }

    @FXML
    void LogIn(ActionEvent event) {
        BankUser bankUser = DBcontroller.getBankUser(TextBox_Email.getText(),TextBox_Password.getText(),TextBox_BacN.getText());
        if (bankUser!=null)
        {
            starter.Show((Stage) Pane_LogIn.getScene().getWindow(),WindowController.windowType.User,bankUser.getID());

        }

    }

    @FXML
    void NewAccount(ActionEvent event) {

        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowController.windowType.Registration);
    }


    @Override
    public void Initialize(Object object) {
        BankUser user = (BankUser) object;




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: 10.06.2021 Remove when realese
        TextBox_BacN.setText("c583-4bac-9fe9");
        TextBox_Email.setText("jbojega@gmail.com");
        TextBox_Password.setText("alendo1");
    }
}