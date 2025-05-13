package User_Management;

import Notification.MailManager;

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

        MailManager mailManager = new MailManager();
        mailManager.sendOTPEmail(recipientEmail, otp); // Send OTP to the user's email
    }
}
