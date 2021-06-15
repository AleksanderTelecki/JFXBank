package utils;

import javafx.scene.control.Alert;
import utils.dbclasses.Bank;

import java.util.List;

/**
 *klasa zawiera metody umoziliwajace przenoszenie srodkow miedzy kontami
 */
public class CheckAndSend {
    //typy kont na ktore mozliwe jest przelewanie srodkow
    public enum operation {None, Balance, Savings, Investment, Credit, CreditLimit, CreditBalance, Overdraft, Transfer, Admin}
    //typy przelewow miedzy kontami
    public enum type {None, Internal, External, Another}

    /**
     * metoda przenosi srodki z jednego konta na drugie przez pracownika banku
     * @param stringFrom
     * @param stringTo
     * @param amount
     * @param oType
     * @param ID
     */
    public static void Send(String stringFrom, String stringTo, double amount, type oType, int ID) {
        operation From = getEnum(stringFrom);
        operation To = getEnum(stringTo);
        refDouble refAmount = new refDouble(amount);
        if (check(From, To, refAmount, ID)) {
            proceedOperation(From, To, refAmount.getValue(), oType, ID, null);
        }
    }

    /**
     * metoda dokonuje przenesienia srodkow miedzy kontami roznych klientow
     * @param stringFrom
     * @param stringTo
     * @param amount
     * @param oType
     * @param ID
     * @param BAcN
     */
    public static void Send(String stringFrom, String stringTo, double amount, type oType, int ID, String BAcN) {
        operation From = getEnum(stringFrom);
        operation To = getEnum(stringTo);
        refDouble refAmount = new refDouble(amount);
        if (check(From, To, refAmount, ID)) {

            proceedOperation(From, To, refAmount.getValue(), oType, ID, BAcN);
        }
    }

    /**
     * metoda aktualizuje stan bazy danych na wartosci po wykonaniu operacji
     * @param from
     * @param to
     * @param amount
     * @param oType
     * @param id
     * @param BAcN
     */
    private static void proceedOperation(operation from, operation to, double amount, type oType, int id, String BAcN) {
        String message = "Successful!";
        double newFrom = 0.0;
        double newTo = 0.0;
        switch (from) {

            case Balance -> {
                newFrom = DBcontroller.getBalance(id) - amount;
                DBcontroller.updateBalance(id, newFrom);
            }
            case Savings -> {
                newFrom = DBcontroller.getEarnedSavings(id) - amount;
                DBcontroller.updateEarnedSavings(id, newFrom);
            }
            case Investment -> {
                newFrom = DBcontroller.getInvestment(id) - amount;
                DBcontroller.updateInvestment(id, newFrom);
            }
            case Credit -> {
                newFrom = DBcontroller.getCreditBalance(id) + amount;
                DBcontroller.updateCreditBalance(id, newFrom);
            }

            case Admin -> {

            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                return;
            }
        }

        switch (to) {
            case Balance -> {
                if (from.equals(operation.Admin)) {
                    DBcontroller.updateBalance(id, amount);
                }else {
                    newTo = DBcontroller.getBalance(id) + amount;
                    DBcontroller.updateBalance(id, newTo);
                }
            }
            case Savings -> {
                if (from.equals(operation.Admin)) {
                    DBcontroller.updateEarnedSavings(id, amount);
                }else {
                    newTo = DBcontroller.getEarnedSavings(id) + amount;
                    DBcontroller.updateEarnedSavings(id, newTo);
                }
            }
            case Investment -> {

                if (from.equals(operation.Admin)) {
                    DBcontroller.updateInvestment(id, amount);

                }else {
                    newTo = DBcontroller.getInvestment(id) + amount;
                    DBcontroller.updateInvestment(id, newTo);
                }
            }
            case Credit -> {
                newTo = DBcontroller.getCreditBalance(id) - amount;
                DBcontroller.updateCreditBalance(id, newTo);
            }
            case Overdraft -> {
                if (from.equals(operation.Admin)) {
                    DBcontroller.updateOverdraft(id, amount);

                } else {
                    newTo = DBcontroller.getOverdraft(id) - amount;
                    DBcontroller.updateOverdraft(id, newTo);
                }
            }

            case CreditBalance -> {
                DBcontroller.updateCreditBalance(id, amount);
            }

            case CreditLimit -> {
                DBcontroller.updateCreditLimit(id, amount);
            }

            case Transfer -> {
                int transferID = DBcontroller.getID(BAcN);
                newTo = DBcontroller.getBalance(transferID) + amount;
                DBcontroller.insertOperation(transferID, to.toString() + " => Balance", oType.toString(), amount);
                DBcontroller.updateBalance(transferID, newTo);
            }
            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                return;
            }

        }

        String description = from.toString() + " => " + to.toString();
        String type = oType.toString();

        message = "Successful!\n" + amount + " has been withdrawn from the " + from.toString() + " to " + to.toString();
        DBcontroller.insertOperation(id, description, type, amount);
        Message.showMessage(Alert.AlertType.INFORMATION, "Operation", message);


    }

    /**
     * metoda sprawdza czy wybrana operacja jest mozliwa do wykonania
     * @param from
     * @param to
     * @param refAmount
     * @param id
     * @return
     */
    private static boolean check(operation from, operation to, refDouble refAmount, int id) {
        //przenoszona miedzy kontami suma
        double amount = refAmount.getValue();
        boolean validator = true;
        switch (from) {
        //sprawdzenie czy przenoszone srodki nie sa wieksze niz srodki znajdujace sie na koncie z ktorego beda przenoszone
            case Balance -> {
                double balance = DBcontroller.getBalance(id) - amount;

                if (balance < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Niewystarczajace srodki na wskazanym koncie by wykonac operacje");
                    validator = false;
                }

            }
            case Savings -> {
                double savings = DBcontroller.getEarnedSavings(id) - amount;

                if (savings < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Niewystarczajace srodki na wskazanym koncie by wykonac operacje");
                    validator = false;
                }
            }
            case Investment -> {
                double investment = DBcontroller.getInvestment(id) - amount;

                if (investment < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Niewystarczajace srodki na wskazanym koncie by wykonac operacje");
                    validator = false;
                }
            }
            case Credit -> {
                double creditdiff = DBcontroller.getCreditDiff(id) - amount;
                if (creditdiff < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Maksymalna dopuszczlna kwota kredytu to " + DBcontroller.getCreditLimit(id) + "!");
                    validator = false;
                }
            }

            case Admin -> {
                if (amount < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Kwota nie moze byc mniejsza od 0");
                    validator = false;
                }
            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                validator = false;
            }
        }
        //sprawdzenie czy przenoszone srodki sa wieksze 0
        switch (to) {
            case Balance, Investment, Savings, Transfer -> {
                if (amount<0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Kwota nie moze byc mniejsza od 0");
                    validator = false;
                }

            }//pobrany kredyt dla konta nie moze wyniesc wiecej niz 5000
            case Credit -> {
                double creditbalance = DBcontroller.getCreditBalance(id);

                amount = (creditbalance - amount) <= 0 ? (amount - (amount - creditbalance)) : amount;
                refAmount.setValue(amount);
                if (amount == 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Dopuszczlna kwota kredytu to 5000");
                    validator = false;
                }

            }
            case Overdraft -> {
                if (!from.equals(operation.Admin)) {
                    double overdraft = DBcontroller.getOverdraft(id);
                    amount = (overdraft - amount) < 0 ? (amount - (amount - overdraft)) : amount;
                    refAmount.setValue(amount);
                } else {
                    if (amount < 0) {
                        Message.showMessage(Alert.AlertType.ERROR, "Error", "Odsetki nie moga byc mniejsze niz 0");
                        validator = false;
                    }
                }

            }

            case CreditLimit -> {
                if (amount < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Kredyt nie moze wynosic mniej niz 0");
                    validator = false;
                }
            }

            case CreditBalance -> {
                double creditlimit = DBcontroller.getCreditLimit(id);
                if (amount < 0 || amount > creditlimit) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Dopuszczalna wartosc kredytu wynosi " + creditlimit + "\n Kredyt nie moze wynosic mniej niz 0");
                    validator = false;
                }
            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                validator = false;
            }

        }

        return validator;
    }

    /**
     * w metodzie wybierana jest konto na ktorym i dla ktorego wykonywane beda operacje
     * String z ComboBoxu jest konwenterowany na String
     * @param combostring
     * @return
     */
    public static operation getEnum(String combostring) {
        switch (combostring) {
            case "Balance" -> {
                return operation.Balance;
            }

            case "Savings" -> {
                return operation.Savings;
            }

            case "Credit" -> {
                return operation.Credit;
            }

            case "CreditLimit" -> {
                return operation.CreditLimit;
            }

            case "CreditBalance" -> {
                return operation.CreditBalance;
            }

            case "Overdraft" -> {
                return operation.Overdraft;
            }

            case "Investment" -> {
                return operation.Investment;
            }

            case "Transfer" -> {
                return operation.Transfer;
            }

            case "Admin" -> {
                return operation.Admin;
            }

            default -> {
                return operation.None;
            }


        }


    }

    /**
     *klasa wewnetrzna by moc przekazywac kwote w parametrze jako referencja
     */
    public static class refDouble {

        private double value;

        //konstruktor klasy
        public refDouble(double value) {
            setValue(value);
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

    }

}
