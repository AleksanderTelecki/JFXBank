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

public class ManageAccountsController implements Initializer {


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

    @FXML
    void BlockAccount(ActionEvent event) {
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Block Account",
                "Do you want to block this account?");

        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.blockUser(choosedUser.getID());
            WindowController.adminController.refresh();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
        }
    }

    @FXML
    void UnblockAccount(ActionEvent event) {
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Block Account",
                "Do you want to unblock this account?");

        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.unblockUser(choosedUser.getID());
            WindowController.adminController.refresh();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
        }
    }

    @FXML
    void DeleteAccount(ActionEvent event) {
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Delete Account",
                "Do you want to delete this account?");

        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.deleteUser(choosedUser.getID());
            Stage thisStage = (Stage) Pane_ManageAccounts.getScene().getWindow();
            Message.showMessage(Alert.AlertType.INFORMATION,"Account","Successful");
            WindowController.adminController.refresh();
            thisStage.close();

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
