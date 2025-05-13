package Notification;

    import java.sql.Time;
    import java.util.Date;

    /**
     * Represents a reminder with a title, date, and time.
     */
    public class Reminder {
        private int reminderId;
        private String reminderTitle;
        private Date reminderDate;
        private Time reminderTime;

        /**
         * Constructs a new Reminder.
         *
         * @param reminderId    The unique ID of the reminder.
         * @param reminderTitle The title of the reminder.
         * @param reminderDate  The date of the reminder.
         * @param reminderTime  The time of the reminder.
         */
        public Reminder(int reminderId, String reminderTitle, Date reminderDate, Time reminderTime) {
            this.reminderId = reminderId;
            this.reminderTitle = reminderTitle;
            this.reminderDate = reminderDate;
            this.reminderTime = reminderTime;
        }

        /**
         * Gets the unique ID of the reminder.
         *
         * @return The reminder ID.
         */
        public int getReminderId() {
            return reminderId;
        }

        /**
         * Gets the title of the reminder.
         *
         * @return The reminder title.
         */
        public String getReminderTitle() {
            return reminderTitle;
        }

        /**
         * Gets the date of the reminder.
         *
         * @return The reminder date.
         */
        public Date getReminderDate() {
            return reminderDate;
        }

        /**
         * Gets the time of the reminder.
         *
         * @return The reminder time.
         */
        public Time getReminderTime() {
            return reminderTime;
        }
    }