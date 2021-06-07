package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.Initializer;

public class TransferToController implements Initializer {

    @FXML
    private Pane Pane_TransferTo;

    @FXML
    private ComboBox<?> CheckBox_From;

    @FXML
    private TextField TextBox_Amount;

    @FXML
    private Button Button_Submit;

    @FXML
    private TextField TextBox_BanC;

    private int ID;

    @FXML
    void Submit(ActionEvent event) {

    }

    @Override
    public void Initialize(Object object) {
        ID=(int)object;
    }
}
