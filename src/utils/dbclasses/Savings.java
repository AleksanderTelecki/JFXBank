package utils.dbclasses;

import javafx.scene.control.Alert;
import utils.DBcontroller;
import utils.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * klasa definiujaca metody dal konta oszczednosciowego klienta
 */
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

    public String getStringStartDate() {
        return dateFormat.format(StartDate);
    }

    public void setStartDate(String startDate) {
        try {
            StartDate = !startDate.equals("null") ? dateFormat.parse(startDate) : null;


        } catch (ParseException e) {
            Message.showMessage(Alert.AlertType.ERROR, "Invalid Data", "invalid Date!\nDate Format: dd.MM.yyy");

        }
    }

    /**
     * metoda oblicza wartosc zaoszczedzonych srodkow
     */
    private void calculateEarnedSavings() {

        long timediff = StartDate != null ? TimeUnit.DAYS.convert((Bank.getCurrentDateTime().getTime() - getStartDate().getTime()), TimeUnit.MILLISECONDS) : 0;
        if (Investment != 0 && (StartDate == null)) {
            //ustawienie poczatkowej daty rozpoczecia
            DBcontroller.updateEarnedSavingsDate(ID_BankUser, Bank.getShortStringCurrentDateTime(), 0.0);
        } else if (Investment != 0 && timediff != 0) {
            //obliczanie wartosci oszczedznosci w zaleznosci od czasu jaki uplynal oraz oprocentowania konta
            setEarnedSavings(EarnedSavings + timediff * Bank.getSavingsPercentage() * getInvestment());
            DBcontroller.updateEarnedSavingsDate(ID_BankUser, Bank.getShortStringCurrentDateTime(), EarnedSavings);
        } else if ((Investment == 0) && (StartDate != null)) {//Setting Date value to null in Savings column StartDate
            DBcontroller.updateEarnedSavingsDate(ID_BankUser, null, EarnedSavings);
        }

    }

    public double getEarnedSavings() {
        calculateEarnedSavings();
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
