package Notification;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a MailManager that sends emails for important notifications (OTP and reminders)
 */
public class MailManager {
    private String apiKey = "9385dd8f2e046880bd11631303c00151"; // Replace with your Mailjet API key
    private String apiSecret = "bfb13a2ecbb2434fbe296374fb43d42b"; // Replace with your Mailjet API secret
    /**
     * Default constructor for MailManager.
     */
    public MailManager() {
        // Initialize the mail object if needed
    }

    /**
     * Sends an OTP email to the user.
     *
     * @param email The recepient email.
     * @param otp The OTP to be sent.
     */
    public void sendOTPEmail(String email, String otp) {
        // Mailjet API configuration
        MailjetClient client = new MailjetClient(ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build());
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put("From", new JSONObject()
                                        .put("Email", "20230220@stud.fci-cu.edu.eg") // Replace with your email
                                        .put("Name", "Budgeting App")) // Replace with your name
                                .put("To", new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", email)
                                                .put("Name", "User")))
                                .put("Subject", "Your OTP Code")
                                .put("TextPart", "Your OTP is: " + otp)
                                .put("HTMLPart", "<h3>Your OTP is: " + otp + "</h3>")));

        try {
            MailjetResponse response = client.post(request);
            if (response.getStatus() == 200) {
                System.out.println("OTP sent successfully to " + email);
            } else {
                System.out.println("Failed to send OTP. Response: " + response.getData());
            }
        } catch (MailjetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a notification email to the user.
     *
     * @param email The recipient email.
     * @param subject The subject of the email.
     * @param message The message to be sent.
     */
    public void sendNotificationEmail(String email, String subject, String message) {
        // Mailjet API configuration
        MailjetClient client = new MailjetClient(ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build());
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put("From", new JSONObject()
                                        .put("Email", "20230220@stud.fci-cu.edu.eg")
                                        .put("Name", "Budgeting App")) // Replace with your name
                                .put("To", new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", email)
                                                .put("Name", "User")))
                                .put("Subject", subject)
                                .put("TextPart", message)
                                .put("HTMLPart", "<h3>" + message + "</h3>")));
        try {
            MailjetResponse response = client.post(request);
            if (response.getStatus() == 200) {
                System.out.println("Notification sent successfully to " + email);
            } else {
                System.out.println("Failed to send notification. Response: " + response.getData());
            }
        } catch (MailjetException e) {
            e.printStackTrace();
        }
    }
}
