package User_Management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles user authentication, sign-up, login, and logout functionality.
 */
public class Authentication {
    private static User currentUser = null; // Stores the currently logged-in user
    private static final String USERS_FILE = "users.json";
    private Map<String, User> users = new HashMap<>(); // Store users in a HashMap

    /**
     * Constructs an Authentication object and loads users from file.
     */
    public Authentication() {
        loadUsers(); // Load users from the file during initialization
    }

    /**
     * Loads users from the users.json file into the users map.
     */
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

    /**
     * Saves the current users map to the users.json file.
     */
    public void saveUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to log in a user with the given username and password.
     * @param username The username
     * @param password The password
     * @return true if login is successful, false otherwise
     */
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

    /**
     * Logs out the current user.
     */
    public void logout() {
        currentUser = null; // Clear the current user
        System.out.println("User logged out.");
    }

    /**
     * Registers a new user after OTP verification.
     * @param name The user's name
     * @param email The user's email
     * @param username The desired username
     * @param password The desired password
     * @param phoneNumber The user's phone number
     */
    public void signUp(String name, String email, String username, String password, String phoneNumber) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        OTPManager otpManager = new OTPManager();
        otpManager.generateOTP(); // Generate OTP
        otpManager.sendOTPViaEmail(email); // Send OTP to the user's email
        System.out.println("An OTP has been sent to your email. Please enter it to complete the sign-up process.");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter the OTP: ");
            String otp = scanner.nextLine();
            if (otpManager.validateOTP(otp)) {
                System.out.println("OTP validated successfully.");
                break;
            } else {
                System.out.println("Invalid OTP. Please try again.");
            }
        }
        while (true);

        int newUserId = users.size() + 1; // Generate a new user ID
        User newUser = new User(newUserId, name, email, username, password, phoneNumber);
        users.put(username, newUser);
        saveUsers();
        System.out.println("Sign-up successful!");
    }

    /**
     * Gets the currently logged-in user.
     * @return The current user, or null if not logged in
     */
    public static User getCurrentUser() {
        return currentUser; // Retrieve the current logged-in user
    }
}
