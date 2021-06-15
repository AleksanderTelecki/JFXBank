package controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import utils.*;
import utils.dbclasses.Bank;
import utils.dbclasses.BankUser;
import utils.dbclasses.Operations;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * klasa zawiera metody dla okienka zalogowanego klienta
 */
public class UserController implements Initializer, Refreshable {
    //elementy GUI
    @FXML
    private BorderPane BorderPane_User;

    @FXML
    private MenuItem MenuItem_SaveHistory;

    @FXML
    private MenuItem MenuItem_Refresh;

    @FXML
    private MenuItem MenuItem_Operate;

    @FXML
    private MenuItem MenuItem_TransferTo;

    @FXML
    private MenuItem MenuItem;

    @FXML
    private MenuItem MenuItem_DeleteAccount;

    @FXML
    private MenuItem MenuItem_About;

    @FXML
    private MenuItem MenuItem_Admin;

    @FXML
    private MenuItem MenuItem_BankAccount;


    @FXML
    private TableView<Operations> Table_UserDate;


    @FXML
    private TableColumn<Operations, String> Column_Date;

    @FXML
    private TableColumn<Operations, String> Column_Description;

    @FXML
    private TableColumn<Operations, String> Column_Type;

    @FXML
    private TableColumn<Operations, Double> Column_Amount;

    @FXML
    private TextField TextBox_BanC;

    @FXML
    private TextField TextBox_Balance;

    @FXML
    private TextField TextBox_Invested;

    @FXML
    private TextField TextBox_Savings;

    @FXML
    private TextField TextBox_Credit;

    @FXML
    private TextField TextBox_CreditBalance;

    @FXML
    private TextField TextBox_Overdraft;

    @FXML
    private Label Label_BankDate;

    private WindowController starter = new WindowController();

    private BankUser User;

    private int ID;

    /**
     * metoda wyswietla okienko z informacjami o banku
     * @param event
     */
    @FXML
    void About(ActionEvent event) {

        starter.Show(WindowController.windowType.BankInfo);
    }

    /**
     * metoda wyswietla okinko z informacjami o numerach kont bankowych pozostalych klientow banku
     * @param event
     */
    @FXML
    void BankAccounts(ActionEvent event) {
        starter.Show(WindowController.windowType.BankAccounts, null);
    }

    /**
     * metoda pozwala klientowi zamknac swoje konto w banku
     * @param event
     */
    @FXML
    void DeleteAccount(ActionEvent event) {
        //monit z prosba o potwierdzenie akcji
        Alert alert = Message.showMessageAndReturnAlertReference(Alert.AlertType.CONFIRMATION,
                "Delete Account",
                "Czy na pewno chcesz zamknac konto?");
        //po potwierdzeniu akcji konto zostaje usuniete z bazy danych
        // i otwierane jest okienko logowania dla klienta
        if (alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            DBcontroller.deleteUser(ID);
            Stage thisStage = (Stage) BorderPane_User.getScene().getWindow();
            starter.Show(WindowController.windowType.LogIn);
            thisStage.close();
        }
    }

    /**
     * metoda pozwala na modyfikacje danych osobowych klienta banku
     * @param event
     */
    @FXML
    void ModifyAccount(ActionEvent event) {
        starter.Show(WindowController.windowType.ModifyAccounts, User.getID());

    }

    /**
     * metoda pozwala na przenoszenie srodkow miedzy kontami klienta,
     * jezeli jego konto nie zostalo zablokowane
     * @param event
     */
    @FXML
    void Operation(ActionEvent event) {
        if(User.getBlocked().equals("1"))
        {
            Message.showMessage(Message.ERROR,"Account","Account is blocked!");
        }else {
            starter.Show(WindowController.windowType.Operation, User.getID());
        }

    }


    /**
     * metoda zapisuje historie akcji wykonanych z konta klienta do pliku csv
     * @param event
     */
    @FXML
    void SaveHistory(ActionEvent event) {
        CsvWriter csvWriter = new CsvWriter();
        List<Operations> list = DBcontroller.getOperationsList(ID);
        List<String[]> stringList = new ArrayList<>();

        String[] header = {"Date", "Description", "Type", "Amount"};
        //wypisanie naglowkow w pierwszej komorce pliku csv
        stringList.add(header);

        for (Operations item:list) {
            String [] tableValue = {item.getStringDate(),item.getDescription(),item.getType(),Double.toString(item.getAmount())};
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
     * metoda przenoszaca srodki z konta klienta na konto innego klienta,
     * jezeli  konto klienta nie zostalo zablokowane
     * @param event
     */
    @FXML
    void TransferTo(ActionEvent event) {
        if(User.getBlocked().equals("1"))
        {
            Message.showMessage(Message.ERROR,"Account","Account is blocked!");
        }else {
            starter.Show(WindowController.windowType.TransferTo, User.getID());
        }


    }

    /**
     * metoda wyswietla okinko logowania dla pracownika banku
     * @param event
     */
    @FXML
    void AdminWindow(ActionEvent event) {
        Stage thisStage = (Stage) BorderPane_User.getScene().getWindow();
        starter.Show(WindowController.windowType.AdminLogIn,null);
        thisStage.close();
    }

    @FXML
    void Refresh(ActionEvent event) {
        refresh();
    }

    /**
     * metoda pozwala wybrac miejsce gdzie ma zostac zapisany plik csv
     * @return
     */
    private File getFilePath()
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        return file;

    }

    @Override
    public void Initialize(Object object) {

        Stage thisStage = (Stage) BorderPane_User.getScene().getWindow();
        thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                WindowController.userController=null;
                starter.Show(WindowController.windowType.LogIn);
            }
        });

        Column_Date.setCellValueFactory(new PropertyValueFactory<Operations, String>("Sdate"));
        Column_Description.setCellValueFactory(new PropertyValueFactory<Operations, String>("Description"));
        Column_Type.setCellValueFactory(new PropertyValueFactory<Operations, String>("Type"));
        Column_Amount.setCellValueFactory(new PropertyValueFactory<Operations, Double>("Amount"));

        ID = (Integer) object;
        refresh();

        new Thread(new Runnable() {
            public void run() {
                initClock();
            }
        }).start();

    }
    /**
     * zegar wyswietlany w prawym dolnym rogu okienka zalogowanego klienta
     */
    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            Label_BankDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @Override
    public void refresh() {
        User = DBcontroller.getBankUser(ID);
        TextBox_BanC.setText(User.getBAcN());
        TextBox_Balance.setText(Double.toString(User.getBalance()));
        TextBox_Invested.setText(Double.toString(User.getUserSavings().getInvestment()));
        TextBox_Credit.setText(Double.toString(User.getUserCredits().getCreditLimit()));
        TextBox_CreditBalance.setText(Double.toString(User.getUserCredits().getCreditBalance()));
        TextBox_Savings.setText(Double.toString(User.getUserSavings().getEarnedSavings()));
        TextBox_Overdraft.setText(Double.toString(User.getUserCredits().getOverdraft()));
        ObservableList<Operations> data = FXCollections.observableArrayList(DBcontroller.getOperationsList(ID));
        Table_UserDate.setItems(data);
    }
}

