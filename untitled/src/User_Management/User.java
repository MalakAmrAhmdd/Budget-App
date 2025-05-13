package User_Management;
import Budgeting_Functionalities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;

    private List<Income> incomes = new ArrayList<Income>();
    private int nextIncomeID = 0;
    private List<Expense> expenses = new ArrayList<Expense>();
    private List<Saving_Goal> savingGoals = new ArrayList<Saving_Goal>();
    private List<Debt> debts = new ArrayList<Debt>();
    private List<Budget> budgets = new ArrayList<Budget>();

    //Default constructor for Jackson
    public User() {}

    //parameterized constructor
    public User(int userId, String name, String email, String username, String password, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Saving_Goal> getSavings() {
        return savingGoals;
    }
    public List<Debt> getDebts() {
        return debts;
    }
    public List<Budget> getBudgets() {
        return budgets;
    }

    public int getNextIncomeID() {
        return nextIncomeID;
    }

    //methods

    public void updateProfile(String email, String username, String phoneNumber) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            this.phoneNumber = phoneNumber;
        }
        System.out.println("Profile updated successfully!");
    }

    @JsonIgnore
    public float getTotalIncome() {
        float total = 0;
        for (Income income : incomes) {
            total += income.getAmount();
        }
        for(Budget budget : budgets){
            total-= budget.getLimit();
        }
        return total;
    }
    @JsonIgnore
    public float getTotalDebt() {
        float total = 0;
        for (Debt debt : debts) {
            total += debt.getAmount();
        }
        return total;

    }
    @JsonIgnore
    public float getTotalSavings() {
        float total = 0;
        for (Saving_Goal savingGoal : savingGoals) {
            total += savingGoal.getTotalSavings();
        }
        return total;
    }
    @JsonIgnore
    public float getTotalExpenses() {
        float total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public void deleteAccount() {
        // Logic for deleting account
    }

    public void updateUserInFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()); // Register JavaTimeModule
        File file = new File("users.json");
        if (!file.exists()) {
            System.out.println("User file not found.");
            return;
        }

        try {
            List<User> users = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserId() == this.userId) {
                    users.set(i, this);
                    break;
                }
            }

            // Write the updated list back to the file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}

