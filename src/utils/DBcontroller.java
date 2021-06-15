package utils;

import javafx.scene.control.Alert;
import utils.dbclasses.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * klasa kontorluje operacje wykonywane na bazie danych
 */
public class DBcontroller {

    private static final String DBpath = "jbankDB.db";
    private static Connection connection;


    /**
     * metoda nawiazuje polaczenie z baza danych
     */
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

    /**
     * wykonuje zapytanie na bazie danych
     * @param statementToExecute zapytanie do bazy danych
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
     * wykonuje zapytanie SELECT na bazie danych
     * @param queryToExecute zapytanie SELECT
     * @return wynik zapytania
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

    /**
     * metoda sprawdza czy wprowadzone haslo administratora znajduje sie w bazie danych
     * @param adminKey
     * @return wartosc bool true jesli haslo znajduje sie w bazie danych
     * lub wartosc false gdy go tam nie ma
     */
    public static boolean isAdminKey(String adminKey)
    {   //result zawiera wynik operacji SELECT szukajacej wprowadzonego hasla
        // w bazie danych
        ResultSet result = executeQuery("SELECT * FROM Admin WHERE Admin.AdminKey='"+adminKey+"'");
        boolean isadminkey=false;
        try {
            //jezeli po wykonaniu zapytania szukania wprowadzonego hasla admina w bazie danych
            //kursor znajduje sie na koncu tabeli(isBeforeFirst()==true) oznacza to, ze nie znaleziono takiego hasla
            if (!result.isBeforeFirst()) {
                Message.showMessage(Alert.AlertType.ERROR, "AdminLog In", "Niepoprawne haslo");
            }else {
                isadminkey=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return isadminkey;

    }

    /**
     * metoda dodaje nowego klienta banku do bazy danych
     * @param bankUser
     */
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

    /**
     * metoda wyswietla w okienku dane z tabeli o kliencie z numerem ID przekazanym jako parametr
     * @param ID numer ID klienta
     * @return
     */
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
            //stworzenie instancji klasy BankUser z danymi zalogowanego klienta
            bankUser = new BankUser(ID_BankUser, firstname, lastname, dob, city, street, phoneNumber, email, password, bAcN, blocked, balance, postalcode);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Getting BankUser from database");
        return bankUser;

    }

    /**
     * metoda sprawdza czy w bazie danych istnieje konto klienta o wporwadzonych danych logowania
     * @param Email
     * @param Password
     * @param BAcN
     * @return
     */
    public static BankUser getBankUser(String Email, String Password, String BAcN) {
        //zapytanie kierowane do bazy danych
        ResultSet result = executeQuery("SELECT * FROM BankUser WHERE BankUser.Email='" + Email + "'");
        boolean validator = true;
        BankUser bankUser = null;
        try {
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Niepoprawny adres email");
            }
            result = executeQuery("SELECT * FROM BankUser WHERE BankUser.Password='" + Password + "'");
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Niepoprawne haslo");
            }
            result = executeQuery("SELECT * FROM BankUser WHERE BankUser.BAcN='" + BAcN + "'");
            if (!result.isBeforeFirst()) {
                validator = false;
                Message.showMessage(Alert.AlertType.ERROR, "Log In", "Niewlasciwy numer konta");
            }
            //jezeli dane logowania sa poprawne (validator==true) metoda zwraca ID klienta
            if (validator) {
                bankUser = getBankUser(result.getInt("ID_BankUser"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bankUser;

    }

    /**
     * metoda wyswietla zawartosc tabeli z danymi dostepnymi dla pracownika banku
     * @return
     */
    public static List<BankUser> getBankUserList() {

        ResultSet result = executeQuery("SELECT * FROM BankUser");

        List<BankUser> bankUsers = new ArrayList<BankUser>();

        try {
            //wypisywanie w okienku zawartosci tabeli bazy danych z danymi kolejnych klientow banku
            //tak dlugo dopoki istnieje kolejny klient
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

    /**
     * metoda blokuje konto klienta o przekazanym w parametrze numerze ID
     * @param ID
     */
    public static void blockUser(int ID) {

        String statment = "UPDATE BankUser SET " +
                "Blocked='1' " +
                "WHERE BankUser.ID_BankUser=" + ID;

        executeStatement(statment);
        System.out.println("Blocking BankUser in database");


    }

    /**
     * metoda odblokowuje zablokowane konto klienta o przekazanym w parametrze numerze ID
     * @param ID
     */
    public static void unblockUser(int ID) {

        String statment = "UPDATE BankUser SET " +
                "Blocked='0' " +
                "WHERE BankUser.ID_BankUser=" + ID;

        executeStatement(statment);
        System.out.println("Unblocking BankUser in database");


    }

    /**
     * metoda aktualizuje dane klienta o wprowadzonym numerze ID
     * @param ID
     * @param newBankUser
     */
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

    /**
     * metoda usuwa klienta o wskazanym numerze ID z tabeli BankUser
     * @param ID
     */
    public static void deleteUser(int ID)
    {
        executeStatement("DELETE FROM BankUser WHERE BankUser.ID_BankUser="+ID);
    }

    /**
     * metoda zwraca numer ID klienta o przekazanym w parametrze numerze konta bankowego
     * @param BAcN
     * @return
     */
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

    /**
     * metoda zwraca wartosc oszczednosci jakie posiada klient o podanym numerze ID na koncie oszczednosciowym
     * @param ID
     * @return
     */
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


    /**
     * metoda przenosi srodki z konta klienta na konto oszczednosciowe
     * @param ID
     * @param investment
     */
    public static void insertInvestment(int ID, double investment) {
        executeStatement("INSERT INTO Savings (Investment, StartDate, EarnedSavings, ID_BankUser)" +
                "VALUES ('" + investment + "', '" + Bank.getStringCurrentDateTime() + "','" + 0.0 + "', '" + ID + "')");
        System.out.println("Inserting new Savings row to database");

    }

    public static void initializeBank() {
        //usuwanie kaskadowe konta bankowego z wszystkich tabel
        executeStatement("PRAGMA foreign_keys = ON");
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

    /**
     * metoda zapusje aktualny czas
     */
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

    /**
     * metoda pobiera informacje o wzietmy kredycie dla wskazanego konta bankowego
     * @param ID
     * @param BAcN
     * @return
     */
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

    /**
     * metoda dodaje kredyt dla konta bankowego o przekazanym numerze
     * @param ID
     * @param BAcN
     */
    public static void insertCredits(int ID, String BAcN) {
        executeStatement("INSERT INTO Credits (CreditBalance, CreditLimit, StartDate, ID_BankUser,BAcN,Overdraft)" +
                "VALUES ('" + 0.0 + "', '" + 5000.0 + "','" + Bank.getShortStringCurrentDateTime() + "', '" + ID + "','" + BAcN + "','" + 0.0 + "')");
        System.out.println("Inserting new Credits row to database");

    }

    /**
     * metoda pobiera stan konta dla klienta o przekazanym numerze ID
     * @param ID
     * @return
     */
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

    /**
     * metoda pobiera stan konta oszczednosciowego dla klienta o przekazanym numerze ID
     * @param ID
     * @return
     */
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

    /**
     * metoda pobiera sume zaoszczedzonych pieniedzy przez klienta o przekazanym numerze ID od otwarcia konta
     * @param ID
     * @return
     */
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


    /**
     * metoda oblicza roznice miedzy aktualna wartoscia posiadanego kredytu i maksymalnym mozliwym posiadanym kredytem
     * @param ID
     * @return
     */
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

    /**
     * metoda pobiera aktualna sume pobranego kredytu przez klienta o przekazanym numerze ID
     * @param ID
     * @return
     */
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

    /**
     * metoda zwraca maksymalna wartosc kredytu jaki moze wziac klient o przekazanym numerze ID
     * @param ID
     * @return
     */
    public static double getCreditLimit(int ID) {
        ResultSet resultSet = executeQuery("SELECT * FROM Credits WHERE Credits.ID_BankUser=" + ID);
        double result = 0;
        try {
            result = resultSet.getDouble("CreditLimit");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return result;
    }

    /**
     * metoda zwraca wartosc naliczonych odsetek dla konta klienta o przekazanym numerze ID
     * @param ID
     * @return
     */
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
    //metody aktualizuja stan konta klienta o przekazanym numerze ID
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


    /**
     * metoda dodaje wykonana akcje przez klienta do historii operacji dla jego konta bankowego
     * @param ID
     * @param Description
     * @param Type
     * @param amount
     */
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

    /**
     * metoda wyswietla w okienku zalogowanego klienta historie dzialan wykonanych na jego koncie
     * @param ID
     * @return
     */
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
