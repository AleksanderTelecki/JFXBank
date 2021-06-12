package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utils.CheckAndSend;
import utils.Initializer;
import utils.Refreshable;
import utils.dbclasses.BankUser;

import java.util.UUID;

public class SetValueController implements Initializer, Refreshable {


    @FXML
    private Pane Pane_SetValue;

    @FXML
    private TextField TextBox_Value;

    @FXML
    private Button Button_Set;

    @FXML
    private ComboBox<String> ComboBox_Account;

    private BankUser bankUser;

    private static final String[] accounts = {"Balance", "Savings", "Investment", "CreditLimit","CreditBalance","Overdraft"};

    @FXML
    void SetValue(ActionEvent event) {

        CheckAndSend.Send("Admin",ComboBox_Account.getValue(),Double.parseDouble(TextBox_Value.getText()), CheckAndSend.type.Another, bankUser.getID());
        refresh();
    }

    @Override
    public void Initialize(Object object) {
        bankUser=(BankUser)object;
        refresh();
    }

    @Override
    public void refresh() {
        ComboBox_Account.setValue("");
        ComboBox_Account.setItems(FXCollections.observableArrayList(accounts));
    }
}

