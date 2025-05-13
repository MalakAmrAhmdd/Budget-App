package Budgeting_Functionalities;

import User_Management.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

/**
 * Manages income-related operations for a user.
 */
public class IncomeManager {
    private User user;
    /**
     * Default constructor.
     */
    public IncomeManager() {}
    /**
     * Constructs an IncomeManager for a specific user.
     *
     * @param user The user associated with this IncomeManager.
     */
    public IncomeManager(User user) {
        this.user = user;
    }
    /**
     * Adds a new income for the user.
     *
     * @param source The source of the income.
     * @param amount The amount of the income.
     * @param date   The date of the income.
     */
    public void addIncome(String source, float amount, LocalDate date) {
        List<Income> incomes = user.getIncomes();
        int nextID = incomes.size() + 1;
        Income income = new Income(nextID, source, amount, date);
        incomes.add(income);
        user.updateUserInFile();
    }


}
