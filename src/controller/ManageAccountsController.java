package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Initializer;
import utils.Message;
import utils.WindowController;
import utils.dbclasses.BankUser;

public class ManageAccountsController implements Initializer {

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_EnterAccount;

    @FXML
    private Button Button_BlockAccount;

    @FXML
    private Button Button_SetValue;

    @FXML
    private Label Label_Name;


    private BankUser choosedUser;

    private WindowController starter = new WindowController();

    @FXML
    void BlockAccount(ActionEvent event) {
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Delete Account",
                "Do you want to delete this account?");

        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Message.showMessage(Alert.AlertType.ERROR, "Yes", "yes");
        }
    }

    @FXML
    void DeleteAccount(ActionEvent event) {
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Delete Account",
                "Do you want to delete this account?");

        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Message.showMessage(Alert.AlertType.ERROR, "Yes", "yes");
        }
    }


    @FXML
    void EnterAccount(ActionEvent event) {
        starter.Show(WindowController.windowType.User, choosedUser.getID());
    }

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
