package Analytics;

import Budgeting_Functionalities.Expense;
import Budgeting_Functionalities.Income;
import Budgeting_Functionalities.Debt;
import User_Management.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class Insights_and_Financial_reports {

    private final User user;

    public Insights_and_Financial_reports(User user) {
        this.user = user;
    }

    public void generateFinancialReport() {
        System.out.println("\n--- Financial Report ---");
        displayIncomeSources();
        displayCategorizedSpending();
        displayDebtStatus();
        provideInsightsAndRecommendations();
    }

    private void displayIncomeSources() {
        System.out.println("\nIncome Sources:");
        List<Income> incomes = user.getIncomes();
        if (incomes == null || incomes.isEmpty()) {
            System.out.println("  No income data available.");
        } else {
            Map<String, Double> incomeSources = incomes.stream()
                    .collect(Collectors.groupingBy(Income::getSource, Collectors.summingDouble(Income::getAmount)));
            incomeSources.forEach((source, total) ->
                    System.out.printf("  %s: %.2f%n", source, total)
            );
        }
    }

    private void displayCategorizedSpending() {
        System.out.println("\nCategorized Spending:");
        List<Expense> expenses = user.getExpenses();
        if (expenses == null || expenses.isEmpty()) {
            System.out.println("  No expense data available.");
        } else {
            Map<String, Double> spendingByCategory = expenses.stream()
                    .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));
            spendingByCategory.forEach((category, total) ->
                    System.out.printf("  %s: %.2f%n", category, total)
            );
        }
    }

    private void displayDebtStatus() {
        System.out.println("\nDebt Status:");
        List<Debt> debts = user.getDebts();
        float totalDebt = user.getTotalDebt();
        if (debts == null || debts.isEmpty()) {
            System.out.println("  No outstanding debt.");
        } else {
            debts.forEach(debt -> {
                LocalDate dueDate = debt.getDueDate();
                System.out.printf("  Debt ID: %d, Creditor: %s, Remaining Amount: %.2f, Amount Owed: %.2f, Due Date: %s%n",
                        debt.getDebtID(),
                        debt.getCreditor(),
                        debt.getRemainingAmount(),
                        debt.getAmountOwed(),
                        dueDate != null ? dueDate.toString() : "N/A"
                );
            });
        }
    }

    private void provideInsightsAndRecommendations() {
        System.out.println("\nInsights and Recommendations:");
        float totalIncome = 0;
        float totalExpenses = 0;
        float totalSavings = 0;
        float totalDebt = user.getTotalDebt();

        List<Income> incomes = user.getIncomes();
        if (incomes != null) {
            totalIncome = (float) incomes.stream().mapToDouble(Income::getAmount).sum();
        }
        List<Expense> expenses = user.getExpenses();
        if (expenses != null) {
            totalExpenses = (float) expenses.stream().mapToDouble(Expense::getAmount).sum();
        }
        if (user.getSavingGoals() != null) {
            totalSavings = (float) user.getSavingGoals().stream().mapToDouble(g -> {
                try {
                    return g.getTotalSavings();
                } catch (Exception e) {
                    return 0.0;
                }
            }).sum();
        }

        if (totalIncome > totalExpenses) {
            System.out.println("  Good job! Your income exceeds your expenses.");
        } else {
            System.out.println("  Warning: Your expenses exceed your income. Consider reducing spending.");
        }

        if (totalSavings < totalIncome * 0.2) {
            System.out.println("  Recommendation: Aim to save at least 20% of your income.");
        }

        System.out.printf("  Total Debt: %.2f%n", totalDebt);

        if (totalDebt > 0) {
            System.out.println("  Recommendation: Focus on paying off your debt to improve financial health.");
        }
    }
}
