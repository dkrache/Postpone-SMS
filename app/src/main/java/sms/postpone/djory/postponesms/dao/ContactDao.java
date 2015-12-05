package sms.postpone.djory.postponesms.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import sms.postpone.djory.postponesms.model.Contact;
import sms.postpone.djory.postponesms.model.Message;

public class ContactDao extends RuntimeExceptionDao<Contact, Long> {

    public ContactDao(Dao<Contact, Long> dao) {
        super(dao);
    }


    public void save(final Set<Contact> contacts) {
        callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (Contact contact : contacts) {
                    createOrUpdate(contact);
                }
                return null;
            }
        });
    }

}
