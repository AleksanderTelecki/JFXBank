package utils;

import javafx.scene.control.Alert;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            Message.showMessage(Alert.AlertType.INFORMATION, "Statment Info", "Success!");
        } catch (SQLException e) {
            e.printStackTrace();
            Message.showMessage(Alert.AlertType.INFORMATION, "Statment Info", "Failed!");
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

    public static List<BankUser> getBankUserList() {

        ResultSet result = executeQuery("SELECT * FROM BankUser");

        List<BankUser> bankUsers = new ArrayList<BankUser>();

        try {

            while (result.next()) {

                int ID_BankUser = result.getInt("ID_BankUser");
                String firstname = result.getString("FirstName");
                String lastname = result.getString("LastName");
                String dob = result.getString("DOB");
                String city = result.getString("City");
                String street = result.getString("Street");
                String phoneNumber = result.getString("PhoneNumber");
                String email = result.getString("Email");
                String password = result.getString("Password");
                String bAcN = result.getString("BAcN");
                String blocked = result.getString("Blocked");
                double balance = result.getDouble("Balance");
                String postalcode = result.getString("PostalCode");

                bankUsers.add(new BankUser(ID_BankUser, firstname, lastname, dob, city, street, phoneNumber, email, password, bAcN, blocked, balance, postalcode));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankUsers;

    }


}
