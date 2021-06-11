package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.Initializer;
import utils.WindowController;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializer {

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


    @Override
    public void Initialize(Object object) {
        Stage thisStage = (Stage) BorderPane_Admin.getScene().getWindow();
        thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                starter.Show(WindowController.windowType.LogIn);
            }
        });


    }
}
