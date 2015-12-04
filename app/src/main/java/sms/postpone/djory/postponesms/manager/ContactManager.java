package sms.postpone.djory.postponesms.manager;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import sms.postpone.djory.postponesms.dao.helper.ContactDao;
import sms.postpone.djory.postponesms.model.Contact;

public class ContactManager {

    private ContactDao contactDao;

    @Inject
    public ContactManager(ContactDao contactDao){
        this.contactDao = contactDao;
    }

    public void save(List<Contact> contacts) {
    }

}
