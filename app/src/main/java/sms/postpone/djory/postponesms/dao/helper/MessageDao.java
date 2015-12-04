package sms.postpone.djory.postponesms.dao.helper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import sms.postpone.djory.postponesms.model.Message;

public class MessageDao extends RuntimeExceptionDao<Message,Long> {

    public MessageDao(Dao<Message, Long> dao) {
        super(dao);
    }


}
