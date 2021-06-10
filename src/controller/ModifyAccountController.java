package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DBcontroller;
import utils.Initializer;
import utils.Message;
import utils.WindowController;
import utils.dbclasses.BankUser;

import java.time.LocalDate;
import java.time.ZoneId;

public class ModifyAccountController implements Initializer {

    @FXML
    private Pane Pane_ChangeAccountInfo;

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
    private PasswordField TextBox_Password;

    @FXML
    private PasswordField TextBox_PassVerification;

    @FXML
    private Button Button_Submit;

    private int ID;

    @FXML
    void SubmitModification(ActionEvent event) {
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

            DBcontroller.updateUser(ID, bankUser);
            Message.showMessage(Alert.AlertType.INFORMATION, "Modifying", "Success!");

        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "Password does not match");
        }


    }

    public void setUserData() {
        BankUser bankUser = DBcontroller.getBankUser(ID);
        TextBox_FirstName.setText(bankUser.getFirstName());
        TextBox_LastName.setText(bankUser.getLastName());
        DataPicker_DOB.setValue(bankUser.getDOB().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        TextBox_CityAdress.setText(bankUser.getCity());
        TextBox_StreetAdress.setText(bankUser.getStreet());
        TextBox_PhoneNumber.setText(bankUser.getPhoneNumber());
        TextBox_Email.setText(bankUser.getEmail());
        TextBox_Password.setText(bankUser.getPassword());
        TextBox_PassVerification.setText(bankUser.getPassword());
        TextBox_PostalCode.setText(bankUser.getPostalCode());
    }

    @Override
    public void Initialize(Object object) {
        ID = (int) object;
        setUserData();
    }
}
