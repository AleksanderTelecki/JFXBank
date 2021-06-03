package utils;

import javafx.scene.control.Alert;

import java.awt.*;
import java.sql.*;
import java.util.UUID;

public class DBcontroller {

    private static final String DBpath = "jbankDB.db";
    private static Connection connection;


    public static void Connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBpath);
            System.out.println("Successful connected");

        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    /**
     * Executes sql against the database.
     *
     * @param statementToExecute the sql statement.
     */
    public static void executeStatement(String statementToExecute) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(statementToExecute);
            Message.showMessage(Alert.AlertType.INFORMATION,"Statment Info","Success!");
        } catch (SQLException e) {
            e.printStackTrace();
            Message.showMessage(Alert.AlertType.INFORMATION,"Statment Info","Failed!");
        }
    }

    /**
     * Executes a select on the database
     *
     * @param queryToExecute the select statemetn
     * @return the resultset
     */
    public static ResultSet executeQuery(String queryToExecute) {
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(queryToExecute);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Execute Query() ->Unable to execute query");
            e.printStackTrace();
            return null;
        }
    }

    public static void registerUser(BankUser bankUser) {


        String statment = "INSERT INTO BankUser " +
                "(FirstName,LastName,DOB,City,Street,PhoneNumber,Email,Password,BAcN,Balance,PostalCode) " +
                "VALUES('" + bankUser.getFirstName() + "'," +
                "'" + bankUser.getLastName() + "'," +
                "'" + bankUser.getStringDOB() + "'," +
                "'" + bankUser.getCity() + "'," +
                "'" + bankUser.getStreet() + "'," +
                "'" + bankUser.getPhoneNumber() + "'," +
                "'" + bankUser.getEmail() + "'," +
                "'" + bankUser.getPassword() + "'," +
                "'" + bankUser.getBAcN() + "'," +
                "'" + bankUser.getBalance() + "'," +
                "'" + bankUser.getPostalCode() + "')";

        System.out.println(statment);
        executeStatement(statment);


    }


}
