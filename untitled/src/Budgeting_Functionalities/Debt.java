package Budgeting_Functionalities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Debt {
    @JsonProperty("debtID")
    private int debtID;
    @JsonProperty("creditor")
    private String creditor;
    @JsonProperty("totalAmount")
    private float totalAmount;
    @JsonProperty("remainingAmount")
    private float remainingAmount;
    @JsonProperty("dueDate")
    private LocalDate dueDate;

    // Default constructor required for Jackson deserialization
    public Debt() {
    }
    public Debt(int debtID, String creditor, float totalAmount, float remainingAmount, LocalDate dueDate) {
        this.debtID = debtID;
        this.creditor = creditor;
        this.totalAmount = totalAmount;
        this.remainingAmount = remainingAmount;
        this.dueDate = dueDate;
    }
    public int getDebtID() {
        return debtID;
    }
    public String getCreditor() {
        return creditor;
    }
    public float getAmountOwed() {
        return totalAmount;
    }
    public float getRemainingAmount() {
        return remainingAmount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

}
