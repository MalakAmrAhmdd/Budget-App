package User_Management;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.SecureRandom;

/**
 * Manages the generation, validation, and sending of OTP codes via email.
 */
public class OTPManager {
    private static final int OTP_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();
    private String storedOTP; // Ensure thread-safety

    /**
     * Generates a random 6-digit OTP and stores it.
     * @return The generated OTP as a String.
     */
    public String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        storedOTP = otp.toString();
        return storedOTP;
    }

    /**
     * Validates the provided OTP against the stored OTP.
     * @param otp The OTP to validate.
     * @return true if the OTP is valid, false otherwise.
     */
    public synchronized boolean validateOTP(String otp) {
        if (storedOTP != null && storedOTP.equals(otp)) {
            storedOTP = null; // Invalidate OTP after successful validation
            return true;
        }
        return false;
    }

    /**
     * Generates an OTP and sends it to the specified email address using Mailjet.
     * @param recipientEmail The recipient's email address.
     */
    public void sendOTPViaEmail(String recipientEmail) {
        String otp = generateOTP(); // Generate OTP

        // Mailjet API configuration
        String apiKey = "9385dd8f2e046880bd11631303c00151"; // Replace with your Mailjet API key
        String apiSecret = "bfb13a2ecbb2434fbe296374fb43d42b"; // Replace with your Mailjet API secret

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
                                                .put("Email", recipientEmail)
                                                .put("Name", "User")))
                                .put("Subject", "Your OTP Code")
                                .put("TextPart", "Your OTP is: " + otp)
                                .put("HTMLPart", "<h3>Your OTP is: " + otp + "</h3>")));

        try {
            MailjetResponse response = client.post(request);
            if (response.getStatus() == 200) {
                System.out.println("OTP sent successfully to " + recipientEmail);
            } else {
                System.out.println("Failed to send OTP. Response: " + response.getData());
            }
        } catch (MailjetException e) {
            e.printStackTrace();
        }
    }
}
