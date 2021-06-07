package controller;

import com.sun.glass.ui.Window;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.CheckAndSend;
import utils.Initializer;
import utils.Message;
import utils.WindowController;

import java.text.ParseException;

public class OperationController implements Initializer {


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


    @FXML
    void ComboChange(ActionEvent event) {
        changeToItems();
    }

    @FXML
    void Submit(ActionEvent event) {

        try
        {
            CheckAndSend.Send(getEnum(ComboBox_From.getValue()), getEnum(ComboBox_To.getValue()), Double.parseDouble(TextBox_Amount.getText()), ID);

        }catch (NullPointerException e)
        {
            Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
            return;
        }catch (NumberFormatException e)
        {
            Message.showMessage(Alert.AlertType.ERROR, "Error", "Wrong data in amount field");
            return;

        }

        WindowController.userController.update();
    }


    @Override
    public void Initialize(Object object) {
        ID = (int) object;
        TextBox_Amount.setText(Integer.toString(ID));
        String[] from = {"Balance", "Savings", "Investment", "Credit"};
        ComboBox_From.setItems(FXCollections.observableArrayList(from));

    }

    private void changeToItems() {
        String[] from = {"Balance", "Savings", "Investment", "Credit"};
        String[] to = new String[4];
        String value = ComboBox_From.getValue();
        int y = 0;
        for (String s : from) {

            if (s != value) {
                to[y] = s;
                y++;
            }

        }

        to[3] = "Overdraft";


        ComboBox_To.setItems(FXCollections.observableArrayList(to));
    }

    private CheckAndSend.operationType getEnum(String combostring) {
        switch (combostring) {
            case "Balance" -> {
                return CheckAndSend.operationType.Balance;
            }

            case "Savings" -> {
                return CheckAndSend.operationType.Savings;
            }

            case "Credit" -> {
                return CheckAndSend.operationType.Credit;
            }

            case "Overdraft" -> {
                return CheckAndSend.operationType.Overdraft;
            }

            case "Investment" -> {
                return CheckAndSend.operationType.Investment;
            }

            default -> {
                return CheckAndSend.operationType.None;
            }


        }


    }

}
