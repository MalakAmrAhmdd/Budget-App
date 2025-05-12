package User_Management;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Authentication {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    private static User currentUser = null; // Stores the currently logged-in user

    public boolean login(String username, String password) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = objectMapper.readValue(line, User.class);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user; // Set the current user
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
        int userId = ID_COUNTER.getAndIncrement();
        User newUser = new User(userId, name, email, username, password, phoneNumber);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser;
        try {
            jsonUser = objectMapper.writeValueAsString(newUser);
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if serialization fails
        }
        try (FileWriter file = new FileWriter("users.json", true)) { // Open file in append mode
            file.write(jsonUser + System.lineSeparator());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getCurrentUser() {
        return currentUser; // Retrieve the current logged-in user
    }
}