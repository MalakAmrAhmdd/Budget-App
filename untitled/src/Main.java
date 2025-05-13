import Budgeting_Functionalities.*;
import User_Management.Authentication;
import User_Management.OTPManager;
import Analytics.Insights_and_Financial_reports;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("3. Send OTP to Email");
            System.out.println("4. Validate OTP");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                        System.out.println("Welcome, " + Authentication.getCurrentUser().getUsername() + "!");
                        System.out.println("What do you want to do?");
                        System.out.println("1. Set & Track Income");
                        System.out.println("2. Set & Track Budget Categories");
                        System.out.println("3. Set & Track Expenses");
                        System.out.println("4. Set & Track Saving Goals");
                        System.out.println("5. Set & Track Debts");
                        System.out.println("6. Insights & Financial Reports");
                        String menuChoice = scanner.nextLine();
                        switch (menuChoice) {
                            case "1":
                                System.out.println("Your total income is " + Authentication.getCurrentUser().getTotalIncome());
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
                            case "2":
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
                            case "3":
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
                            case "4":
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
                            case "5":
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
                                                System.out.printf("  [%d] %s: %.2f remaining: %s%n",
                                                        debt.getDebtID(),
                                                        debt.getCreditor(),
//                                                        debt.getName(),
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
                            case "6":
                                System.out.println("Generating financial report...");
                                Insights_and_Financial_reports insights = new Insights_and_Financial_reports(Authentication.getCurrentUser());
                                insights.generateFinancialReport();
                                break;
                            default:
                                System.out.println("Invalid choice.");

                        }
                    }
                    break;

                case 3: // Fix OTP sending option
                    if (Authentication.getCurrentUser() != null) {
                        String recipientEmail = Authentication.getCurrentUser().getEmail();
                        otpManager.sendOTPViaEmail(recipientEmail);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 4: // Fix OTP validation option
                    if (Authentication.getCurrentUser() != null) {
                        System.out.print("Enter the OTP: ");
                        String otp = scanner.nextLine();
                        if (otpManager.validateOTP(otp)) {
                            System.out.println("OTP validated successfully!");
                        } else {
                            System.out.println("Invalid OTP.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 5:
                    auth.logout();
                    break;

                case 6:
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

