// ...existing imports...

import Analytics.Insights_and_Financial_reports;
import Budgeting_Functionalities.*;
import User_Management.*;
import Notification.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.time.LocalDate;

/**
 * Entry point for the Budget App.
 * Provides a menu-driven interface for user interaction.
 */
public class Main {
    /**
     * Main method to run the Budget App.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Authentication auth = new Authentication();
        OTPManager otpManager = new OTPManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Budget App Menu ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // ...existing sign up code...
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    auth.signUp(name, email, username, password, phoneNumber);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (auth.login(loginUsername, loginPassword)) {
                        boolean loggedIn = true;
                        // Start reminder checker for the logged-in user
                        ReminderChecker reminderChecker = new ReminderChecker();
                        reminderChecker.startChecking(Authentication.getCurrentUser());
                        while (loggedIn) {
                            System.out.println("\n--- User Menu ---");
                            System.out.println("1. Profile Management");
                            System.out.println("2. Income");
                            System.out.println("3. Budgets");
                            System.out.println("4. Expenses");
                            System.out.println("5. Saving Goals");
                            System.out.println("6. Debts");
                            System.out.println("7. Reminders");
                            System.out.println("8. Insights & Financial Reports");
                            System.out.println("9. Logout");
                            System.out.println("10. Delete Account");
                            System.out.print("Enter your choice: ");
                            String userChoice = scanner.nextLine();

                            switch (userChoice) {
                                case "1": // Profile Management
                                    System.out.println("1. View Profile");
                                    System.out.println("2. Update Profile");
                                    String profileChoice = scanner.nextLine();
                                    if (profileChoice.equals("1")) {
                                        User user = Authentication.getCurrentUser();
                                        System.out.println("Name: " + user.getName());
                                        System.out.println("Email: " + user.getEmail());
                                        System.out.println("Username: " + user.getUsername());
                                        System.out.println("Phone: " + user.getPhoneNumber());
                                    } else if (profileChoice.equals("2")) {
                                        System.out.print("Enter new email (leave blank to keep current): ");
                                        String newEmail = scanner.nextLine();
                                        System.out.print("Enter new username (leave blank to keep current): ");
                                        String newUsername = scanner.nextLine();
                                        System.out.print("Enter new phone number (leave blank to keep current): ");
                                        String newPhone = scanner.nextLine();
                                        Authentication.getCurrentUser().updateProfile(newEmail, newUsername, newPhone);
                                        Authentication.getCurrentUser().updateUserInFile();
                                    }
                                    break;

                                case "2": // Income
                                    System.out.println("1. Track Income");
                                    System.out.println("2. Add Income");
                                    String incomeChoice = scanner.nextLine();
                                    switch (incomeChoice) {
                                        case "1":
                                            System.out.println("Your income list:");
                                            List<Income> incomes = Authentication.getCurrentUser().getIncomes();
                                            if (incomes.isEmpty()) {
                                                System.out.println("  No incomes recorded yet.");
                                            } else {
                                                for (Income income : incomes) {
                                                    System.out.printf("  [%d] %s: %.2f on %s%n",
                                                            income.getIncomeID(),
                                                            income.getSource(),
                                                            income.getAmount(),
                                                            income.getDate()
                                                    );
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.print("Enter income source: ");
                                            String incomeSource = scanner.nextLine();

                                            System.out.print("Enter income amount: ");
                                            float incomeAmount;
                                            try {
                                                incomeAmount = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Income not recorded.");
                                                break;
                                            }

                                            System.out.print("Enter date (YYYY-MM-DD): ");
                                            LocalDate incomeDate;
                                            try {
                                                incomeDate = LocalDate.parse(scanner.nextLine());
                                            } catch (DateTimeParseException e) {
                                                System.out.println("Invalid date format. Income not recorded.");
                                                break;
                                            }
                                            IncomeManager incomeManager = new IncomeManager(Authentication.getCurrentUser());
                                            incomeManager.addIncome(incomeSource, incomeAmount, incomeDate);
                                            System.out.println("Income added successfully!");
                                            break;
                                    }
                                    break;

                                case "3": // Budgets
                                    System.out.println("1. Add Budget Category");
                                    System.out.println("2. View Budget Categories");
                                    String budgetChoice = scanner.nextLine();
                                    switch (budgetChoice) {
                                        case "1":
                                            System.out.print("Enter budget category: ");
                                            String category = scanner.nextLine();

                                            System.out.print("Enter budget amount: ");
                                            float budgetAmount;
                                            try {
                                                budgetAmount = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Budget not added.");
                                                break;
                                            }

                                            System.out.print("Enter start date (YYYY-MM-DD): ");
                                            String startDate = scanner.nextLine();

                                            System.out.print("Enter end date (YYYY-MM-DD): ");
                                            String endDate = scanner.nextLine();

                                            BudgetManager budgetManager = new BudgetManager(Authentication.getCurrentUser());
                                            boolean budgetAdded = budgetManager.addBudget(category, budgetAmount, startDate, endDate);

                                            if (budgetAdded) {
                                                System.out.println("Budget added successfully.");
                                            } else {
                                                System.out.println("Failed to add budget.");
                                            }
                                            break;

                                        case "2":
                                            System.out.println("Your budget categories:");
                                            List<Budget> budgets = Authentication.getCurrentUser().getBudgets();
                                            if (budgets.isEmpty()) {
                                                System.out.println("  No budgets recorded yet.");
                                            } else {
                                                for (Budget budget : budgets) {
                                                    System.out.printf("  [%d] %s: %.2f from %s to %s%n",
                                                            budget.getBudgetID(),
                                                            budget.getCategory(),
                                                            budget.getAmount(),
                                                            budget.getStartDate(),
                                                            budget.getEndDate()
                                                    );
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                    break;

                                case "4": // Expenses
                                    System.out.println("1. Add Expense");
                                    System.out.println("2. View Expenses");
                                    String expenseChoice = scanner.nextLine();
                                    switch (expenseChoice) {
                                        case "1":
                                            System.out.print("Enter expense amount: ");
                                            float expenseAmount;
                                            try {
                                                expenseAmount = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Expense not added.");
                                                break;
                                            }

                                            System.out.print("Enter date (YYYY-MM-DD): ");
                                            LocalDate expenseDate;
                                            try {
                                                expenseDate = LocalDate.parse(scanner.nextLine());
                                            } catch (DateTimeParseException e) {
                                                System.out.println("Invalid date format. Expense not added.");
                                                break;
                                            }

                                            ExpenseManager expenseManager = new ExpenseManager(Authentication.getCurrentUser());
                                            boolean expenseAdded = expenseManager.addExpense(expenseAmount, expenseDate);

                                            if (expenseAdded) {
                                                System.out.println("Expense added successfully.");
                                            } else {
                                                System.out.println("Failed to add expense.");
                                            }
                                            break;

                                        case "2":
                                            System.out.println("Your expense list:");
                                            List<Expense> expenses = Authentication.getCurrentUser().getExpenses();
                                            if (expenses.isEmpty()) {
                                                System.out.println("  No expenses recorded yet.");
                                            } else {
                                                for (Expense expense : expenses) {
                                                    System.out.printf("  [%d] %s: %.2f on %s%n",
                                                            expense.getExpenseID(),
                                                            expense.getCategory(),
                                                            expense.getAmount(),
                                                            expense.getDate()
                                                    );
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                    break;

                                case "5": // Saving Goals
                                    System.out.println("1. Add Saving Goal");
                                    System.out.println("2. View Saving Goals");
                                    String savingChoice = scanner.nextLine();
                                    switch (savingChoice) {
                                        case "1":
                                            System.out.print("Enter saving goal name: ");
                                            String goalName = scanner.nextLine();

                                            System.out.print("Enter target amount: ");
                                            float targetAmount;
                                            try {
                                                targetAmount = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Saving goal not added.");
                                                break;
                                            }

                                            System.out.print("Enter deadline (YYYY-MM-DD): ");
                                            LocalDate deadline;
                                            try {
                                                deadline = LocalDate.parse(scanner.nextLine());
                                            } catch (DateTimeParseException e) {
                                                System.out.println("Invalid date format. Saving goal not added.");
                                                break;
                                            }

                                            Saving_GoalManager savingGoalManager = new Saving_GoalManager(Authentication.getCurrentUser());
                                            boolean goalAdded = savingGoalManager.addSavingGoal(goalName, targetAmount, deadline);

                                            if (goalAdded) {
                                                System.out.println("Saving goal added successfully.");
                                            } else {
                                                System.out.println("Failed to add saving goal.");
                                            }
                                            break;

                                        case "2":
                                            System.out.println("Your saving goals:");
                                            List<Saving_Goal> goals = Authentication.getCurrentUser().getSavingGoals();
                                            if (goals.isEmpty()) {
                                                System.out.println("  No saving goals recorded yet.");
                                            } else {
                                                for (Saving_Goal goal : goals) {
                                                    System.out.printf("  [%d] %s: %.2f by %s%n",
                                                            goal.getGoalID(),
                                                            goal.getName(),
                                                            goal.getTargetAmount(),
                                                            goal.getDeadline()
                                                    );
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                    break;

                                case "6": // Debts
                                    System.out.println("1. Add Debt");
                                    System.out.println("2. View Debts");
                                    String debtChoice = scanner.nextLine();
                                    switch (debtChoice) {
                                        case "1":
                                            System.out.print("Enter debt name: ");
                                            String debtName = scanner.nextLine();

                                            System.out.print("Enter amount owed: ");
                                            float amountOwed;
                                            try {
                                                amountOwed = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Debt not added.");
                                                break;
                                            }
                                            System.out.print("Enter the remaining amount: ");
                                            float remainingAmount;
                                            try {
                                                remainingAmount = Float.parseFloat(scanner.nextLine());
                                            } catch (NumberFormatException e) {
                                                System.out.println("Invalid amount. Debt not added.");
                                                break;
                                            }

                                            System.out.print("Enter due date (YYYY-MM-DD): ");
                                            LocalDate dueDate;
                                            try {
                                                dueDate = LocalDate.parse(scanner.nextLine());
                                            } catch (DateTimeParseException e) {
                                                System.out.println("Invalid date format. Debt not added.");
                                                break;
                                            }

                                            DebtManager debtManager = new DebtManager(Authentication.getCurrentUser());
                                            boolean debtAdded = debtManager.addDebt(debtName, amountOwed, remainingAmount, dueDate);

                                            if (debtAdded) {
                                                System.out.println("Debt added successfully.");
                                            } else {
                                                System.out.println("Failed to add debt.");
                                            }
                                            break;

                                        case "2":
                                            System.out.println("Your debts:");
                                            List<Debt> debts = Authentication.getCurrentUser().getDebts();
                                            if (debts.isEmpty()) {
                                                System.out.println("  No debts recorded yet.");
                                            } else {
                                                for (Debt debt : debts) {
                                                    System.out.printf("  [%d] %s: %.2f remaining: %.2f due: %s%n",
                                                            debt.getDebtID(),
                                                            debt.getCreditor(),
                                                            debt.getAmountOwed(),
                                                            debt.getRemainingAmount(),
                                                            debt.getDueDate()
                                                    );
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                    break;

                                case "7": // Reminders
                                    System.out.println("1. View Reminders");
                                    System.out.println("2. Add Reminder");
                                    String reminderChoice = scanner.nextLine();
                                    switch (reminderChoice) {
                                        case "1":
                                            List<Reminder> reminders = Authentication.getCurrentUser().getReminders();
                                            if (reminders == null || reminders.isEmpty()) {
                                                System.out.println("No reminders found.");
                                            } else {
                                                for (Reminder reminder : reminders) {
                                                    System.out.printf("  [%d] %s on %s at %s%n",
                                                        reminder.getReminderId(),
                                                        reminder.getReminderTitle(),
                                                        new SimpleDateFormat("yyyy-MM-dd").format(reminder.getReminderDate()),
                                                        reminder.getReminderTime().toString()
                                                    );
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.print("Enter reminder title: ");
                                            String reminderTitle = scanner.nextLine();
                                            System.out.print("Enter reminder date (YYYY-MM-DD): ");
                                            String dateStr = scanner.nextLine();
                                            System.out.print("Enter reminder time (HH:MM:SS): ");
                                            String timeStr = scanner.nextLine();
                                            Date reminderDate;
                                            Time reminderTime;
                                            try {
                                                reminderDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                                                reminderTime = Time.valueOf(timeStr);
                                            } catch (ParseException | IllegalArgumentException e) {
                                                System.out.println("Invalid date or time format. Reminder not added.");
                                                break;
                                            }
                                            int reminderId = Authentication.getCurrentUser().getReminders().size() + 1;
                                            ReminderManager reminderManager = new ReminderManager();
                                            reminderManager.addReminder(Authentication.getCurrentUser(), reminderId, reminderTitle, reminderDate, reminderTime);
                                            System.out.println("Reminder added successfully!");
                                            break;
                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                    break;

                                case "8": // Insights & Financial Reports
                                    System.out.println("Generating financial report...");
                                    Insights_and_Financial_reports insights = new Insights_and_Financial_reports(Authentication.getCurrentUser());
                                    insights.generateFinancialReport();
                                    break;
                                case "9": // Logout
                                    auth.logout();
                                    loggedIn = false;
                                    reminderChecker.stopChecking();
                                    break;

                                case "10": // Delete Account
                                    System.out.print("Are you sure you want to delete your account? (yes/no): ");
                                    String confirmDelete = scanner.nextLine();
                                    if (confirmDelete.equalsIgnoreCase("yes")) {
                                        Authentication.getCurrentUser().deleteAccount();
                                        auth.logout();
                                        loggedIn = false;
                                        reminderChecker.stopChecking();
                                        System.out.println("Account deleted successfully.");
                                    } else {
                                        System.out.println("Account deletion canceled.");
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
