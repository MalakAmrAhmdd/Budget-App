package Notification;

import User_Management.User;

import java.sql.Time;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Periodically checks if the current date and time match any reminders for the logged-in user.
 */
public class ReminderChecker {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Starts the reminder checking process for the logged-in user.
     *
     * @param currentUser The currently logged-in user.
     */
    public void startChecking(User currentUser) {
        if (currentUser == null) {
            throw new IllegalStateException("No user is logged in.");
        }

        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<Reminder> reminders = currentUser.getReminders();
                if (reminders != null) {

                    // ... inside the scheduled task
                    LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

                    for (Reminder reminder : reminders) {
                        LocalDate reminderDate = reminder.getReminderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalTime reminderTime = reminder.getReminderTime().toLocalTime().withSecond(0).withNano(0);
                        LocalDateTime reminderDateTime = LocalDateTime.of(reminderDate, reminderTime);

                        // Check if the reminder is within the current minute
                        if (!reminderDateTime.isAfter(now) && !reminderDateTime.isBefore(now.minusMinutes(1))) {
                            System.out.println("Triggering notification for: " + reminder.getReminderTitle());
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.sendNotification(currentUser, reminder.getReminderId(), reminder.getReminderTitle());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    /**
     * Stops the reminder checking process.
     */
    public void stopChecking() {
        scheduler.shutdown();
    }
}
