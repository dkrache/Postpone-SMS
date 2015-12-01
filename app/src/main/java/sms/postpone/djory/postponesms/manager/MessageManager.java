package sms.postpone.djory.postponesms.manager;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import sms.postpone.djory.postponesms.dao.helper.MessageDao;
import sms.postpone.djory.postponesms.model.Message;
@Singleton
public class MessageManager {
    private MessageDao messageDao;
    private Context context;

    @Inject
    public MessageManager(Context context, MessageDao messageDao) {
        this.context = context;
        this.messageDao = messageDao;
    }

    public List<Message> getMessages() {
        return messageDao.queryForAll();
    }

    public void create(Message message) {
        messageDao.create(message);
    }


    public List<Message> getMessage(DateTime dateTime) {
        // TODO : Choisir entre dateTime -60s et DateTime +60
        return messageDao.queryForAll();
    }
}
