package sms.postpone.djory.postponesms.event;

/**
 * Created by excilys on 01/12/15.
 */
public class TimePickerEvent {
    private int hour;
    private int minute;

    public TimePickerEvent(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
