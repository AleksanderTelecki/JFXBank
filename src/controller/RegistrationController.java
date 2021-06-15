package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.dbclasses.BankUser;
import utils.DBcontroller;
import utils.Message;
import utils.WindowController;

/**
 * klasa zawiera metody dla okienka dodawania nowego klienta banku
 */
public class RegistrationController {
    //elementy interfejsu GUI
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
    private PasswordField TextBox_Password;

    @FXML
    private PasswordField TextBox_PassVerification;

    @FXML
    private Button Button_Submit;

    @FXML
    private Button Button_LogIn;

    private WindowController starter = new WindowController();

    /**
     * metoda wyswietla okienko rejestracji nowego klienta banku
     * @param event
     */
    @FXML
    void LogIn(ActionEvent event) {
        starter.Show((Stage) Pane_Registration.getScene().getWindow(), WindowController.windowType.LogIn);
    }

    /**
     * metoda wywolywana dla nacisniecia przycisku dodajacego klienta do bazy danych
     * metoda wywoluje metode dodajaca nowego klienta i otwiera okienka dla zalogowanego klienta
     * @param event
     */
    @FXML
    void SubmitRegistration(ActionEvent event) {
        try{
            //jezeli powtorzone haslo jest takie samo jak ustawiane halso tworzony jest
            // obiekt bankUser reprezentujacy klienta
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
                starter.Show((Stage) Pane_Registration.getScene().getWindow(), WindowController.windowType.User,bankUser.getID());

            } else {
                Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "Password does not match");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}

