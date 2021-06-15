package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.*;
import utils.dbclasses.BankUser;
import utils.dbclasses.Operations;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * klasa zawaiera metody dostepne w okienku pracownika banku
 */
public class AdminController implements Initializer, Refreshable {

    @FXML
    private BorderPane BorderPane_Admin;

    @FXML
    private MenuItem MenuItem_SaveHistory;

    @FXML
    private MenuItem MenuItem_Refresh;

    @FXML
    private TableView<BankUser> TableView_UserInfo;

    @FXML
    private TableColumn<BankUser, Integer> Table_Column_ID;

    @FXML
    private TableColumn<BankUser, String> Table_Column_BAcN;

    @FXML
    private TableColumn<BankUser, String> Table_Column_FullName;

    @FXML
    private TableColumn<BankUser, String> Table_Column_Blocked;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Balance;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Invested;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Savings;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_CreditBalance;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_CreditLimit;

    @FXML
    private TableColumn<BankUser, Double> Table_Column_Overdraft;

    private WindowController starter = new WindowController();


    /**
     * zapisanie wartosci wyswietlanych w okienku do pliku csv
     * @param event
     */
    @FXML
    void SaveHistory(ActionEvent event) {

        CsvWriter csvWriter = new CsvWriter();
        List<BankUser> list = DBcontroller.getBankUserList();
        List<String[]> stringList = new ArrayList<>();

        String[] header = {"Account ID", "BAcN", "Name","Blocked" ,"Balance","Invested","Savings","CreditBalance","CreditLimit","Overdraft"};
       //wypisanie naglowkow w pierwszej komorce pliku csv
        stringList.add(header);

        for (BankUser item:list) {
            String [] tableValue = {Integer.toString(item.getID()),item.getBAcN(),item.getFullName(),item.getBlocked(),Double.toString(item.getBalance()),Double.toString(item.getInvested()),Double.toString(item.getSavings()),Double.toString(item.getCreditBalance()),Double.toString(item.getUserCredits().getCreditLimit()),Double.toString(item.getOverdraft())};
           //wypisywanie danych w kolejnych komorkach pliku csv
            stringList.add(tableValue);
        }
        //wybor gdzie ma zostac zapisany plik csv
        try {
            File filePath= getFilePath();
            if(filePath==null)
                return;
            csvWriter.writeToCsvFile(stringList,filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metoda wywoluje metode aktualizujaca zawartosc wyswietlanego okienka
     * @param event
     */
    @FXML
    void Refresh(ActionEvent event) {
        refresh();
    }

    /**
     * metoda sluzy wybraniu miejsca gdzie ma zostac zapisany plik csv
     * @return
     */
    private File getFilePath()
    {
        FileChooser fileChooser = new FileChooser();

        //ustawienie filtru dla plikow tesktowych by wyswietlane byly tylko pliki o rozszerzeniu csv
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //okno dialogowe zapisywania pliku csv
        File file = fileChooser.showSaveDialog(new Stage());

        return file;

    }

    @Override
    public void Initialize(Object object) {
        Stage thisStage = (Stage) BorderPane_Admin.getScene().getWindow();
        thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                starter.Show(WindowController.windowType.LogIn);
            }
        });
        //dwukrotne klikniecie na rejestr klienta otwiera okienko z mozliwymi dla tego konta akcjami
        TableView_UserInfo.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    starter.Show(WindowController.windowType.ManageAccounts, TableView_UserInfo.getSelectionModel().getSelectedItem());
                }
            }
        });

        //dane klientow wyswietlane w okienku pracownika banku
        Table_Column_ID.setCellValueFactory(new PropertyValueFactory<BankUser, Integer>("ID"));
        Table_Column_BAcN.setCellValueFactory(new PropertyValueFactory<BankUser, String>("BAcN"));
        Table_Column_FullName.setCellValueFactory(new PropertyValueFactory<BankUser, String>("FullName"));
        Table_Column_Blocked.setCellValueFactory(new PropertyValueFactory<BankUser, String>("Blocked"));
        Table_Column_Balance.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Balance"));
        Table_Column_Invested.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Invested"));
        Table_Column_Savings.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Savings"));
        Table_Column_CreditBalance.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("CreditBalance"));
        Table_Column_CreditLimit.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("CreditLimit"));
        Table_Column_Overdraft.setCellValueFactory(new PropertyValueFactory<BankUser, Double>("Overdraft"));
        refresh();

    }

    /**
     * metoda tworzy  nowy watek i odswieza zawartosc kolekcji
     */
    @Override
    public void refresh() {
       // new Thread(new Runnable() {
        //    public void run() {
                ObservableList<BankUser> data = FXCollections.observableArrayList(DBcontroller.getBankUserList());
                TableView_UserInfo.setItems(data);
        //    }
      //  }).start();
    }
}
