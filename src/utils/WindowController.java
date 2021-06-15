package utils;

import controller.AdminController;
import controller.OperationController;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * klasa zarzadza wyswietlanymi okienkami
 */
public class WindowController {

//klasa enum zawiera wszystkie nazwy okienek mogacych wystapic w aplikacji
    public enum windowType {None, LogIn, Registration, AdminLogIn, User, Operation, TransferTo, BankAccounts, ModifyAccounts, BankInfo, Admin, ManageAccounts, SetValue}
    //by mozna bylo sie do nich odwolac z innych okienek
    public static UserController userController;
    public static OperationController operationController;
    public static AdminController adminController;

    //metoda wyswietla Stage
    public void Show(Stage stage, windowType windowtype) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, stage);
        pair.getFirst().show();

    }
    //metoda otwiera nowe okienko
    public void Show(windowType windowtype) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, new Stage());
        pair.getFirst().show();
    }
    //metoda zamienia okno
    public void Show(Stage stage, windowType windowtype, Object object) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, stage);
        Initializer initializer = pair.getSecond().getController();
        initializer.Initialize(object);
        pair.getFirst().show();
    }
    //metoda zamienia okno i przekazuej obiekt
    public void Show(windowType windowtype, Object object) {
        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, new Stage());
        Initializer initializer = pair.getSecond().getController();
        initializer.Initialize(object);
        pair.getFirst().show();
    }

    /**
     * metoda wybiera okienko, ktore jest wyswietlane
     * @param windowtype
     * @param stage
     * @return
     */
    private Pair<Stage, FXMLLoader> getWindow(windowType windowtype, Stage stage) {
        Pair<Stage, FXMLLoader> pair = null;
        try {

            Parent window;
            Stage thisStage = stage;
            FXMLLoader loader;

            switch (windowtype) {
                case LogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Logowanie");
                }
                case Registration -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Rejestracja");
                }

                case User -> {
                    loader = new FXMLLoader(getClass().getResource("../view/User.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Klient");
                    userController = loader.getController();
                }

                case AdminLogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/AdminLogin.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Pracownik");
                }

                case Operation -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Operation.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Przelew miedzy kontami");
                    operationController = loader.getController();
                }

                case TransferTo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/TransferTo.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Zwykly przelew");
                }

                case BankAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankAccounts.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Konta w banku");
                }

                case ModifyAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ModifyAccount.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Konto klienta");
                }

                case BankInfo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankInfo.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Informacje o banku");
                }

                case Admin -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Admin.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Pracownik");
                    adminController=loader.getController();
                }

                case ManageAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ManageAccounts.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Zarzadzanie kontem");
                }

                case SetValue -> {
                    loader = new FXMLLoader(getClass().getResource("../view/SetValue.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Modyfikowanie stanu konta");
                }

                default -> throw new NullPointerException();
            }

            //rozmiar otwartego okienka nie moze by zmieniany
            thisStage.setFullScreen(false);
            thisStage.setMaximized(false);
            thisStage.setResizable(false);
            thisStage.setScene(new Scene(window));
            pair = new Pair<Stage, FXMLLoader>(thisStage, loader);//

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pair;

    }

    /**
     * klasa wewnetrzna Pair
     * @param <U> pierwszy element pary
     * @param <V> drugi element pary
     */
    public class Pair<U, V> {

        private U first;

        private V second;

        /**
         * konstruktor dwuargumentowy klasy Pair         *
         * @param first  pierwszy element
         * @param second drugi element
         */
        public Pair(U first, V second) {

            this.first = first;
            this.second = second;
        }

        //metody klasy
        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }


    }


}
