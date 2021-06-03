package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.BankUser;
import utils.DBcontroller;
import utils.Message;
import utils.WindowStarter;

import java.time.format.DateTimeFormatter;

public class RegistrationController {

    @FXML
    private Pane Pane_Registration;

    @FXML
    private TextField TextBox_FirstName;

    @FXML
    private TextField TextBox_LastName;

    @FXML
    private DatePicker DataPicker_DOB;

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
        starter.Show((Stage) Pane_Registration.getScene().getWindow(), WindowStarter.windowType.LogIn);
    }

    @FXML
    void SubmitRegistration(ActionEvent event) {
        // TODO: 28.05.2021 Data checking function or class?

        if (TextBox_Password.getText().equals(TextBox_PassVerification.getText())) {
            BankUser bankUser = new BankUser(TextBox_FirstName.getText(),
                    TextBox_LastName.getText(),
                    DataPicker_DOB.getEditor().getText(),
                    TextBox_CityAdress.getText(),
                    TextBox_StreetAdress.getText(),
                    TextBox_PhoneNumber.getText(),
                    TextBox_Email.getText(),
                    TextBox_Password.getText(),
                    TextBox_PostalCode.getText());

            DBcontroller.registerUser(bankUser);
            starter.Show((Stage) Pane_Registration.getScene().getWindow(), WindowStarter.windowType.User);

        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "Password does not match");
        }

    }

}

