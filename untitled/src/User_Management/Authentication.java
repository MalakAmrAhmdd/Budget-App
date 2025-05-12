package User_Management;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Authentication {
    private static User currentUser = null; // Stores the currently logged-in user

    public boolean login(String username, String password) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        if (!file.exists() || file.length() == 0) {
            System.out.println("No users found.");
            return false;
        }

        try {
            List<User> users = Arrays.asList(objectMapper.readValue(file, User[].class));
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    System.out.println("Login successful!");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User not found or incorrect credentials.");
        return false;
    }

    public void logout() {
        currentUser = null; // Clear the current user
        System.out.println("User logged out.");
    }

    public void signUp(String name, String email, String username, String password, String phoneNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        List<User> users = new ArrayList<>();
        if (file.exists() && file.length() > 0) {
            try {
                users = Arrays.asList(objectMapper.readValue(file, User[].class));
                users = new ArrayList<>(users); // To make it mutable
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int max = users.stream().mapToInt(User::getUserId).max().orElse(0);
        int newUserId = max+1;
        User newUser = new User(newUserId, name, email, username, password, phoneNumber);
        users.add(newUser);

        try (FileWriter writer = new FileWriter(file)) { // Open file in append mode
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getCurrentUser() {
        return currentUser; // Retrieve the current logged-in user
    }
}