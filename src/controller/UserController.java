package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import utils.DBcontroller;
import utils.dbclasses.Bank;
import utils.dbclasses.BankUser;
import utils.Initializer;
import utils.Message;
import utils.WindowController;

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
    private TableView<String> Table_UserDate;

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

    private WindowController starter = new WindowController();

    private BankUser User;

    private int ID;

    @FXML
    void About(ActionEvent event) {

        starter.Show(WindowController.windowType.BankInfo);
    }

    @FXML
    void BankAccounts(ActionEvent event) {
        starter.Show(WindowController.windowType.BankAccounts);
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
        starter.Show(WindowController.windowType.ModifyAccounts, User.getID());
    }

    @FXML
    void Operation(ActionEvent event) {
        starter.Show(WindowController.windowType.Operation, User.getID());
    }

    @FXML
    void SaveHistory(ActionEvent event) {

    }

    @FXML
    void TransferTo(ActionEvent event) {
        starter.Show(WindowController.windowType.TransferTo, User.getID());
    }

    @FXML
    void AdminWindow(ActionEvent event) {
        starter.Show(WindowController.windowType.AdminLogIn);
    }

    @FXML
    void Refresh(ActionEvent event) {
        update();
    }

    @Override
    public void Initialize(Object object) {
        ID = (Integer) object;
        update();
    }


    public void update() {

        User = DBcontroller.getBankUser(ID);
        TextBox_BanC.setText(User.getBAcN());
        TextBox_Balance.setText(Double.toString(User.getBalance()));
        TextBox_Invested.setText(Double.toString(User.getUserSavings().getInvestment()));
        TextBox_Credit.setText(Double.toString(User.getUserCredits().getCreditDiff()));
        TextBox_Savings.setText(Double.toString(User.getUserSavings().getEarnedSavings()));
        TextBox_Overdraft.setText(Double.toString(User.getUserCredits().getOverdraft()));

        Label_BankDate.setText(Bank.getStringCurrentDate());

    }


}

