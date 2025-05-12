package User_Management;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Authentication {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    private static JSONObject currentUser = null; // Stores the currently logged-in user

    public boolean login(String username, String password) {
        JSONParser parser = new JSONParser();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject user = (JSONObject) parser.parse(line);
                if (user.get("username").equals(username) && user.get("password").equals(password)) {
                    currentUser = user; // Set the current user
                    System.out.println("Login successful!");
                    return true;
                }
            }
        } catch (IOException | ParseException e) {
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

    public static JSONObject getCurrentUser() {
        return currentUser; // Retrieve the current logged-in user
    }
}