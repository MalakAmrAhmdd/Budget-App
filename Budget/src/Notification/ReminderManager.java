package Notification;

import User_Management.User;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Manages reminders for a user, including adding new reminders.
 */
public class ReminderManager {

    /**
     * Adds a new reminder to the specified user.
     *
     * @param user          The user to whom the reminder will be added.
     * @param reminderId    The unique ID of the reminder.
     * @param reminderTitle The title of the reminder.
     * @param reminderDate  The date of the reminder.
     * @param reminderTime  The time of the reminder.
     * @throws IllegalArgumentException If the user is null.
     */
    public void addReminder(User user, int reminderId, String reminderTitle, Date reminderDate, Time reminderTime) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        List<Reminder> reminders = user.getReminders();
        Reminder reminder = new Reminder(reminderId, reminderTitle, reminderDate, reminderTime);
        reminders.add(reminder);
        user.setReminders(reminders);
        user.updateUserInFile(); // Persist the changes
    }
}