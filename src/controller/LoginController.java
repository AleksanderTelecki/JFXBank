package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DBcontroller;
import utils.Message;
import utils.WindowStarter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

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
        String sqlquery = "SELECT * FROM Bank";
        ResultSet result = DBcontroller.executeQuery(sqlquery);

        try{

            Message.showMessage(Alert.AlertType.INFORMATION,"inf",result.getString("Name"));

        }catch (Exception e){

        }

        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowStarter.windowType.User);

    }

    @FXML
    void NewAccount(ActionEvent event) {
        // TODO: 28.05.2021 Data checking function or class?
        starter.Show((Stage) Pane_LogIn.getScene().getWindow(), WindowStarter.windowType.Registration);

    }

}