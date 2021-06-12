package utils;

import javafx.scene.control.Alert;
import utils.dbclasses.Bank;

import java.util.List;

public class CheckAndSend {

    public enum operation {None, Balance, Savings, Investment, Credit, CreditLimit, CreditBalance, Overdraft, Transfer, Admin}

    public enum type {None, Internal, External, Another}


    public static void Send(operation From, operation To, double amount, type oType, int ID) {

        refDouble refAmount = new refDouble(amount);
        if (check(From, To, refAmount, ID)) {
            proceedOperation(From, To, refAmount.getValue(), oType, ID, null);
        }
    }

    public static void Send(String stringFrom, String stringTo, double amount, type oType, int ID) {

        operation From = getEnum(stringFrom);
        operation To = getEnum(stringTo);
        refDouble refAmount = new refDouble(amount);
        if (check(From, To, refAmount, ID)) {

            proceedOperation(From, To, refAmount.getValue(), oType, ID, null);
        }
    }

    public static void Send(String stringFrom, String stringTo, double amount, type oType, int ID, String BAcN) {

        operation From = getEnum(stringFrom);
        operation To = getEnum(stringTo);
        refDouble refAmount = new refDouble(amount);
        if (check(From, To, refAmount, ID)) {

            proceedOperation(From, To, refAmount.getValue(), oType, ID, BAcN);
        }
    }

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

            case Admin ->{

            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                return;
            }
        }

        switch (to) {

            case Balance -> {
                newTo = DBcontroller.getBalance(id) + amount;
                DBcontroller.updateBalance(id, newTo);
            }
            case Savings -> {
                newTo = DBcontroller.getEarnedSavings(id) + amount;
                DBcontroller.updateEarnedSavings(id, newTo);
            }
            case Investment -> {
                newTo = DBcontroller.getInvestment(id) + amount;
                DBcontroller.updateInvestment(id, newTo);
            }
            case Credit -> {
                newTo = DBcontroller.getCreditBalance(id) - amount;
                DBcontroller.updateCreditBalance(id, newTo);
            }
            case Overdraft -> {
                if(!from.equals(operation.Admin))
                {
                    newTo = DBcontroller.getOverdraft(id) - amount;
                    DBcontroller.updateOverdraft(id, newTo);
                }else {
                    DBcontroller.updateOverdraft(id, amount);
                }

            }

            case CreditBalance ->{
                DBcontroller.updateCreditBalance(id, amount);
            }

            case CreditLimit ->{
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

        // TODO: 08.06.2021 Maybe change description property
        String description = from.toString() + " => " + to.toString();
        String type = oType.toString();

        message = "Successful!\n" + amount + " has been withdrawn from the " + from.toString() + " to " + to.toString();
        DBcontroller.insertOperation(id, description, type, amount);
        Message.showMessage(Alert.AlertType.INFORMATION, "Operation", message);


    }

    private static boolean check(operation from, operation to, refDouble refAmount, int id) {

        double amount = refAmount.getValue();
        boolean validator = true;
        switch (from) {

            case Balance -> {
                double balance = DBcontroller.getBalance(id) - amount;

                if (balance < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Balance) to perform operation");
                    validator = false;
                }

            }
            case Savings -> {
                double savings = DBcontroller.getEarnedSavings(id) - amount;

                if (savings < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Savings) to perform operation");
                    validator = false;
                }
            }
            case Investment -> {
                double investment = DBcontroller.getInvestment(id) - amount;

                if (investment < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Investment) to perform operation");
                    validator = false;
                }
            }
            case Credit -> {
                double creditdiff = DBcontroller.getCreditDiff(id) - amount;
                if (creditdiff < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Credit Limit is " + DBcontroller.getCreditLimit(id) + "!");
                    validator = false;
                }
            }


            case Admin ->{
                if (amount < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Value can't be less then 0!");
                    validator = false;
                }
            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                validator = false;
            }
        }

        switch (to) {

            case Balance, Investment, Savings, Transfer -> {

            }
            case Credit -> {

                double creditbalance = DBcontroller.getCreditBalance(id);
                amount = (creditbalance - amount) <= 0 ? (amount - (amount - creditbalance)) : amount;
                refAmount.setValue(amount);
                if (amount == 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Credit Limit is 5000");
                    validator = false;
                }

            }

            case Overdraft -> {
                if(!from.equals(operation.Admin))
                {
                    double overdraft = DBcontroller.getOverdraft(id);
                    amount = (overdraft - amount) < 0 ? (amount - (amount - overdraft)) : amount;
                    refAmount.setValue(amount);
                }else {

                    if(amount<0){
                        Message.showMessage(Alert.AlertType.ERROR, "Error", "Overdraft can't be less then 0!");
                        validator = false;
                    }
                }

            }

            case CreditLimit -> {
                if (amount < 0) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Credit Limit can't be less then 0!");
                    validator = false;
                }
            }

            case CreditBalance -> {
                double creditlimit = DBcontroller.getCreditLimit(id);
                if (amount < 0 || amount > creditlimit) {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Credit Limit is " + creditlimit + "\n CreditBalance can't be less then 0!");
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

    public static class refDouble {

        private double value;

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
