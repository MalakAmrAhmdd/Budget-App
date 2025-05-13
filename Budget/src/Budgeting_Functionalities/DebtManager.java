package Budgeting_Functionalities;

import User_Management.User;

import java.time.LocalDate;

/**
 * Manages debts for a user, allowing addition of new debts.
 */
public class DebtManager {
    private User user;

    /**
     * Constructs a DebtManager for the specified user.
     * @param user The user whose debts are managed.
     */
    public DebtManager(User user) {
        this.user = user;
    }

    /**
     * Adds a new debt for the user.
     * @param creditor The name of the creditor.
     * @param totalAmount The total amount owed.
     * @param remainingAmount The remaining amount to be paid.
     * @param dueDate The due date for the debt.
     * @return true if the debt was added successfully, false otherwise.
     */
    public boolean addDebt(String creditor, float totalAmount, float remainingAmount, LocalDate dueDate) {
        // Debt constructor: (int debtID, String category, float totalAmount, float remainingAmount, LocalDate dueDate)
        int newId = user.getDebts().size() + 1;
        Debt debt = new Debt(newId, creditor, totalAmount, remainingAmount, dueDate);
        user.getDebts().add(debt);
        user.updateUserInFile();
        return true;
    }

}
