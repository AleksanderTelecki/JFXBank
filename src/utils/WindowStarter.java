package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowStarter {


    public enum windowType {None, LogIn, Registration, AdminLogIn, User, Operation, TransferTo, BankAccounts, ModifyAccounts, BankInfo, Admin, ManageAccounts,ShowHistory,SetValue}

    public void Show(Stage stage, windowType windowtype) {


        try {

            Parent window;
            Stage currentStage = stage;
            switch (windowtype) {
                case None -> throw new NullPointerException();
                case LogIn -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    currentStage.setTitle("Log In");
                }
                case Registration -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));

                    currentStage.setTitle("Registration");
                }

                case User -> {
                    window = FXMLLoader.load(getClass().getResource("../view/User.fxml"));
                    currentStage.setTitle("User");
                }


                case AdminLogIn -> {
                    window = FXMLLoader.load(getClass().getResource("../view/AdminLogin.fxml"));
                    currentStage.setTitle("Log In");
                }

                case Operation -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Operation.fxml"));
                    currentStage.setTitle("Operation");
                }

                case TransferTo -> {
                    window = FXMLLoader.load(getClass().getResource("../view/TransferTo.fxml"));
                    currentStage.setTitle("TransferTo");
                }

                case BankAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/BankAccounts.fxml"));
                    currentStage.setTitle("Bank Accounts");
                }

                case ModifyAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ModifyAccount.fxml"));
                    currentStage.setTitle("Modify Account");
                }

                case BankInfo -> {
                    window = FXMLLoader.load(getClass().getResource("../view/BankInfo.fxml"));
                    currentStage.setTitle("Bank Information");
                }

                case Admin -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
                    currentStage.setTitle("Admin");
                }

                case ManageAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ManageAccounts.fxml"));
                    currentStage.setTitle("Manage Account");
                }

                case ShowHistory -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ShowHistory.fxml"));
                    currentStage.setTitle("History");
                }

                case SetValue -> {
                    window = FXMLLoader.load(getClass().getResource("../view/SetValue.fxml"));
                    currentStage.setTitle("Set Value");
                }

                default -> throw new NullPointerException();
            }


            currentStage.setFullScreen(false);
            currentStage.setMaximized(false);
            currentStage.setResizable(false);

            currentStage.setScene(new Scene(window));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Show(windowType windowtype) {


        try {

            Parent window;
            Stage newStage = new Stage();


            switch (windowtype) {
                case None -> throw new NullPointerException();
                case LogIn -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    newStage.setTitle("Log In");
                }
                case Registration -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
                    newStage.setTitle("Registration");
                }

                case User -> {
                    window = FXMLLoader.load(getClass().getResource("../view/User.fxml"));
                    newStage.setTitle("User");
                }


                case AdminLogIn -> {
                    window = FXMLLoader.load(getClass().getResource("../view/AdminLogin.fxml"));
                    newStage.setTitle("Log In");
                }

                case Operation -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Operation.fxml"));
                    newStage.setTitle("Operation");
                }

                case TransferTo -> {
                    window = FXMLLoader.load(getClass().getResource("../view/TransferTo.fxml"));
                    newStage.setTitle("TransferTo");
                }

                case BankAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/BankAccounts.fxml"));
                    newStage.setTitle("Bank Accounts");
                }

                case ModifyAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ModifyAccount.fxml"));
                    newStage.setTitle("Modify Account");
                }

                case BankInfo -> {
                    window = FXMLLoader.load(getClass().getResource("../view/BankInfo.fxml"));
                    newStage.setTitle("Bank Information");
                }

                case Admin -> {
                    window = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
                    newStage.setTitle("Admin");
                }

                case ManageAccounts -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ManageAccounts.fxml"));
                    newStage.setTitle("Manage Account");
                }

                case ShowHistory -> {
                    window = FXMLLoader.load(getClass().getResource("../view/ShowHistory.fxml"));
                    newStage.setTitle("History");
                }

                case SetValue -> {
                    window = FXMLLoader.load(getClass().getResource("../view/SetValue.fxml"));
                    newStage.setTitle("Set Value");
                }

                default -> throw new NullPointerException();
            }


            newStage.setFullScreen(false);
            newStage.setMaximized(false);
            newStage.setResizable(false);
            newStage.setScene(new Scene(window));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
