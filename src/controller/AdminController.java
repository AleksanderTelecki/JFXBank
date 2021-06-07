package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import utils.WindowController;

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

    private WindowController starter = new WindowController();

    @FXML
    void ManageAccount(ActionEvent event) {
        starter.Show(WindowController.windowType.ManageAccounts);
    }

    @FXML
    void SaveHistory(ActionEvent event) {

    }

    @FXML
    void Refresh(ActionEvent event) {

    }

}
