package Budgeting_Functionalities;

public class Budget {
    private int budgetID;
    private String category;
    private float amount;
    private String startDate;
    private String endDate;

    // Default constructor required for Jackson deserialization
    public Budget() {}
    public Budget(int budgetID, String category, float amount, String startDate, String endDate) {
        this.budgetID = budgetID;
        this.category = category;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getBudgetID() {
        return budgetID;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public float getAmount() {
        return amount;
    }
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


}