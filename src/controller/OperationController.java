package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class OperationController {


    @FXML
    private Pane Pane_Operation;

    @FXML
    private ComboBox<String> CheckBox_From;

    @FXML
    private ComboBox<String> CheckBox_To;

    @FXML
    private TextField TextBox_Amount;

    @FXML
    private Button Button_Submit;


    @FXML
    void Submit(ActionEvent event) {

    }
}
