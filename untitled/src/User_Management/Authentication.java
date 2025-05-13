package User_Management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private static User currentUser = null; // Stores the currently logged-in user
    private static final String USERS_FILE = "users.json";
    private Map<String, User> users = new HashMap<>(); // Store users in a HashMap

    public Authentication() {
        loadUsers(); // Load users from the file during initialization
    }
    private void loadUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(USERS_FILE);
        if (file.exists() && file.length() > 0) {
            try {
                List<User> userList = Arrays.asList(objectMapper.readValue(file, User[].class));
                for (User user : userList) {
                    users.put(user.getUsername(), user); // Store users in the HashMap
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("User not found or incorrect credentials.");
        return false;
    }

    public void logout() {
        currentUser = null; // Clear the current user
        System.out.println("User logged out.");
    }

    public void signUp(String name, String email, String username, String password, String phoneNumber) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        int newUserId = users.size() + 1; // Generate a new user ID
        User newUser = new User(newUserId, name, email, username, password, phoneNumber);
        users.put(username, newUser); // Add the user to the HashMap
        saveUsers(); // Save the updated users to the file
        System.out.println("Sign-up successful!");
    }

    public static User getCurrentUser() {
        return currentUser; // Retrieve the current logged-in user
    }
}