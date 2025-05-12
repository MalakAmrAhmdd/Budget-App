import User_Management.Authentication;
//import User_Management.OTPManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Authentication auth = new Authentication();
//        OTPManager otpManager = new OTPManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Budget App Menu ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Send OTP to Email");
            System.out.println("4. Validate OTP");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    auth.signUp(name, email, username, password, phoneNumber);
                    System.out.println("Sign-up successful!");
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (auth.login(loginUsername, loginPassword)) {
                        System.out.println("Welcome, " + Authentication.getCurrentUser().getUsername() + "!");
                    }
                    break;

//                case 3: // Fix OTP sending option
//                    if (Authentication.getCurrentUser() != null) {
//                        String recipientEmail = Authentication.getCurrentUser().getEmail();
//                        otpManager.sendOTPViaEmail(recipientEmail);
//                    } else {
//                        System.out.println("Please log in first.");
//                    }
//                    break;
//
//                case 4: // Fix OTP validation option
//                    if (Authentication.getCurrentUser() != null) {
//                        System.out.print("Enter the OTP: ");
//                        String otp = scanner.nextLine();
//                        if (otpManager.validateOTP(otp)) {
//                            System.out.println("OTP validated successfully!");
//                        } else {
//                            System.out.println("Invalid OTP.");
//                        }
//                    } else {
//                        System.out.println("Please log in first.");
//                    }
//                    break;

                case 5:
                    auth.logout();
                    break;

                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
