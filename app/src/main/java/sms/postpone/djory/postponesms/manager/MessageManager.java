package sms.postpone.djory.postponesms.manager;

import android.content.Context;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.dao.MessageDao;
import sms.postpone.djory.postponesms.event.MessageEvent;
import sms.postpone.djory.postponesms.model.Message;

@Singleton
public class MessageManager {
    private MessageDao messageDao;
    private Context context;
    private EventBus bus;
    @Inject
    public MessageManager(Context context, MessageDao messageDao, EventBus bus) {
        this.context = context;
        this.messageDao = messageDao;
        this.bus = bus;
    }

    public void getMessages() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                bus.post(new MessageEvent(messageDao.queryForAll()));
            }
        }).start();
    }

    public void create(Message message) {
        messageDao.create(message);
    }


    public List<Message> getMessage(DateTime dateTime) {
        // TODO : Choisir entre dateTime -60s et DateTime +60
        return messageDao.getMessagesByDate(dateTime);
    }
}
