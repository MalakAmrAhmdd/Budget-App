import Budgeting_Functionalities.*;
import User_Management.Authentication;
import User_Management.OTPManager;
//import User_Management.OTPManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
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
