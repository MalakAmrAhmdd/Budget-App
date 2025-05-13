package Notification;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Notification {
    private int notificationId;
    private String content;
    private LocalDate sentDate;
    private LocalTime sentTime;

    /**
     * Default constructor for Notification.
     */
    public Notification(int notificationId, String content, LocalDate sentDate, LocalTime sentTime) {
        this.notificationId = notificationId;
        this.content = content;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
    }

    /**
     * Gets the notification ID.
     * @return notificationId
     */
    public int getNotificationId() {
        return notificationId;
    }
    /**
     * Gets the content of the notification.
     * @return content
     */
    public String getContent() {
        return content;
    }
    /**
     * Gets the date the notification was sent.
     * @return sentDate
     */
    public LocalDate getSentDate() {
        return sentDate;
    }
    /**
     * Gets the time the notification was sent.
     * @return sentTime
     */
    public LocalTime getSentTime() {
        return sentTime;
    }

}
