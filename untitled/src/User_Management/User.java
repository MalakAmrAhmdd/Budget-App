package User_Management;
import Budgeting_Functionalities.Income;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class User {
    private int userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;

    private List<Income> incomes = new ArrayList<Income>();
//    private List<Expense> expenses;
//    private List<Goal> goals;
//    private List<Saving> savings;
//    private List<Debt> debts;
    private int nextIncomeID;

//    public void addIncome(String source, float amount, LocalDate date) {
//        Income income = new Income(nextIncomeID++, source, amount, date);
//        incomes.add(income);
//        System.out.println("Added Income " + income + "to user " + username + ".");
//    }
public void addIncome(String source, float amount, LocalDate date) {
    Income income = new Income(nextIncomeID++, source, amount, date);
    incomes.add(income);
    System.out.println("Added Income " + income + " to user " + username + ".");
    updateUserInFile(); // Update the user in the JSON file
}

   private void updateUserInFile() {
       ObjectMapper objectMapper = new ObjectMapper();
//       objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()); // Register JavaTimeModule
       File file = new File("users.json");
       if (!file.exists()) {
           System.out.println("User file not found.");
           return;
       }

       try {
           // Read all users from the file
           List<User> users = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));

           // Find and update the current user
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

    public List<Income> getIncomes() {
        return incomes;
    }
    @JsonIgnore
    public float getTotalIncome() {
        float total = 0;
        for (Income income : incomes) {
            total += income.getAmount();
        }
        return total;
    }

    // Default constructor required for Jackson deserialization
    public User() {
    }

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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.username = username;
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

    public void deleteAccount() {
        // Logic for deleting account
    }
}
