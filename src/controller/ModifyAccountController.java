package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.Initializer;

public class ModifyAccountController implements Initializer {

    @FXML
    private Pane Pane_ChangeAccountInfo;

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

    private int ID;

    @FXML
    void SubmitRegistration(ActionEvent event) {

    }

    @Override
    public void Initialize(Object object) {
        ID=(int)object;
    }
}
