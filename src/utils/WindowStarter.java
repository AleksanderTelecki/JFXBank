package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowStarter {


    public enum windowType {None, LogIn, Registration, AdminLogIn, User, Operation, TransferTo, BankAccounts, ModifyAccounts, BankInfo, Admin, ManageAccounts, ShowHistory, SetValue}

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

    public void Show(Stage stage, windowType windowtype, Object object) {


        try {

            Parent window;
            Stage currentStage = stage;
            FXMLLoader loader;

            switch (windowtype) {
                case None -> throw new NullPointerException();
                case LogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Log In");
                }
                case Registration -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Registration");
                }

                case User -> {
                    loader = new FXMLLoader(getClass().getResource("../view/User.fxml"));
                    window = loader.load();
                    currentStage.setTitle("User");
                }


                case AdminLogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/AdminLogin.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Log In");
                }

                case Operation -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Operation.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Operation");
                }

                case TransferTo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/TransferTo.fxml"));
                    window = loader.load();
                    currentStage.setTitle("TransferTo");
                }

                case BankAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankAccounts.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Bank Accounts");
                }

                case ModifyAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ModifyAccount.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Modify Account");
                }

                case BankInfo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankInfo.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Bank Information");
                }

                case Admin -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Admin.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Admin");
                }

                case ManageAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ManageAccounts.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Manage Account");
                }

                case ShowHistory -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ShowHistory.fxml"));
                    window = loader.load();
                    currentStage.setTitle("History");
                }

                case SetValue -> {
                    loader = new FXMLLoader(getClass().getResource("../view/SetValue.fxml"));
                    window = loader.load();
                    currentStage.setTitle("Set Value");
                }

                default -> throw new NullPointerException();
            }


            currentStage.setFullScreen(false);
            currentStage.setMaximized(false);
            currentStage.setResizable(false);



            Initializer controller = loader.getController();
            controller.Initialize(object);

            currentStage.setScene(new Scene(window));
            currentStage.show();




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Show(windowType windowtype, Object object) {


        try {

            Parent window;
            Stage newStage = new Stage();
            FXMLLoader loader;

            switch (windowtype) {
                case None -> throw new NullPointerException();
                case LogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
                    window = loader.load();
                    newStage.setTitle("Log In");
                }
                case Registration -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
                    window = loader.load();
                    newStage.setTitle("Registration");
                }

                case User -> {
                    loader = new FXMLLoader(getClass().getResource("../view/User.fxml"));
                    window = loader.load();
                    newStage.setTitle("User");
                }


                case AdminLogIn -> {
                    loader = new FXMLLoader(getClass().getResource("../view/AdminLogin.fxml"));
                    window = loader.load();
                    newStage.setTitle("Log In");
                }

                case Operation -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Operation.fxml"));
                    window = loader.load();
                    newStage.setTitle("Operation");
                }

                case TransferTo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/TransferTo.fxml"));
                    window = loader.load();
                    newStage.setTitle("TransferTo");
                }

                case BankAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankAccounts.fxml"));
                    window = loader.load();
                    newStage.setTitle("Bank Accounts");
                }

                case ModifyAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ModifyAccount.fxml"));
                    window = loader.load();
                    newStage.setTitle("Modify Account");
                }

                case BankInfo -> {
                    loader = new FXMLLoader(getClass().getResource("../view/BankInfo.fxml"));
                    window = loader.load();
                    newStage.setTitle("Bank Information");
                }

                case Admin -> {
                    loader = new FXMLLoader(getClass().getResource("../view/Admin.fxml"));
                    window = loader.load();
                    newStage.setTitle("Admin");
                }

                case ManageAccounts -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ManageAccounts.fxml"));
                    window = loader.load();
                    newStage.setTitle("Manage Account");
                }

                case ShowHistory -> {
                    loader = new FXMLLoader(getClass().getResource("../view/ShowHistory.fxml"));
                    window = loader.load();
                    newStage.setTitle("History");
                }

                case SetValue -> {
                    loader = new FXMLLoader(getClass().getResource("../view/SetValue.fxml"));
                    window = loader.load();
                    newStage.setTitle("Set Value");
                }

                default -> throw new NullPointerException();
            }


            newStage.setFullScreen(false);
            newStage.setMaximized(false);
            newStage.setResizable(false);



            Initializer controller = loader.getController();
            controller.Initialize(object);

            newStage.setScene(new Scene(window));
            newStage.show();




        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
