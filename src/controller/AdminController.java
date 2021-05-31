package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import utils.WindowStarter;

public class AdminController {

    @FXML
    private BorderPane BorderPane_Admin;

    @FXML
    private MenuItem MenuItem_SaveHistory;

    @FXML
    private MenuItem MenuItem_Refresh;

    @FXML
    private MenuItem MenuItem_ManageAcc;

    @FXML
    private TableView<?> TableView_UserInfo;

    private WindowStarter starter = new WindowStarter();

    @FXML
    void ManageAccount(ActionEvent event) {
        starter.Show(WindowStarter.windowType.ManageAccounts);
    }

    @FXML
    void SaveHistory(ActionEvent event) {

    }

    @FXML
    void Refresh(ActionEvent event) {

    }

}
