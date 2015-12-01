package sms.postpone.djory.postponesms.event;

/**
 * Created by excilys on 01/12/15.
 */
public class SMSEvent {
    private String sms;

    public SMSEvent(String sms){
        super();
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
