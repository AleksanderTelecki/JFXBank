package utils.dbclasses;

import javafx.scene.control.Alert;
import utils.DBcontroller;
import utils.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Credits {

    private int ID_Credit;
    private double CreditBalance;
    private double CreditLimit;
    private Date StartDate;
    private int ID_BankUser;
    private String BAcN;
    private double Overdraft;

    public static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Credits(int ID_Credit, double creditBalance, double creditLimit, String startdate, int ID_BankUSer, String BAcN, double overdraft) {
        setID_Credit(ID_Credit);
        setCreditBalance(creditBalance);
        setCreditLimit(creditLimit);
        setStartDate(startdate);
        setID_BankUser(ID_BankUSer);
        setBAcN(BAcN);
        setOverdraft(overdraft);
    }


    public int getID_Credit() {
        return ID_Credit;
    }

    public double getCreditDiff() {
        return CreditLimit - CreditBalance;
    }

    public void setID_Credit(int ID_Credit) {
        this.ID_Credit = ID_Credit;
    }

    public double getCreditBalance() {
        return CreditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        CreditBalance = creditBalance;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        CreditLimit = creditLimit;
    }


    public Date getStartDate() {
        return StartDate;
    }

    public String getStringStartDate() {
        return shortDateFormat.format(getStringStartDate());
    }

    public void setStartDate(String startDate) {
        try {
            StartDate = !startDate.equals("null") ? shortDateFormat.parse(startDate) : null;


        } catch (ParseException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate Format: dd.MM.yyy");

        }
    }


    public int getID_BankUser() {
        return ID_BankUser;
    }

    public void setID_BankUser(int ID_BankUser) {
        this.ID_BankUser = ID_BankUser;
    }

    public String getBAcN() {
        return BAcN;
    }

    public void setBAcN(String BAcN) {
        this.BAcN = BAcN;
    }

    private void calculateOverdraft() {

        long timediff = StartDate != null ? TimeUnit.DAYS.convert((Bank.getCurrentDateTime().getTime() - getStartDate().getTime()), TimeUnit.MILLISECONDS) : 0;
        if (CreditBalance != 0 && (StartDate == null)) {//Setting Date value to Credit column StartDate
            DBcontroller.updateOverdraftWithDate(getID_BankUser(), Bank.getShortStringCurrentDateTime(), 0.0);
        } else if (CreditBalance != 0 && timediff != 0) {//Setting Overdraft counted by for each day
            setOverdraft(Overdraft+CreditBalance * Bank.getDepositPercentage() * timediff);
            DBcontroller.updateOverdraftWithDate(ID_BankUser,Bank.getShortStringCurrentDateTime() ,Overdraft);
        } else if ((CreditBalance == 0) && (StartDate != null)) {//Setting Date value to null in Credit column StartDate
            DBcontroller.updateOverdraftWithDate(getID_BankUser(), null, Overdraft);
        }

    }

    public double getOverdraft() {
        calculateOverdraft();
        return Overdraft;
    }

    public void setOverdraft(double overdraft) {
        Overdraft = overdraft;
    }

}
