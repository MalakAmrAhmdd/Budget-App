package Budgeting_Functionalities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a debt with a creditor, total amount, remaining amount, and due date.
 */
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

    /**
     * Default constructor required for Jackson deserialization.
     */
    public Debt() {
    }

    /**
     * Constructs a Debt with the specified parameters.
     * @param debtID The unique identifier for the debt.
     * @param creditor The name of the creditor.
     * @param totalAmount The total amount owed.
     * @param remainingAmount The remaining amount to be paid.
     * @param dueDate The due date for the debt.
     */
    public Debt(int debtID, String creditor, float totalAmount, float remainingAmount, LocalDate dueDate) {
        this.debtID = debtID;
        this.creditor = creditor;
        this.totalAmount = totalAmount;
        this.remainingAmount = remainingAmount;
        this.dueDate = dueDate;
    }

    /**
     * Gets the debt ID.
     * @return The debt ID.
     */
    public int getDebtID() {
        return debtID;
    }

    /**
     * Gets the creditor's name.
     * @return The creditor's name.
     */
    public String getCreditor() {
        return creditor;
    }

    /**
     * Gets the total amount owed.
     * @return The total amount owed.
     */
    public float getAmountOwed() {
        return totalAmount;
    }

    /**
     * Gets the remaining amount to be paid.
     * @return The remaining amount.
     */
    public float getRemainingAmount() {
        return remainingAmount;
    }

    /**
     * Gets the due date for the debt.
     * @return The due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

}
