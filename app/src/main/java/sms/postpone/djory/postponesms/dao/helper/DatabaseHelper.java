package sms.postpone.djory.postponesms.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import sms.postpone.djory.postponesms.model.Contact;
import sms.postpone.djory.postponesms.model.Message;
import timber.log.Timber;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "sms.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DatabaseHelper.class.getCanonicalName();
    private MessageDao messageDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.d(TAG, "Tables created");
            TableUtils.createTable(connectionSource, Contact.class);
            TableUtils.createTable(connectionSource, Message.class);
        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Message.class, true);
            TableUtils.dropTable(connectionSource, Contact.class, true);
            onCreate(db);
        } catch (SQLException e) {
            Log.e(TAG, "Impossible to drop database", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public MessageDao getMessageDao() {
        if (messageDao == null) {
            messageDao = new MessageDao(createDao(Message.class));
        }
        return messageDao;
    }

    /**
     * Create a Dao of a given model class.
     *
     * @param clazz  The class that the code will be operating on.
     * @param <T>    The class that the code will be operating on.
     * @return A Dao operating on the given class.
     */
    private <T> Dao<T, Long> createDao(Class<T> clazz) {
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            Timber.e(e, "Error while creating %s dao", clazz.getSimpleName());
            throw new RuntimeException(e);
        }
    }

}