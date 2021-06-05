package utils;

import javafx.scene.control.Alert;
import utils.dbclasses.Bank;
import utils.dbclasses.BankUser;
import utils.dbclasses.Credits;
import utils.dbclasses.Savings;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("ExecutedStatment: " + statementToExecute);
        } catch (SQLException e) {
            System.out.println("Execute Statmnet() ->Unable to execute query");
            e.printStackTrace();

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
            System.out.println("ExecutedQuery: " + queryToExecute);
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

        System.out.println("Inserting new BankUser row to database");
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

        System.out.println("Getting BankUsers list from database");
        return bankUsers;

    }

    public static int getID(String BAcN) {
        ResultSet result = executeQuery("SELECT ID_BankUser FROM BankUser WHERE BankUser.BAcN='" + BAcN + "'");
        try {
            System.out.println("Getting ID_BankUser from database");
            return result.getInt("ID_BankUser");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    public static List<Savings> getSavings(int ID) {

        ResultSet result = executeQuery("SELECT * FROM Savings WHERE Savings.ID_BankUser=" + ID);
        List<Savings> savingList = new ArrayList<Savings>();
        try {
            if (!result.isBeforeFirst()) {
                System.out.println("Savings table null!");
                System.out.println("Inserting initial values to Savings table in database ");
                insertInvestment(ID, 0.0);
                savingList = getSavings(ID);
            } else {

                while (result.next()) {
                    int ID_Savings = result.getInt("ID_Savings");
                    double Investment = result.getDouble("Investment");
                    String StartDate = result.getString("StartDate");
                    double EardedSavings = result.getDouble("EarnedSavings");
                    int ID_BankUser = result.getInt("ID_BankUser");
                    Savings savings = new Savings(ID_Savings, Investment, StartDate, EardedSavings, ID_BankUser);
                    savingList.add(savings);
                }
                System.out.println("Getting Savings from database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return savingList;

    }

    public static void insertInvestment(int ID, double investment) {
        executeStatement("INSERT INTO Savings (Investment, StartDate, EarnedSavings, ID_BankUser)" +
                "VALUES ('" + investment + "', '" + Bank.getStringCurrentDate() + "','" + 0.0 + "', '" + ID + "')");
        System.out.println("Inserting new Savings row to database");

    }

    public static void initializeBank() {
        ResultSet result = executeQuery("SELECT * FROM Bank WHERE Bank.ID=" + 1);
        ResultSet percentage = executeQuery("SELECT * FROM Percentage");
        try {
            Bank.initializeBank(result.getString("CurrentDate"), result.getString("Adress"), result.getString("Name"), result.getString("Phone"), result.getString("Email"), result.getString("PostalCode"), result.getString("Website"), result.getInt("UserCount"),percentage.getDouble("Deposit"),percentage.getDouble("Savings"));
            System.out.println("Getting Bank info from database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static Credits getCredits(int ID, String BAcN) {

        ResultSet result = executeQuery("SELECT * FROM Credits WHERE Credits.BAcN='" + BAcN + "'");
        Credits credits=null;
        try {
            if (!result.isBeforeFirst()) {
                System.out.println("Credit table null!");
                System.out.println("Inserting initial values to Credit table in database ");
                insertCredits(ID, BAcN);
                credits = getCredits(ID, BAcN);
            } else {

                int ID_Credit = result.getInt("ID_Credit");
                double CreditBalance = result.getDouble("CreditBalance");
                double CreditLimit = result.getDouble("CreditLimit");
                double FinanceCharge = result.getDouble("FinanceCharge");
                int ID_BankUser = result.getInt("ID_BankUser");
                String bacn = result.getString("BAcN");
                credits = new Credits(ID_Credit, CreditBalance, CreditLimit, FinanceCharge, ID_BankUser,bacn);
                System.out.println("Getting Credits row from database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credits;

    }

    public static void insertCredits(int ID, String BAcN) {
        executeStatement("INSERT INTO Credits (CreditBalance, CreditLimit, FinanceCharge, ID_BankUser,BAcN)" +
                "VALUES ('" + 0.0 + "', '" + 5000.0 + "','" + 5.0 + "', '" + ID + "','" + BAcN + "')");
        System.out.println("Inserting new Credits row to database");

    }


}
