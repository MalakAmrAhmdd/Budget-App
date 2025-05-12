package Budgeting_Functionalities;

import java.util.Date;

public class Transaction {
    public enum Type {
        INCOME, EXPENSE
    }

    private Type type;
    private double amount;
    private Date date;
    private boolean isRecurring;

    public Transaction(Type type, double amount, Date date, boolean isRecurring) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.isRecurring = isRecurring;
    }
}