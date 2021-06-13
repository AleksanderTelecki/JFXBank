package utils.dbclasses;

import javafx.scene.control.Alert;
import utils.DBcontroller;
import utils.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankUser {

    private int ID;
    private String FirstName;
    private String LastName;
    private String FullName;
    private Date DOB;
    private String City;
    private String Street;
    private String PhoneNumber;
    private String Email;
    private String Password;
    private String BAcN;
    private String Blocked;
    private double Balance;
    private String PostalCode;


    private Savings UserSavings;
    private Credits UserCredits;

    //Created only for table in AdminController
    private double Invested;
    private double CreditBalance;
    private double CreditLimit;
    private double Overdraft;
    private double Savings;
    //Created only for table in AdminController

    private boolean catcherror = false;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    //https://stackoverflow.com/questions/46326822/java-regex-first-name-validation
    /*
    1. ignore case: "(?i)"
    2. can only start with an a-z character: "(?i)[a-z]"
    3. can only end with an a-z: "(?i)[a-z](.*[a-z])?"
    4. is between 1 and 25 characters: "(?i)[a-z](.{0,24}[a-z])?"
    5. can contain a-z and [ '-,.]: "(?i)[a-z]([- ',.a-z]{0,24}[a-z])?"
    */

    String regexNamePattern = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";

    //https://www.baeldung.com/java-regex-validate-phone-numbers
    String regexNumberPatterns
            = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$"
            + "|^(\\d{3}[- .]?){2}\\d{3}$";

    //https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
    String regexEmailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    String regexPostalCodePattern = "\\d{2}-\\d{3}";

    public BankUser(String firstName, String lastName, String DOB, String city, String street, String phoneNumber, String email, String password, String postalCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setDOB(DOB);
        setCity(city);
        setStreet(street);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setPassword(password);
        setPostalCode(postalCode);
        if (catcherror == true)
            throw new IllegalArgumentException();
    }

    public BankUser(int ID, String firstName, String lastName, String DOB, String city, String street, String phoneNumber, String email, String password, String BAcN, String blocked, double balance, String postalCode) {
        setID(ID);
        setFirstName(firstName);
        setLastName(lastName);
        setDOB(DOB);
        setCity(city);
        setStreet(street);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setPassword(password);
        setBAcN(BAcN);
        setBlocked(blocked);
        setBalance(balance);
        setPostalCode(postalCode);
        if (catcherror == true)
            throw new IllegalArgumentException();
    }

    public int getID() {
        if (ID == 0) {
            setID(DBcontroller.getID(BAcN));
        }
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {

        if (firstName.matches(regexNamePattern)) {
            FirstName = firstName;
        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid FirstName!");
            catcherror = true;
        }

    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {

        if (lastName.matches(regexNamePattern)) {
            LastName = lastName;
        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid LastName!");
            catcherror = true;
        }

    }


    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }


    public Date getDOB() {
        return DOB;
    }

    public String getStringDOB() {
        return dateFormat.format(DOB);
    }

    public void setDOB(String DOB) {
        try {
            Date date = dateFormat.parse(DOB);

            if (date.compareTo(dateFormat.parse("01.01.1950")) > 0 || date.compareTo(dateFormat.parse("01.01.2004")) < 1) {
                this.DOB = date;
            } else {
                Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate must be between years 1950 and 2004");
                catcherror = true;
            }


        } catch (ParseException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate Format: dd.MM.yyy");
            catcherror = true;

        }
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {

        if (city.length() < 2) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid City Adress!");
            catcherror = true;

        } else {
            City = city;
        }

    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        if (street.length() < 2) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Street Adress!");
            catcherror = true;

        } else {

            Street = street;
        }

    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(regexNumberPatterns);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            PhoneNumber = phoneNumber;
        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid PhoneNumber!");
            catcherror = true;

        }

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile(regexEmailPattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            Email = email;
        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Email!");
            catcherror = true;
        }


    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Password!\nMin length 6 symbols");
            catcherror = true;
        } else {

            Password = password;
        }

    }

    public String getBAcN() {

        if (BAcN == null || BAcN.length() == 0) {
            BAcN = UUID.randomUUID().toString().substring(9, 23);
            return BAcN;
        } else {
            return BAcN;
        }


    }

    public void setBAcN(String BAcN) {
        this.BAcN = BAcN;
    }

    public String getBlocked() {
        return Blocked;
    }

    public void setBlocked(String blocked) {
        int result = Integer.parseInt(blocked);
        if (result > 1 || result < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            Blocked = blocked;
        }


    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        if (postalCode.matches(regexPostalCodePattern)) {
            PostalCode = postalCode;
        } else {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid PostalCode!\nPostal code format: NN-NNN");
            catcherror = true;
        }


    }


    public Savings getUserSavings() {

        if (UserSavings == null) {
            setUserSavings(DBcontroller.getSavings(getID()));
        }

        return UserSavings;
    }

    public void setUserSavings(Savings userSavings) {
        UserSavings = userSavings;
    }


    public Credits getUserCredits() {
        if (UserCredits == null) {
            setUserCredits(DBcontroller.getCredits(getID(), BAcN));
        }
        return UserCredits;
    }


    public double getInvested() {
        return getUserSavings().getInvestment();
    }

    public double getCreditBalance() {
        return getUserCredits().getCreditBalance();
    }

    public double getCreditLimit() {
        return getUserCredits().getCreditLimit();
    }

    public double getOverdraft() {
        return getUserCredits().getOverdraft();
    }

    public double getSavings() {
        return getUserSavings().getEarnedSavings();
    }



    public void setUserCredits(Credits userCredits) {
        UserCredits = userCredits;
    }


    public String toFullString() {
        return "BankUser{" +
                "ID=" + ID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", DOB=" + DOB +
                ", City='" + City + '\'' +
                ", Street='" + Street + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", BAcN='" + BAcN + '\'' +
                ", Blocked='" + Blocked + '\'' +
                ", Balance=" + Balance +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return getFullName();
    }


}
