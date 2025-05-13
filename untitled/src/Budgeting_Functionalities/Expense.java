package Budgeting_Functionalities;

public class Expense {
    private int expenseID;
    private String category;
    private float amount;
    private float limit;


    // Default constructor required for Jackson deserialization
    public Expense() {
    }
    public Expense(int expenseID, String category, float amount, float limit) {
        this.expenseID = expenseID;
        this.category = category;
        this.amount = amount;
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
