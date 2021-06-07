package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import utils.Message;
import utils.WindowController;

public class ManageAccountsController {

    @FXML
    private TextField TextBox_AccountID;

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_ShowHistory;

    @FXML
    private Button Button_Transfer;

    @FXML
    private Button Button_Operation;

    @FXML
    private Button Button_BlockAccount;

    @FXML
    private Button Button_CreditLimit;

    @FXML
    private Button Button_Overdraft;

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
    void Operation(ActionEvent event) {
        starter.Show(WindowController.windowType.Operation);
    }

    @FXML
    void SetCreditLimit(ActionEvent event) {
        starter.Show(WindowController.windowType.SetValue);
    }

    @FXML
    void SetOverdraft(ActionEvent event) {
        starter.Show(WindowController.windowType.SetValue);
    }

    @FXML
    void ShowHistory(ActionEvent event) {
        starter.Show(WindowController.windowType.ShowHistory);
    }

    @FXML
    void Transfer(ActionEvent event) {
        starter.Show(WindowController.windowType.TransferTo);
    }

}
