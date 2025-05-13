package Budgeting_Functionalities;

import User_Management.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

public class IncomeManager {
    private User user;
    public IncomeManager() {}
    public IncomeManager(User user) {
        this.user = user;
    }

    public void addIncome(String source, float amount, LocalDate date) {
        List<Income> incomes = user.getIncomes();
        int nextID = incomes.size() + 1;
        Income income = new Income(nextID, source, amount, date);
        incomes.add(income);
//        System.out.println("Added Income " + income + " to user " + username + ".");
        user.updateUserInFile();
    }

    @JsonIgnore
    public float getTotalIncome() {
        float total = 0;
        for (Income income : user.getIncomes()) {
            total += income.getAmount();
        }
        return total;
    }
}
