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

/**
 * klasa zawiera metody do zarzadzania kontami klientow banku dotepne dla pracownika
 */
public class ManageAccountsController implements Initializer {

    //elementy interfejsu graficznego
    @FXML
    private Pane Pane_ManageAccounts;

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_EnterAccount;

    @FXML
    private Button Button_BlockAccount;

    @FXML
    private Button Button_SetValue;

    @FXML
    private Button Button_UnblockAccount;

    @FXML
    private Label Label_Name;


    private BankUser choosedUser;

    private WindowController starter = new WindowController();

    /**
     * metoda nakladka blokade na konto bankowe zaznaczonego klienta(choosedUser)
     * @param event
     */
    @FXML
    void BlockAccount(ActionEvent event) {
        //monit o potwierdzenie akcji
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Block Account",
                "Do you want to block this account?");
        //po potwierdzeniu przyciskiem dane w oknie sa odswiezane
        //i wyswietlany jest monit z potwierdzeniem zablokowania konta
        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.blockUser(choosedUser.getID());
            WindowController.adminController.refresh();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
        }
    }

    /**
     * metoda odblokowuje zablokowane konto bankowe zaznaczonego klienta(choosedUser)
     * @param event
     */
    @FXML
    void UnblockAccount(ActionEvent event) {
        //monit o potwierdzenie akcji
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Block Account",
                "Do you want to unblock this account?");
        //po potwierdzeniu przyciskiem dane w oknie sa odswiezane i wyswietlany jest
        //monit z potwierdzeniem odblokowania konta
        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.unblockUser(choosedUser.getID());
            WindowController.adminController.refresh();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
        }
    }

    /**
     * metoda usuwa z tabeli konto bankowe zaznaczonego klienta(choosedUser)
     * @param event
     */
    @FXML
    void DeleteAccount(ActionEvent event) {
        //monit o potwierdzenie akcji
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Delete Account",
                "Do you want to delete this account?");
        //po potwierdzeniu przyciskiem dane w oknie sa odswiezane i wyswietlany jest
        //monit z potwierdzeniem odblokowania konta
        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.deleteUser(choosedUser.getID());
            //wyswietlane jest nowe okno nie zawierajace usunietego rejestru
            Stage thisStage = (Stage) Pane_ManageAccounts.getScene().getWindow();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
            WindowController.adminController.refresh();
            thisStage.close();
        }
    }

    /**
     * metoda wyswietla okno zawierajace historie akcji popelnionych przez wybranego klienta
     * @param event
     */
    @FXML
    void EnterAccount(ActionEvent event) {
        starter.Show(WindowController.windowType.User, choosedUser.getID());
    }

    /**
     * metoda pozwala pracownikowi banku wykonywac operacje na koncie klienta
     * @param event
     */
    @FXML
    void SetValue(ActionEvent event) {
        starter.Show(WindowController.windowType.SetValue, choosedUser);
    }

    @Override
    public void Initialize(Object object) {
        choosedUser = (BankUser) object;
        Label_Name.setText(choosedUser.getFullName());
    }
}
