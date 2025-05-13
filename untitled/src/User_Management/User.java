package User_Management;
import Budgeting_Functionalities.*;
import Notification.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a user in the budgeting application.
 * Stores user profile information and financial data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("saving_goals")
    private List<Saving_Goal> savingGoals = new ArrayList<Saving_Goal>();
    @JsonProperty("debts")
    private List<Debt> debts = new ArrayList<Debt>();
    private List<Budget> budgets = new ArrayList<Budget>();
    private List<Reminder> reminders = new ArrayList<Reminder>();

    /**
     * Default constructor for Jackson.
     */
    public User() {}

    /**
     * Parameterized constructor to create a user.
     * @param userId User ID
     * @param name Name
     * @param email Email
     * @param username Username
     * @param password Password
     * @param phoneNumber Phone number
     */
    public User(int userId, String name, String email, String username, String password, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user ID.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the user's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's phone number.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the list of incomes.
     * @return incomes
     */
    public List<Income> getIncomes() {
        return incomes;
    }

    /**
     * Gets the list of expenses.
     * @return expenses
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Gets the list of saving goals.
     * @return savingGoals
     */
    public List<Saving_Goal> getSavingGoals() {
        return savingGoals;
    }

    /**
     * Gets the list of debts.
     * @return debts
     */
    public List<Debt> getDebts() {
        return debts;
    }

    /**
     * Gets the list of budgets.
     * @return budgets
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Gets the next income ID.
     * @return nextIncomeID
     */
    public int getNextIncomeID() {
        return nextIncomeID;
    }


    /**
     * Gets the list of reminders.
     */
    public List<Reminder> getReminders() {
        return reminders;
    }

    /**
     * Updates the user's profile information.
     * @param email New email
     * @param username New username
     * @param phoneNumber New phone number
     */
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

    /**
     * Calculates the total income minus budgets.
     * @return total income
     */
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

    /**
     * Calculates the total debt.
     * @return total debt
     */
    @JsonIgnore
    public float getTotalDebt() {
        float total = 0;
        for (Debt debt : debts) {
            total += debt.getAmountOwed();
        }
        return total;
    }

    /**
     * Calculates the total savings from all saving goals.
     * @return total savings
     */
    @JsonIgnore
    public float getTotalSavings() {
        float total = 0;
        for (Saving_Goal savingGoal : savingGoals) {
            total += savingGoal.getTotalSavings();
        }
        return total;
    }

    /**
     * Calculates the total expenses.
     * @return total expenses
     */
    @JsonIgnore
    public float getTotalExpenses() {
        float total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    /**
     * Deletes the user's account.
     */
    public void deleteAccount() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()); // Register JavaTimeModule
        File file = new File("users.json");
        if (!file.exists()) {
            System.out.println("User file not found.");
            return;
        }

        try {
            // Read the list of users from the file
            List<User> users = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));

            // Remove the user with the matching userId
            users.removeIf(user -> user.getUserId() == this.userId);

            // Write the updated list back to the file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            System.out.println("Account deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Updates the user's data in the users.json file.
     */
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

    /**
     * Sets the list of expenses.
     * @param expenses List of expenses
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Sets the list of saving goals.
     * @param goals List of saving goals
     */
    public void setSavingGoals(List<Saving_Goal> goals) {
        this.savingGoals = goals;
    }

    /**
     * Sets the list of reminders.
     * @param reminders List of reminders
     */
    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

}
