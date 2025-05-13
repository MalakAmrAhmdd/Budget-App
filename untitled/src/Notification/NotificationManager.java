package Notification;

import java.time.LocalDate;
import java.time.LocalTime;

public class NotificationManager
{
    /**
     * Default constructor for NotificationManager.
     */
    public NotificationManager() {
        // Initialize the notification object if needed
    }

    /**
     * Sends a notification to the user.
     * @param notificationId The ID of the notification.
     * @param content The content of the notification.
     */
    public void sendNotification(int notificationId, String content) {
        // Get the current date and time
        LocalDate d = LocalDate.now();
        LocalTime t = LocalTime.now();
        Notification notification = new Notification(notificationId, content, d, t);
        System.out.println("Notification sent: " + notification.getContent() + " at " + notification.getSentTime());
    }
}
