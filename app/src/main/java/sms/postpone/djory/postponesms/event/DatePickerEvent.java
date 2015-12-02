package sms.postpone.djory.postponesms.event;

/**
 * Created by excilys on 01/12/15.
 */
public class DatePickerEvent {
    private int year;
    private int month;
    private int day;

    public DatePickerEvent(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
