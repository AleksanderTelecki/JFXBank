package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.DBcontroller;
import utils.Initializer;
import utils.Refreshable;
import utils.WindowController;
import utils.dbclasses.BankUser;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializer, Refreshable {

    @FXML
    private BorderPane BorderPane_Admin;

    @FXML
    private MenuItem MenuItem_SaveHistory;

    @FXML
    private MenuItem MenuItem_Refresh;

    @FXML
    private TableView<BankUser> TableView_UserInfo;

    @FXML
    private TableColumn<BankUser, Integer> Table_Column_ID;

    @FXML
    private TableColumn<BankUser, String> Table_Column_BAcN;

    @FXML
    private TableColumn<BankUser, String> Table_Column_FullName;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Balance;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Invested;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Savings;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Credit;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Overdraft;

    private WindowController starter = new WindowController();


    @FXML
    void SaveHistory(ActionEvent event) {

    }

    @FXML
    void Refresh(ActionEvent event) {
        refresh();
    }


    @Override
    public void Initialize(Object object) {
        Stage thisStage = (Stage) BorderPane_Admin.getScene().getWindow();
        thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                starter.Show(WindowController.windowType.LogIn);
            }
        });

        TableView_UserInfo.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    starter.Show(WindowController.windowType.ManageAccounts, TableView_UserInfo.getSelectionModel().getSelectedItem());
                }
            }
        });


        Table_Column_ID.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("ID"));
        Table_Column_BAcN.setCellValueFactory(new PropertyValueFactory<BankUser, String>("BAcN"));
        Table_Column_FullName.setCellValueFactory(new PropertyValueFactory<BankUser, String>("FullName"));
        Table_Column_Balance.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Balance"));
        Table_Column_Invested.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Invested"));
        Table_Column_Savings.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Savings"));
        Table_Column_Credit.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("CreditBalance"));
        Table_Column_Overdraft.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Overdraft"));
        refresh();

    }

    @Override
    public void refresh() {
        new Thread(new Runnable() {
            public void run() {
                ObservableList<BankUser> data = FXCollections.observableArrayList(DBcontroller.getBankUserList());
                TableView_UserInfo.setItems(data);
            }
        }).start();
    }
}
