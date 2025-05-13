package Budgeting_Functionalities;

import java.time.LocalDate;

public class Debt {
    private int debtID;
    private String category;
    private float totalAmount;
    private float remainingAmount;
    private LocalDate dueDate;

    // Default constructor required for Jackson deserialization
    public Debt() {
    }
    public Debt(int debtID, String category, float totalAmount, float remainingAmount, LocalDate dueDate) {
        this.debtID = debtID;
        this.category = category;
        this.totalAmount = totalAmount;
        this.remainingAmount = remainingAmount;
        this.dueDate = dueDate;
    }
    public int getDebtID() {
        return debtID;
    }
    public String getCategory() {
        return category;
    }
    public float getAmount() {
        return totalAmount;
    }
    public float getRemainingAmount() {
        return remainingAmount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

}
