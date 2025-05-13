package Budgeting_Functionalities;

import User_Management.User;

import java.time.LocalDate;
import java.util.List;

public class ExpenseManager {
    private User user;

    public ExpenseManager() {}

    public ExpenseManager(User user) {
        this.user = user;
    }

    public boolean addExpense(String category, float amount, LocalDate date) {
        // Validate input
        if (amount <= 0) {
            System.out.println("Invalid expense amount. Please enter a positive value.");
            return false;
        }

        List<Expense> expenses = user.getExpenses();
        int nextID = expenses.size() + 1;
        Expense expense = new Expense(nextID, category, amount, date, 0);
        expenses.add(expense);

        List<Budget> budgets = user.getBudgets();
        boolean budgetUpdated = false;
        for (Budget budget : budgets) {
            if (budget.getCategory().equalsIgnoreCase(category)) {
                budget.setAmount(budget.getAmount() - amount);
                budgetUpdated = true;
                break;
            }
        }

        if (!budgetUpdated) {
            System.out.println("No matching budget found for the category. Expense added without budget adjustment.");
        }

        user.updateUserInFile();

        System.out.println("Expense added successfully. Your transaction history has been updated.");
        return true;
    }
}
