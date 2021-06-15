package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DBcontroller;
import utils.Initializer;
import utils.Refreshable;
import utils.dbclasses.BankUser;

public class BankAccountsController implements Initializer, Refreshable {
    // TODO: narazie nieuzywane
    /*
    @FXML
    private ScrollPane ScrollPane_Users;
*/
    @FXML
    private TableView<BankUser> Table_Users;

    @FXML
    private TableColumn<BankUser, String> Column_Name;

    @FXML
    private TableColumn<BankUser, String> Column_BAcN;

    @Override
    public void Initialize(Object object) {
        Column_Name.setCellValueFactory(new PropertyValueFactory<BankUser, String>("FullName"));
        Column_BAcN.setCellValueFactory(new PropertyValueFactory<BankUser, String>("BAcN"));
        refresh();
    }

    @Override
    public void refresh() {

        ObservableList<BankUser> data = FXCollections.observableArrayList(DBcontroller.getBankUserList());
        Table_Users.setItems(data);

    }
}

