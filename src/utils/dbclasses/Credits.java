package utils.dbclasses;

public class Credits {

    private int ID_Credit;
    private double CreditBalance;
    private double CreditLimit;
    private double FinanceCharge;
    private int ID_BankUSer;
    private String BAcN;
    private double Overdraft;


    public Credits(int ID_Credit,double creditBalance, double creditLimit, double financeCharge, int ID_BankUSer, String BAcN,double overdraft) {
        setID_Credit(ID_Credit);
        setCreditBalance(creditBalance);
        setCreditLimit(creditLimit);
        setFinanceCharge(financeCharge);
        setID_BankUSer(ID_BankUSer);
        setBAcN(BAcN);
        setOverdraft(overdraft);
    }



    public int getID_Credit() {
        return ID_Credit;
    }

    public double getCreditDiff()
    {
        return CreditLimit-CreditBalance;
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

    public double getFinanceCharge() {
        return FinanceCharge;
    }

    public void setFinanceCharge(double financeCharge) {
        FinanceCharge = financeCharge;
    }

    public int getID_BankUSer() {
        return ID_BankUSer;
    }

    public void setID_BankUSer(int ID_BankUSer) {
        this.ID_BankUSer = ID_BankUSer;
    }

    public String getBAcN() {
        return BAcN;
    }

    public void setBAcN(String BAcN) {
        this.BAcN = BAcN;
    }


    public double getOverdraft() {
        return Overdraft;
    }

    public void setOverdraft(double overdraft) {
        Overdraft = overdraft;
    }

}
