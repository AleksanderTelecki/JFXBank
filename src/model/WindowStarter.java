package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class  WindowStarter {


    public enum windowType {None, LogIn, Registration, AdminLogIn, User}

    public void ReplaceShow(Stage stage,windowType windowtype)
    {


        try {

            Parent window;
            Stage currentStage;
            switch (windowtype)
            {
                case None->  throw new NullPointerException();
                case LogIn -> {
                     window = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                     currentStage = stage;
                    currentStage.setTitle("Log In");
                }
                case Registration-> {
                     window = FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
                     currentStage = stage;
                    currentStage.setTitle("Registration");
                }

                case User-> {
                    window = FXMLLoader.load(getClass().getResource("../view/User.fxml"));
                    currentStage = stage;
                    currentStage.setTitle("User Window");
                }


                case AdminLogIn -> {
                     window = FXMLLoader.load(getClass().getResource("../view/AdminLogin.fxml"));
                     currentStage = stage;
                    currentStage.setTitle("Log In");
                }

                default -> throw new NullPointerException();
            }



            currentStage.setScene(new Scene(window));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }






    }

    public void Show(Stage stage,windowType windowtype)
    {



    }






}
