package sms.postpone.djory.postponesms.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.Callable;

import sms.postpone.djory.postponesms.model.Message;

public class MessageDao extends RuntimeExceptionDao<Message, Long> {

    public MessageDao(Dao<Message, Long> dao) {
        super(dao);
    }

    private static final long TIME_LIMIT_MS = 120 * 1000;

    public List<Message> getMessagesByDate(final DateTime date) {
        return callBatchTasks(new Callable<List<Message>>() {
            @Override
            public List<Message> call() throws Exception {
                return queryBuilder().where().between(Message.DATE, date.getMillis() - TIME_LIMIT_MS, date.getMillis() + TIME_LIMIT_MS).query();
            }
        });
    }

}
