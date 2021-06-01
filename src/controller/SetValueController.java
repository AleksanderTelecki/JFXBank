package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.UUID;

public class SetValueController {

    @FXML
    private Pane Pane_SetValue;

    @FXML
    private TextField TextBox_Value;

    @FXML
    private Button Button_Set;

    @FXML
    void SetValue(ActionEvent event) {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().substring(9,23);
        TextBox_Value.setText(uuidAsString);


    }

}

