package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.*;
import utils.dbclasses.BankUser;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa z metodami pozwalajacymi na transfer pieniedzy miedzy kontami klientow
 */
public class TransferToController implements Initializer, Refreshable {
    //elementy interfejsu graficznego
    @FXML
    private Pane Pane_TransferTo;

    @FXML
    private ComboBox<String> ComboBox_From;

    @FXML
    private TextField TextBox_Amount;

    @FXML
    private Button Button_Submit;

    @FXML
    private ComboBox<BankUser> ComboBox_To;

    private static final String[] from = {"Balance", "Savings", "Investment", "Credit"};

    private int ID;

    /**
     * metoda wywoluje metode przenoszaca srodki ze wskazanego konta klienta na konto bankowe innego klienta
     * @param event
     */
    @FXML
    void Submit(ActionEvent event) {
        try {
            CheckAndSend.Send(
                    ComboBox_From.getValue(),
                    "Transfer",
                    Double.parseDouble(TextBox_Amount.getText()),
                    CheckAndSend.type.External,
                    ID,
                    ComboBox_To.getValue().getBAcN());
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

    @Override
    public void refresh() {
        ComboBox_From.setValue("");
        ComboBox_To.setValue(null);
        TextBox_Amount.setText("0.0");
        ComboBox_From.setItems(FXCollections.observableArrayList(from));
        ComboBox_To.setItems(FXCollections.observableArrayList(DBcontroller.getBankUserList()));
    }
}
