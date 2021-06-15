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

/**
 *klasa zawiera metody dla operacji wykonywanych na koncie bankowym przez klienta
 */
public class OperationController implements Initializer, Refreshable {
    //elementy GUI
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

    /**
     * metoda wywoluje metode ktora wypelni wartosciami comboBox z dostepnymi kontami klienta
     * na ktorych bedzie wykonywana operacja
     * @param event
     */
    @FXML
    void ComboChange(ActionEvent event) {
        comboboxWithoutRepeats();
    }

    /**
     * metoda zawiera akcje dla zatwierdzenia przelania srodkow miedzy kontami klienta
     * @param event
     */
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

    /**
     * metoda pozwalajaca na przeniesienie srodkow miedzy kontami tego samego klienta
     */
    private void comboboxWithoutRepeats() {
        String[] to = new String[4];
        //wybranie konta z ktorego zostana przeniesione srodki
        String value = ComboBox_From.getValue();
        int y = 0;
        //wypelnienie mozliwymi wartosciami drugiego comboBoxa, bez konta z ktorego beda przeniesione srodki
        for (String s : from) {
            if (!s.equals(value)) {
                to[y] = s;
                y++;
            }
        }
        to[3] = "Overdraft";
        //wybranie konta z ktorego zostana przeniesione srodki
        ComboBox_To.setItems(FXCollections.observableArrayList(to));
    }


    @Override
    public void refresh() {
        TextBox_Amount.setText("0.0");
        ComboBox_From.setValue("");
        ComboBox_From.setItems(FXCollections.observableArrayList(from));
        ComboBox_To.setValue("");
    }
}
