package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.WindowController;

public class AdminLoginController {


    @FXML
    private Pane Pane_AdminLogIn;

    @FXML
    private TextField Button_AdminKey;

    @FXML
    private Button Button_Submit;

    @FXML
    private Button Button_LogIn;

    private WindowController starter = new WindowController();

    @FXML
    void LogIn(ActionEvent event) {
        starter.Show((Stage) Pane_AdminLogIn.getScene().getWindow(), WindowController.windowType.LogIn);
    }

    @FXML
    void SubmitLogIn(ActionEvent event) {
        // TODO: 28.05.2021 Data checking function or class?
        starter.Show((Stage) Pane_AdminLogIn.getScene().getWindow(), WindowController.windowType.Admin);


    }

}
