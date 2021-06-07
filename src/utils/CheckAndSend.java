package utils;

import javafx.scene.control.Alert;

public class CheckAndSend {

    public enum operationType {None, Balance, Savings, Investment, Credit, Overdraft}

    public static void Send(operationType From, operationType To, double amount, int senderID) {

        double from;
        String fromString = null;
        String toString = null;



        if((To==operationType.Credit))
        {
            double creditbalance = DBcontroller.getCreditBalance(senderID);
            amount = (creditbalance-amount)<=0?(amount-(amount-creditbalance)):amount;
        }

        switch (From) {

            case Balance -> {
                from = DBcontroller.getBalance(senderID) - amount;

                if (from >= 0) {
                    DBcontroller.updateBalance(senderID, from);
                    fromString = From.toString();
                } else {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Balance) to perform operation");
                    return;
                }

            }
            case Savings -> {
                from = DBcontroller.getEarnedSavings(senderID) - amount;

                if (from >= 0) {
                    DBcontroller.updateEarnedSavings(senderID, from);
                    fromString = From.toString();
                } else {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Savings) to perform operation");
                    return;
                }
            }
            case Investment -> {
                from = DBcontroller.getInvestment(senderID) - amount;

                if (from >= 0) {
                    DBcontroller.updateInvestment(senderID, from);
                    fromString = From.toString();
                } else {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (Investment) to perform operation");
                    return;
                }
            }
            case Credit -> {
                // TODO: 07.06.2021 Maybe set access to get credit minus for -100000
                from = DBcontroller.getCreditDiff(senderID) - amount;
                if (from >= 0) {
                    DBcontroller.updateCreditBalance(senderID, DBcontroller.getCreditBalance(senderID) + amount);
                    fromString = From.toString();
                } else {
                    Message.showMessage(Alert.AlertType.ERROR, "Error", "Not enough funds in (CreditBalance) to perform operation");
                    return;
                }
            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                return;
            }
        }

        switch (To) {

            case Balance -> {
                DBcontroller.updateBalance(senderID, DBcontroller.getBalance(senderID) + amount);
                toString = To.toString();
            }
            case Savings -> {
                DBcontroller.updateEarnedSavings(senderID, DBcontroller.getEarnedSavings(senderID) + amount);
                toString = To.toString();
            }
            case Investment -> {
                DBcontroller.updateInvestment(senderID, DBcontroller.getInvestment(senderID) + amount);
                toString = To.toString();
            }
            case Credit -> {
                DBcontroller.updateCreditBalance(senderID, DBcontroller.getCreditBalance(senderID) - amount);
                toString = To.toString();
            }
            case Overdraft -> {
                DBcontroller.updateOverdraft(senderID, DBcontroller.getOverdraft(senderID) + amount);
                toString = To.toString();
            }

            default -> {
                Message.showMessage(Alert.AlertType.ERROR, "Error", "Null Data");
                return;
            }

        }

        String description = fromString + " => " + toString;
        String type = "Internal transfer";

        DBcontroller.insertOperation(senderID, description, type, amount);


    }


}
