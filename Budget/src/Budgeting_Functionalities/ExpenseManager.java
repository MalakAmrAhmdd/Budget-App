package Budgeting_Functionalities;

import Budgeting_Functionalities.Budget;
import User_Management.Authentication;
import User_Management.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages expense-related operations for a user.
 */
public class ExpenseManager {
    private User user;

    /**
     * Default constructor.
     */
    public ExpenseManager() {}
    /**
     * Constructs an ExpenseManager for a specific user.
     *
     * @param user The user associated with this ExpenseManager.
     */
    public ExpenseManager(User user) {
        this.user = user;
    }
    /**
     * Adds a new expense for the user.
     *
     * @param amount The amount of the expense.
     * @param date   The date of the expense.
     * @return True if the expense was added successfully, false otherwise.
     */
    public boolean addExpense(float amount, LocalDate date) {
        // Display available categories
        List<Budget> budgets = user.getBudgets();
        if (budgets.isEmpty()) {
            System.out.println("No categories available. Please add a budget first.");
            return false;
        }

        System.out.println("Available Categories:");
        for (int i = 0; i < budgets.size(); i++) {
            System.out.println((i + 1) + ". " + budgets.get(i).getCategory());
        }

        // Prompt user to choose a category
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number corresponding to the category for the expense:");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return false;
        }

        if (choice < 1 || choice > budgets.size()) {
            System.out.println("Invalid choice. Please select a valid category number.");
            return false;
        }

        String category = budgets.get(choice - 1).getCategory();

        // Validate input
        if (amount <= 0) {
            System.out.println("Invalid expense amount. Please enter a positive value.");
            return false;
        }

        // Add the expense
        List<Expense> expenses = user.getExpenses();
        if (expenses == null){
            expenses = new ArrayList<>();
            user.setExpenses(expenses);
            Authentication.getCurrentUser().setExpenses(expenses);
        }
        int nextID = expenses.size() + 1;
        Expense expense = new Expense(nextID, category, amount, date, 0);
        expenses.add(expense);

        // Update the budget
        for (Budget budget : budgets) {
            if (budget.getCategory().equalsIgnoreCase(category)) {
                budget.setAmount(budget.getAmount() - amount);
                break;
            }
        }

        user.updateUserInFile();

        System.out.println("Expense added successfully. Your transaction history has been updated.");
        return true;
    }
}