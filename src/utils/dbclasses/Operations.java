package utils.dbclasses;

import javafx.scene.control.Alert;
import utils.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Operations implements Comparator<Operations> {

    private int ID_Operation;
    private Date Date;

    //korzystamy z niej w kontorlerze UserContorller by kolumna z tablicy z bazy danych
    //mogla pobrac wartosc Date jako String by mogla byc wyswietlona w kolumnie
    private String Sdate;

    private String Description;
    private String Type;
    private double Amount;
    private int ID_BankUser;
    private int Counter;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    /**
     * konstruktor bezargumentowy
     */
    public Operations() {

    }

    /**
     * konstruktor klasy
     * @param date
     * @param description
     * @param type
     * @param amount
     * @param ID_BankUser
     * @param counter
     */
    public Operations(String date, String description, String type, double amount, int ID_BankUser, int counter) {
        setDate(date);
        setDescription(description);
        setAmount(amount);
        setID_BankUser(ID_BankUser);
        setCounter(counter);
        setType(type);
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getID_Operation() {
        return ID_Operation;
    }

    public void setID_Operation(int ID_Operation) {
        this.ID_Operation = ID_Operation;
    }


    public java.util.Date getDate() {
        return this.Date;
    }

    public String getStringDate() {
        return dateFormat.format(this.Date);
    }

    public void setDate(String startDate) {
        try {
            Date date = dateFormat.parse(startDate);
            this.Date = date;
        } catch (ParseException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate Format: dd.MM.yyy");

        }
    }

    public String getSdate() {
        return getStringDate();
    }

    public void setSdate(String sdate) {
        setDate(sdate);
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public int getID_BankUser() {
        return ID_BankUser;
    }

    public void setID_BankUser(int ID_BankUser) {
        this.ID_BankUser = ID_BankUser;
    }

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }


    @Override
    public int compare(Operations o1, Operations o2) {

        return Integer.compare(o1.getCounter(), o2.getCounter());
    }


}
