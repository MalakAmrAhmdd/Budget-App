package Notification;

import User_Management.User;

import java.sql.Time;
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
            List<Reminder> reminders = currentUser.getReminders();
            if (reminders != null) {
                Date now = new Date();
                Time currentTime = new Time(now.getTime());

                for (Reminder reminder : reminders) {
                    if (reminder.getReminderDate().equals(now) && reminder.getReminderTime().equals(currentTime)) {
                        NotificationManager notificationManager = new NotificationManager();
                        notificationManager.sendNotification(reminder.getReminderId(), reminder.getReminderTitle(), now);
                    }
                }
            }
        }, 0, 1, TimeUnit.MINUTES); // Check every minute
    }

    /**
     * Stops the reminder checking process.
     */
    public void stopChecking() {
        scheduler.shutdown();
    }
}