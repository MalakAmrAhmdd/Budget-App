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
