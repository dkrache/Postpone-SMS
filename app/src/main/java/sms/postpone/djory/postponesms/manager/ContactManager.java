package sms.postpone.djory.postponesms.manager;

import java.util.Set;

import javax.inject.Inject;

import sms.postpone.djory.postponesms.dao.ContactDao;
import sms.postpone.djory.postponesms.model.Contact;

public class ContactManager {

    private ContactDao contactDao;

    @Inject
    public ContactManager(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void save(Set<Contact> contacts) {
        contactDao.save(contacts);
    }

}
