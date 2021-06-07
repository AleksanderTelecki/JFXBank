package utils.dbclasses;

import javafx.scene.control.Alert;
import utils.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Savings {

    private int ID_Savings;
    private double Investment;
    private Date StartDate;
    private double EarnedSavings;
    private int ID_BankUser;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Savings(int ID_Savings, double investment, String startDate, double earnedSavings, int ID_BankUser) {
        setID_Savings(ID_Savings);
        setInvestment(investment);
        setStartDate(startDate);
        setEarnedSavings(earnedSavings);
        setID_BankUser(ID_BankUser);
    }

    public int getID_Savings() {
        return ID_Savings;
    }

    public void setID_Savings(int ID_Savings) {
        this.ID_Savings = ID_Savings;
    }

    public double getInvestment() {
        return Investment;
    }

    public void setInvestment(double investment) {
        Investment = investment;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public String getStringStartDate()
    {
        return dateFormat.format(StartDate);
    }

    public void setStartDate(String startDate) {
        try {
            Date date = dateFormat.parse(startDate);
            this.StartDate = date;


        } catch (ParseException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate Format: dd.MM.yyy");

        }
    }


    public double getEarnedSavings() {
        return EarnedSavings;
    }

    public void setEarnedSavings(double earnedSavings) {
        EarnedSavings = earnedSavings;
    }

    public int getID_BankUser() {
        return ID_BankUser;
    }

    public void setID_BankUser(int ID_BankUser) {
        this.ID_BankUser = ID_BankUser;
    }


}