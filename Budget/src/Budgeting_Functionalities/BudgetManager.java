package Budgeting_Functionalities;

import User_Management.User;

import java.util.List;
/**
 * Manages budget-related operations for a user.
 */
public class BudgetManager {
    private User user;
    /**
     * Default constructor.
     */
    public BudgetManager() {}
    /**
     * Constructs a BudgetManager for a specific user.
     *
     * @param user The user associated with this BudgetManager.
     */
    public BudgetManager(User user) {
        this.user = user;
    }
    /**
     * Adds a new budget category for the user.
     *
     * @param category  The category of the budget.
     * @param amount    The allocated amount for the budget.
     * @param startDate The start date of the budget.
     * @param endDate   The end date of the budget.
     * @return True if the budget was added successfully, false otherwise.
     */
    public boolean addBudget(String category, float amount, String startDate, String endDate) {
        double totalIncome = user.getTotalIncome();

        if (amount > totalIncome) {
            System.out.println("Insufficient income to add this budget.");
            return false;
        }

        List<Budget> budgets = user.getBudgets();
        int nextID = budgets.size() + 1;
        Budget budget = new Budget(nextID, category, amount, startDate, endDate);
        budgets.add(budget);

        user.updateUserInFile();

        System.out.println("Budget added successfully. Total income updated.");
        return true;
    }

}
