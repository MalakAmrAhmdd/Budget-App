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

    public void updateProfile(String email, String username, String phoneNumber) {
        // Logic for updating profile
    }

    public void deleteAccount() {
        // Logic for deleting account
    }
}
