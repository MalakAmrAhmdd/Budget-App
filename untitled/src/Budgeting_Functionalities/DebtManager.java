package Budgeting_Functionalities;

import User_Management.User;

import java.time.LocalDate;

public class DebtManager {
    private User user;

    public DebtManager(User user) {
        this.user = user;
    }

    /**
     * Adds a new debt for the user.
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
