package Notification;

import User_Management.User;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ReminderManager {

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