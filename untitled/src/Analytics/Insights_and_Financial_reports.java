package Analytics;

import Budgeting_Functionalities.Expense;
import Budgeting_Functionalities.Income;
import User_Management.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (incomes.isEmpty()) {
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
        List<Budgeting_Functionalities.Expense> expenses = user.getExpenses(); // Assuming `getExpenses` exists in `User`
        if (expenses.isEmpty()) {
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
        float totalDebt = user.getTotalDebt(); // Assuming `getTotalDebt` exists in `User`
        if (totalDebt == 0) {
            System.out.println("  No outstanding debt.");
        } else {
            System.out.printf("  Total Debt: %.2f%n", totalDebt);
        }
    }

    private void provideInsightsAndRecommendations() {
        System.out.println("\nInsights and Recommendations:");
        float totalIncome = user.getTotalIncome();
        float totalExpenses = user.getTotalExpenses(); // Assuming `getTotalExpenses` exists in `User`
        float totalSavings = user.getTotalSavings(); // Assuming `getTotalSavings` exists in `User`

        if (totalIncome > totalExpenses) {
            System.out.println("  Good job! Your income exceeds your expenses.");
        } else {
            System.out.println("  Warning: Your expenses exceed your income. Consider reducing spending.");
        }

        if (totalSavings < totalIncome * 0.2) {
            System.out.println("  Recommendation: Aim to save at least 20% of your income.");
        }

        if (user.getTotalDebt() > 0) {
            System.out.println("  Recommendation: Focus on paying off your debt to improve financial health.");
        }
    }
}
