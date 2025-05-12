package User_Management;

import java.util.List;

public class User {
    private int userId;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;

    private List<Income> incomes;
    private List<Expense> expenses;
    private List<Goal> goals;
    private List<Saving> savings;
    private List<Debt> debts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }


    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Saving> getSavings() {
        return savings;
    }

    public void setSavings(List<Saving> savings) {
        this.savings = savings;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }

    public void updateProfile(String email, String username, String phoneNumber) {
        // Logic for updating profile
        if(email!= null && !email.isEmpty()) {
            this.email = email;
        }
        if(username!= null && !username.isEmpty()) {
            this.username = username;
        }
        if(phoneNumber!= null && !phoneNumber.isEmpty()) {
            this.phoneNumber = phoneNumber;
        }
        System.out.println("Profile updated successfully!");
    }

    public void deleteAccount() {
        // Logic for deleting account
    }
}
