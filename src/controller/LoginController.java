package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.*;
import utils.dbclasses.BankUser;

import java.util.List;

public class LoginController implements Initializer {

    @FXML
    private Pane Pane_LogIn;

    @FXML
    private TextField TextBox_Email;

    @FXML
    private TextField TextBox_Password;

    @FXML
    private TextField TextBox_BacN;

    @FXML
    private Button Button_Admin;

    @FXML
    private Button Button_LogIn;

    @FXML
    private Button Button_NewAccount;

    private WindowStarter starter = new WindowStarter();


    @FXML
    void AdminLogin(ActionEvent event) {
        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowStarter.windowType.AdminLogIn);

    }

    @FXML
    void LogIn(ActionEvent event) {
        // TODO: 28.05.2021 Data checking function or class?
        List<BankUser> bankUsers = DBcontroller.getBankUserList();

        if (bankUsers.size() != 0) {
            //starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowStarter.windowType.User,bankUsers.get(0));
            starter.Show(WindowStarter.windowType.User,bankUsers.get(0));

        }else{

        }


    }

    @FXML
    void NewAccount(ActionEvent event) {

        // TODO: 28.05.2021 Data checking function or class?
        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowStarter.windowType.Registration);


    }


    @Override
    public void Initialize(Object object) {
        BankUser user = (BankUser) object;

    }
}