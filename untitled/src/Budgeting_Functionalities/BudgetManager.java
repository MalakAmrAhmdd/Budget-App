package Budgeting_Functionalities;

import User_Management.User;

import java.util.List;

public class BudgetManager {
    private User user;
    public BudgetManager() {}
    public BudgetManager(User user) {
        this.user = user;
    }
    public boolean addBudget(String category, float amount, String startDate, String endDate) {
        double totalIncome = user.getTotalIncome(); // Assuming `getTotalIncome` exists in `User`

        if (amount > totalIncome) {
            System.out.println("Insufficient income to add this budget.");
            return false;
        }
        // Add the budget to the user's budget list
        List<Budget> budgets = user.getBudgets(); // Assuming `getBudgets` exists in `User`
        int nextID = budgets.size() + 1;
        Budget budget = new Budget(nextID, category, amount, startDate, endDate);
        budgets.add(budget);

        // Update the user data in the JSON file
        user.updateUserInFile();

        System.out.println("Budget added successfully.");
        return true;
    }

}
