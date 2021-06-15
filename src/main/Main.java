package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBcontroller;

public class Main extends Application {

    /**
     * utworzenie stage'a i nawiazanie polaczenia z baza danych
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 275, 400));
        primaryStage.show();
        DBcontroller.Connect();
        DBcontroller.initializeBank();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
