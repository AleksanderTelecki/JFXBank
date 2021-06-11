package utils;

import javafx.scene.control.Alert;
import utils.dbclasses.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public static BankUser getBankUser(int ID) {
        ResultSet result = executeQuery("SELECT * FROM BankUser WHERE BankUser.ID_BankUser=" + ID);

        BankUser bankUser = null;

        try {

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

            bankUser = new BankUser(ID_BankUser, firstname, lastname, dob, city, street, phoneNumber, email, password, bAcN, blocked, balance, postalcode);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Getting BankUser from database");
        return bankUser;

    }

    public static BankUser getBankUser(String Email, String Password, String BAcN) {
        ResultSet result = executeQuery("SELECT * FROM BankUser WHERE BankUser.Email='" + Email + "'");
        boolean validator = true;
        BankUser bankUser = null;
        try {
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Incorrect Email!");
            }
            result = executeQuery("SELECT * FROM BankUser WHERE BankUser.Password='" + Password + "'");
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Incorrect Password!");
            }
            result = executeQuery("SELECT * FROM BankUser WHERE BankUser.BAcN='" + BAcN + "'");
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Incorrect BAcN!");
            }

            if (validator) {
                bankUser = getBankUser(result.getInt("ID_BankUser"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bankUser;

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

    public static void updateUser(int ID, BankUser newBankUser) {

        String statment = "UPDATE BankUser SET " +
                "FirstName='" + newBankUser.getFirstName() + "'," +
                "LastName='" + newBankUser.getLastName() + "'," +
                "DOB='" + newBankUser.getStringDOB() + "'," +
                "City='" + newBankUser.getCity() + "'," +
                "Street='" + newBankUser.getStreet() + "'," +
                "PhoneNumber='" + newBankUser.getPhoneNumber() + "'," +
                "Email='" + newBankUser.getEmail() + "'," +
                "Password='" + newBankUser.getPassword() + "'," +
                "PostalCode='" + newBankUser.getPostalCode() + "'" +
                "WHERE BankUser.ID_BankUser=" + ID;

        executeStatement(statment);
        System.out.println("Updating BankUser row in database");


    }

    public static void deleteUser(int ID)
    {
        executeStatement("DELETE FROM BankUser WHERE BankUser.ID_BankUser="+ID);
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

    public static Savings getSavings(int ID) {

        ResultSet result = executeQuery("SELECT * FROM Savings WHERE Savings.ID_BankUser=" + ID);
        Savings savings = null;
        try {
            if (!result.isBeforeFirst()) {
                System.out.println("Savings table null!");
                System.out.println("Inserting initial values to Savings table in database ");
                insertInvestment(ID, 0.0);
                savings = getSavings(ID);
            } else {
                int ID_Savings = result.getInt("ID_Savings");
                double Investment = result.getDouble("Investment");
                String StartDate = result.getString("StartDate");
                double EardedSavings = result.getDouble("EarnedSavings");
                int ID_BankUser = result.getInt("ID_BankUser");
                savings = new Savings(ID_Savings, Investment, StartDate, EardedSavings, ID_BankUser);


                System.out.println("Getting Savings from database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return savings;

    }



    public static void insertInvestment(int ID, double investment) {
        executeStatement("INSERT INTO Savings (Investment, StartDate, EarnedSavings, ID_BankUser)" +
                "VALUES ('" + investment + "', '" + Bank.getStringCurrentDateTime() + "','" + 0.0 + "', '" + ID + "')");
        System.out.println("Inserting new Savings row to database");

    }

    public static void initializeBank() {
        executeStatement("PRAGMA foreign_keys = ON");//To on cascade deletion in foreign keys
        ResultSet result = executeQuery("SELECT * FROM Bank WHERE Bank.ID=" + 1);
        ResultSet percentage = executeQuery("SELECT * FROM Percentage");
        ResultSet date = executeQuery("SELECT datetime('now','localtime') as time;");
        try {
            Bank.initializeBank(date.getString("time"),
                    result.getString("Adress"), result.getString("Name"),
                    result.getString("Phone"), result.getString("Email"),
                    result.getString("PostalCode"), result.getString("Website"),
                    result.getInt("UserCount"), percentage.getDouble("Deposit"),
                    percentage.getDouble("Savings"));
            System.out.println("Getting Bank info from database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void updateDateTime() {
        executeStatement("UPDATE Bank SET CurrentDateTime=datetime('now','localtime') WHERE Bank.ID=" + 1);
        ResultSet result = executeQuery("SELECT * FROM Bank WHERE Bank.ID=" + 1);
        try {
            Bank.setCurrentDateTime(result.getString("CurrentDateTime"));
            System.out.println("Getting Bank.CurrentDateTime from database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static Credits getCredits(int ID, String BAcN) {

        ResultSet result = executeQuery("SELECT * FROM Credits WHERE Credits.BAcN='" + BAcN + "'");
        Credits credits = null;
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
                String StartDate = result.getString("StartDate");
                int ID_BankUser = result.getInt("ID_BankUser");
                String bacn = result.getString("BAcN");
                double overdraft = result.getDouble("Overdraft");
                credits = new Credits(ID_Credit, CreditBalance, CreditLimit, StartDate, ID_BankUser, bacn, overdraft);

                System.out.println("Getting Credits row from database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credits;

    }

    public static void insertCredits(int ID, String BAcN) {
        executeStatement("INSERT INTO Credits (CreditBalance, CreditLimit, StartDate, ID_BankUser,BAcN,Overdraft)" +
                "VALUES ('" + 0.0 + "', '" + 5000.0 + "','" + Bank.getShortStringCurrentDateTime() + "', '" + ID + "','" + BAcN + "','" + 0.0 + "')");
        System.out.println("Inserting new Credits row to database");

    }

    public static double getBalance(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM BankUser WHERE BankUser.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("Balance");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    public static double getInvestment(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Savings WHERE Savings.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("Investment");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    public static double getEarnedSavings(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Savings WHERE Savings.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("EarnedSavings");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }


    public static double getCreditDiff(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Credits WHERE Credits.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("CreditLimit") - resultSet.getDouble("CreditBalance");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    public static double getCreditBalance(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Credits WHERE Credits.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("CreditBalance");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    public static double getOverdraft(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Credits WHERE Credits.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("Overdraft");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    public static void updateBalance(int ID, double amount) {
        executeStatement("UPDATE BankUser SET Balance=" + amount + " WHERE BankUser.ID_BankUser=" + ID);
    }

    public static void updateInvestment(int ID, double amount) {
        executeStatement("UPDATE Savings SET Investment=" + amount + " WHERE Savings.ID_BankUser=" + ID);
    }

    public static void updateEarnedSavings(int ID, double amount) {
        executeStatement("UPDATE Savings SET EarnedSavings=" + amount + " WHERE Savings.ID_BankUser=" + ID);
    }

    public static void updateEarnedSavingsDate(int ID,String date,double amount)
    {
        executeStatement("UPDATE Savings SET EarnedSavings=" + amount + ", StartDate='"+date+"' WHERE Savings.ID_BankUser=" + ID);
    }

    public static void updateCreditBalance(int ID, double amount) {
        executeStatement("UPDATE Credits SET CreditBalance=" + amount + " WHERE Credits.ID_BankUser=" + ID);
    }

    public static void updateCreditLimit(int ID, double amount) {
        executeStatement("UPDATE Credits SET CreditLimit=" + amount + " WHERE Credits.ID_BankUser=" + ID);
    }

    public static void updateOverdraft(int ID, double amount) {
        executeStatement("UPDATE Credits SET Overdraft=" + amount + " WHERE Credits.ID_BankUser=" + ID);
    }

    public static void updateOverdraftWithDate(int ID,String Date ,double amount) {
        executeStatement("UPDATE Credits SET Overdraft=" + amount + ",StartDate='"+Date+"' WHERE Credits.ID_BankUser=" + ID);
    }


    public static void insertOperation(int ID, String Description, String Type, double amount) {

        ResultSet resultSet = executeQuery("SELECT * FROM Operations WHERE Operations.ID_BankUser=" + ID);


        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Operations table null!");
                System.out.println("Inserting initial values to Operations table in database ");
                executeStatement("INSERT INTO Operations (Date,Description,Type,Amount,ID_BankUser,Counter)" +
                        "VALUES ('" + Bank.getStringCurrentDateTime() + "', '" + Description + "','" + Type + "', '" + amount + "','" + ID + "','" + 1 + "')");
                System.out.println("Inserting new Operations row to database");

            } else {
                resultSet = executeQuery("SELECT max(Operations.Counter) as MaxCount FROM Operations WHERE Operations.ID_BankUser=" + ID);
                int counter = resultSet.getInt("MaxCount") + 1;
                executeStatement("INSERT INTO Operations (Date,Description,Type,Amount,ID_BankUser,Counter)" +
                        "VALUES ('" + Bank.getStringCurrentDateTime() + "', '" + Description + "','" + Type + "', '" + amount + "','" + ID + "','" + counter + "')");

                System.out.println("Inserting new Operations row to database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static List<Operations> getOperationsList(int ID) {

        ResultSet result = executeQuery("SELECT * FROM Operations WHERE Operations.ID_BankUser=" + ID);
        List<Operations> operations = new ArrayList<Operations>();
        try {
            if (!result.isBeforeFirst()) {
                System.out.println("Operations table null!");
                System.out.println("Inserting initial values to Operations table in database ");
                insertOperation(ID, "Initial operation", "Init", 0.0);
                operations = getOperationsList(ID);
            } else {
                while (result.next()) {
                    int ID_Operations = result.getInt("ID_Operation");
                    String Date = result.getString("Date");
                    String Description = result.getString("Description");
                    String Type = result.getString("Type");
                    double Amount = result.getDouble("Amount");
                    int ID_BankUser = result.getInt("ID_BankUser");
                    int Counter = result.getInt("Counter");
                    operations.add(new Operations(Date, Description, Type, Amount, ID_BankUser, Counter));
                }
                System.out.println("Getting Operations from database");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Collections.sort(operations, new Operations());
        Collections.reverse(operations);
        return operations;


    }


}
