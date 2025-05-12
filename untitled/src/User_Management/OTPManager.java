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

public class OTPManager {
    private static final int OTP_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();
    private String storedOTP;

    public String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        storedOTP = otp.toString();
        return storedOTP;
    }

    public boolean validateOTP(String otp) {
        if (storedOTP != null && storedOTP.equals(otp)) {
            storedOTP = null; // Invalidate OTP after successful validation
            return true;
        }
        return false;
    }

    public void sendOTPViaEmail(String recipientEmail) {
        String otp = generateOTP(); // Generate OTP

        // Mailjet API configuration
        String apiKey = "your-mailjet-api-key"; // Replace with your Mailjet API key
        String apiSecret = "your-mailjet-api-secret"; // Replace with your Mailjet API secret

        MailjetClient client = new MailjetClient(ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build());
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put("From", new JSONObject()
                                        .put("Email", "yassinsawy@outlook.com") // Replace with your email
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