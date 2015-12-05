package sms.postpone.djory.postponesms.event;

import sms.postpone.djory.postponesms.model.Contact;

/**
 * Created by excilys on 01/12/15.
 */
public class SMSEvent {
    private String sms;
    private Contact contact;

    public SMSEvent(Contact contact, String sms) {
        super();
        this.contact = contact;
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
