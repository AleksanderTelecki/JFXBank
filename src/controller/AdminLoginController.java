package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DBcontroller;
import utils.Initializer;
import utils.WindowController;

/**
 * klasa zawiera metody pozwalajace zalogowac sie pracownikowi banku
 */
public class AdminLoginController implements Initializer {

    //elementy GUI
    @FXML
    private Pane Pane_AdminLogIn;

    @FXML
    private Button Button_Submit;

    @FXML
    private Button Button_LogIn;

    @FXML
    private PasswordField TextBox_AdminKey;

    private WindowController starter = new WindowController();

    /**
     * metoda wyswietla okineko logowania dla uzytkownika
     * @param event
     */
    @FXML
    void LogIn(ActionEvent event) {
        starter.Show((Stage) Pane_AdminLogIn.getScene().getWindow(), WindowController.windowType.LogIn);
    }

    /**
     * metoda wyswietla okienko przeznaczone dla pracownika banku
     * @param event
     */
    @FXML
    void SubmitLogIn(ActionEvent event) {
        if(DBcontroller.isAdminKey(TextBox_AdminKey.getText()))
        {
            //jezeli wprowadzone dane sa poprawne wyswietlane jest okienko pracownika
            starter.Show((Stage) Pane_AdminLogIn.getScene().getWindow(), WindowController.windowType.Admin,null);

        }
    }

    // TODO: mozna usunac
    /**
     * nadpisanie  metody by nie trzeba bylo wprowadzac danych po starcie aplikacji
     * @param object
     */
    @Override
    public void Initialize(Object object) {
        TextBox_AdminKey.setText("027186D790F04E4BB0FECF2D86A69536");
    }
}
