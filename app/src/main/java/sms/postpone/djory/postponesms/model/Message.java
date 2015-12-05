package sms.postpone.djory.postponesms.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

/**
 * Created by excilys on 01/11/15.
 */
@DatabaseTable(tableName = "Message")
public class Message {
    public static final String MESSAGE = "message";
    public static final String CONTACT = "contact";
    public static final String DATE = "date";
    @DatabaseField(generatedId = true) private Long id;
    @DatabaseField(columnName = MESSAGE) private String message;
    @DatabaseField(columnName = DATE) private DateTime date;
    @DatabaseField(columnName = CONTACT, foreign = true) private Contact contact;

    @SuppressWarnings("unused") // used by Ormlite
    private Message() {
    }

    public Message(Contact contact, String message, DateTime date) {
        this.contact = contact;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", contact=" + contact +
                ", date=" + date +
                '}';
    }
}
