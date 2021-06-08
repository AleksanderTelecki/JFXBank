package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.*;

public class OperationController implements Initializer, Refreshable {


    @FXML
    private Pane Pane_Operation;

    @FXML
    private ComboBox<String> ComboBox_From;

    @FXML
    private ComboBox<String> ComboBox_To;

    @FXML
    private TextField TextBox_Amount;

    @FXML
    private Button Button_Submit;

    private int ID;

    private static final String[] from = {"Balance", "Savings", "Investment", "Credit"};

    @FXML
    void ComboChange(ActionEvent event) {
        comboboxWithoutRepeats();
    }

    @FXML
    void Submit(ActionEvent event) {

        try {

            CheckAndSend.Send(
                    ComboBox_From.getValue(),
                    ComboBox_To.getValue(),
                    Double.parseDouble(TextBox_Amount.getText()),
                    CheckAndSend.type.Internal,
                    ID);

        } catch (NullPointerException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Error", "Please set value in combobox");
        } catch (NumberFormatException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Error", "Invalid value in amount field");
        }

        refresh();
        WindowController.userController.refresh();
    }


    @Override
    public void Initialize(Object object) {
        ID = (int) object;
        refresh();
    }

    private void comboboxWithoutRepeats() {
        String[] to = new String[4];
        String value = ComboBox_From.getValue();
        int y = 0;
        for (String s : from) {

            if (!s.equals(value)) {
                to[y] = s;
                y++;
            }

        }
        to[3] = "Overdraft";
        ComboBox_To.setItems(FXCollections.observableArrayList(to));
    }


    @Override
    public void refresh() {
        TextBox_Amount.setText("0.0");
        ComboBox_From.setValue("");
        ComboBox_From.setItems(FXCollections.observableArrayList(from));
        ComboBox_To.setValue("");
        System.out.println("sad");
    }
}
