# Budget-App

## Description

Budget-App is a Java-based budgeting application that helps users manage their finances. It allows users to track incomes and expenses, set budgets, saving goals, debts, and receive reminders via email notifications.

## Project Structure

- `Main.java`  
  Entry point of the application. Provides a menu-driven interface for user interaction.

- `User_Management/`
  - `User.java`: Represents a user and stores profile and financial data.
  - `Authentication.java`: Handles user sign-up, login, and logout.
  - `OTPManager.java`: Manages OTP generation, validation, and sending via email.

- `Notification/`
  - `Notification.java`: Represents a notification sent to the user.
  - `NotificationManager.java`: Handles sending notifications and emails.
  - `MailManager.java`: Integrates with Mailjet API to send emails.
  - `Reminder.java`: Represents a reminder with a title, date, and time.
  - `ReminderManager.java`: Manages adding reminders for users.
  - `ReminderChecker.java`: Periodically checks and triggers reminders.

- `Budgeting_Functionalities/`  
  Contains classes for managing incomes, expenses, budgets, saving goals, and debts.

- `users.json`  
  Stores user data, including financial records and reminders, in JSON format.

- `pom.xml`  
  Maven configuration file specifying project dependencies.

## Tools & Technologies

- Java (JDK 8+)
- Maven (for dependency management and build)
- Mailjet API (for sending emails)
- Jackson (for JSON serialization/deserialization)
- IntelliJ IDEA (recommended IDE)

## How to Run

1. Ensure you have Java and Maven installed.
2. Import the project into IntelliJ IDEA or your preferred IDE.
3. Build the project using Maven.
4. Run `Main.java` to start the application.

## Notes

- Update Mailjet API keys in `MailManager.java` with your own credentials for email functionality.
- All user data is stored in `users.json` in the project root.