package Budgeting_Functionalities;

import java.time.LocalDate;

public class Expense {
    private int expenseID;
    private String category;
    private float amount;
    private float limit;
    private LocalDate date;


    // Default constructor required for Jackson deserialization
    public Expense() {
    }
    public Expense(int expenseID, String category, float amount, LocalDate date,float limit) {
        this.expenseID = expenseID;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.limit = limit;
    }
    public int getExpenseID() {
        return expenseID;
    }
    public String getCategory() {
        return category;
    }
    public float getAmount() {
        return amount;
    }
    public float getLimit() {
        return limit;
    }
}
