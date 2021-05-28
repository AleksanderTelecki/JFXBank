package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowStarter;

public class RegistrationController {

    @FXML
    private Pane Pane_Registration;

    @FXML
    private TextField TextBox_FirstName;

    @FXML
    private TextField TextBox_LastName;

    @FXML
    private TextField TextBox_DOB;

    @FXML
    private TextField TextBox_CityAdress;

    @FXML
    private TextField TextBox_StreetAdress;

    @FXML
    private TextField TextBox_PhoneNumber;

    @FXML
    private TextField TextBox_PostalCode;

    @FXML
    private TextField TextBox_Email;

    @FXML
    private TextField TextBox_Password;

    @FXML
    private TextField TextBox_PassVerification;

    @FXML
    private Button Button_Submit;

    @FXML
    private Button Button_LogIn;

    private WindowStarter starter = new WindowStarter();


    @FXML
    void LogIn(ActionEvent event) {
        starter.ReplaceShow((Stage) Pane_Registration.getScene().getWindow(), WindowStarter.windowType.LogIn);
    }

    @FXML
    void SubmitRegistration(ActionEvent event) {
        // TODO: 28.05.2021 Data checking function or class?
        starter.ReplaceShow((Stage)Pane_Registration.getScene().getWindow(), WindowStarter.windowType.User );
    }

}

