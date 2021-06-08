package utils.dbclasses;

import utils.DBcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {

    private static Date CurrentDateTime;
    private static String Adress;
    private static String Name;
    private static String Phone;
    private static String Email;
    private static String PostalCode;
    private static String Website;
    private static int UserCount;

    private static double DepositPercentage;
    private static double SavingsPercentage;


    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public static final SimpleDateFormat DBdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void initializeBank(String currentdatetime,String adress,String name,String phone,String email,String postalcode,String website,int userCount,double depositPercentage,double savingsPercentage)  {

        try {
            CurrentDateTime = DBdateFormat.parse(currentdatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Adress=adress;
        Name=name;
        Phone=phone;
        Email=email;
        PostalCode=postalcode;
        Website=website;
        UserCount=userCount;
        DepositPercentage=depositPercentage;
        SavingsPercentage=savingsPercentage;


    }

    public static void setCurrentDateTime(Date currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public static void setCurrentDateTime(String currentDateTime) {
        try {
            CurrentDateTime = DBdateFormat.parse(currentDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void setUserCount(int userCount) {
        UserCount = userCount;
    }

    public static Date getCurrentDateTime() {
        return CurrentDateTime;
    }

    public static String getStringCurrentDateTime()
    {

        DBcontroller.updateDateTime();
        return dateFormat.format(CurrentDateTime);

    }

    public static String getAdress() {
        return Adress;
    }

    public static String getName() {
        return Name;
    }

    public static String getPhone() {
        return Phone;
    }

    public static String getEmail() {
        return Email;
    }

    public static String getPostalCode() {
        return PostalCode;
    }

    public static String getWebsite() {
        return Website;
    }

    public static int getUserCount() {
        return UserCount;
    }


    public static double getDepositPercentage() {
        return DepositPercentage;
    }

    public static double getSavingsPercentage() {
        return SavingsPercentage;
    }

}
