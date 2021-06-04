package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import utils.BankUser;
import utils.Initializer;
import utils.Message;
import utils.WindowStarter;

public class UserController implements Initializer {

    @FXML
    private BorderPane BorderPane_User;

    @FXML
    private MenuItem MenuItem_SaveHistory;

    @FXML
    private MenuItem MenuItem_Refresh;

    @FXML
    private MenuItem MenuItem_Operate;

    @FXML
    private MenuItem MenuItem_TransferTo;

    @FXML
    private MenuItem MenuItem;

    @FXML
    private MenuItem MenuItem_DeleteAccount;

    @FXML
    private MenuItem MenuItem_About;

    @FXML
    private MenuItem MenuItem_Admin;

    @FXML
    private MenuItem MenuItem_BankAccount;

    @FXML
    private TableView<?> Table_UserDate;

    @FXML
    private TextField TextBox_BanC;

    @FXML
    private TextField TextBox_Balance;

    @FXML
    private TextField TextBox_Invested;

    @FXML
    private TextField TextBox_Savings;

    @FXML
    private TextField TextBox_Credit;

    @FXML
    private TextField TextBox_Overdraft;

    @FXML
    private Label Label_BankDate;

    private WindowStarter starter = new WindowStarter();

    private BankUser User;

    @FXML
    void About(ActionEvent event) {

        starter.Show(WindowStarter.windowType.BankInfo);
    }

    @FXML
    void BankAccounts(ActionEvent event) {
        starter.Show(WindowStarter.windowType.BankAccounts);
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
    void ModifyAccount(ActionEvent event) {
        starter.Show(WindowStarter.windowType.ModifyAccounts);
    }

    @FXML
    void Operation(ActionEvent event) {
        starter.Show(WindowStarter.windowType.Operation);
    }

    @FXML
    void SaveHistory(ActionEvent event) {

    }

    @FXML
    void TransferTo(ActionEvent event) {
        starter.Show(WindowStarter.windowType.TransferTo);
    }

    @FXML
    void AdminWindow(ActionEvent event) {
        starter.Show(WindowStarter.windowType.AdminLogIn);
    }

    @FXML
    void Refresh(ActionEvent event) {

    }

    @Override
    public void Initialize(Object object) {
         User = (BankUser)object;
         setUser();
    }

    private void setUser()
    {
        TextBox_BanC.setText(User.getBAcN());
        TextBox_Balance.setText(Double.toString(User.getBalance()));


    }


}

