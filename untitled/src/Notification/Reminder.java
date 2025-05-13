package Notification;

import java.sql.Time;
import java.util.Date;

public class Reminder {
    private int reminderId;
    private String reminderTitle;
    private Date reminderDate;
    private Time reminderTime;

    public Reminder(int reminderId, String reminderTitle, Date reminderDate, Time reminderTime) {
        this.reminderId = reminderId;
        this.reminderTitle = reminderTitle;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
    }

    // Getters
    public int getReminderId() {
        return reminderId;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public Time getReminderTime() {
        return reminderTime;
    }
}