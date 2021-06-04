package utils.dbclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {

    private static Date CurrentDate;
    private static String Adress;
    private static String Name;
    private static String Phone;
    private static String Email;
    private static String PostalCode;
    private static String Website;
    private static int UserCount;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void initializeBank(String currentdate,String adress,String name,String phone,String email,String postalcode,String website,int userCount) {

        try {
            CurrentDate = dateFormat.parse(currentdate);
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


    }

    public static void setCurrentDate(Date currentDate) {
        CurrentDate = currentDate;
    }

    public static void setUserCount(int userCount) {
        UserCount = userCount;
    }

    public static Date getCurrentDate() {
        return CurrentDate;
    }

    public static String getStringCurrentDate()
    {

        return dateFormat.format(CurrentDate);

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
}
