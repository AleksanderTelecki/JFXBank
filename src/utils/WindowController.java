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

public class WindowController {


    public enum windowType {None, LogIn, Registration, AdminLogIn, User, Operation, TransferTo, BankAccounts, ModifyAccounts, BankInfo, Admin, ManageAccounts, SetValue}

    public static UserController userController;
    public static OperationController operationController;
    public static AdminController adminController;

    public void Show(Stage stage, windowType windowtype) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, stage);
        pair.getFirst().show();

    }

    public void Show(windowType windowtype) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, new Stage());
        pair.getFirst().show();
    }

    public void Show(Stage stage, windowType windowtype, Object object) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, stage);
        Initializer initializer = pair.getSecond().getController();
        initializer.Initialize(object);
        pair.getFirst().show();
    }

    public void Show(windowType windowtype, Object object) {

        Pair<Stage, FXMLLoader> pair = getWindow(windowtype, new Stage());
        Initializer initializer = pair.getSecond().getController();
        initializer.Initialize(object);
        pair.getFirst().show();
    }


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
                    thisStage.setTitle("Log In");
                }
                case Registration -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Registration");
                }

                case User -> {
                    loader = new FXMLLoader(getClass().getResource("../view/User.fxml"));
                    window = loader.load();
                    thisStage.setTitle("User");
                    // TODO: 12.06.2021 Maybe create list of userWindows to refresh more than one window
                    userController = loader.getController();

                }


                case AdminLogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/AdminLogin.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Log In");
                }

                case Operation -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Operation.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Operation");
                    operationController = loader.getController();
                }

                case TransferTo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/TransferTo.fxml"));
                    window = loader.load();
                    thisStage.setTitle("TransferTo");
                }

                case BankAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankAccounts.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Bank Accounts");
                }

                case ModifyAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ModifyAccount.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Modify Account");
                }

                case BankInfo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankInfo.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Bank Information");
                }

                case Admin -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Admin.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Admin");
                    adminController=loader.getController();
                }

                case ManageAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ManageAccounts.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Manage Account");
                }

                case SetValue -> {
                    loader = new FXMLLoader(getClass().getResource("../view/SetValue.fxml"));
                    window = loader.load();
                    thisStage.setTitle("Set Value");
                }

                default -> throw new NullPointerException();
            }


            thisStage.setFullScreen(false);
            thisStage.setMaximized(false);
            thisStage.setResizable(false);
            thisStage.setScene(new Scene(window));
            pair = new Pair<Stage, FXMLLoader>(thisStage, loader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pair;

    }

    public class Pair<U, V> {

        /**
         * The first element of this <code>Pair</code>
         */
        private U first;

        /**
         * The second element of this <code>Pair</code>
         */
        private V second;

        /**
         * Constructs a new <code>Pair</code> with the given values.
         *
         * @param first  the first element
         * @param second the second element
         */
        public Pair(U first, V second) {

            this.first = first;
            this.second = second;
        }


        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }


    }


}
