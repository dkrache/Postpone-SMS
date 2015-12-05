package sms.postpone.djory.postponesms;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.activity.MainActivity;
import sms.postpone.djory.postponesms.activity.SplashScreenActivity;
import sms.postpone.djory.postponesms.dao.MessageDao;
import sms.postpone.djory.postponesms.dao.ContactDao;
import sms.postpone.djory.postponesms.dao.helper.DatabaseHelper;
import sms.postpone.djory.postponesms.dialog.fragment.DatePickerFragment;
import sms.postpone.djory.postponesms.dialog.fragment.EditTextFragment;
import sms.postpone.djory.postponesms.dialog.fragment.TimePickerFragment;

/**
 * Created by excilys on 19/11/15.
 */
public class PostponeApplication extends Application {
    private PostponeComponent postponeComponent;
    private static PostponeApplication app;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        app = this;
        databaseHelper = new DatabaseHelper(this);
        postponeComponent = DaggerPostponeApplication_PostponeComponent.builder().postponeModule(new PostponeModule()).build();
    }

    public static PostponeApplication app() {
        return app;
    }

    public PostponeComponent getPostponeComponent(){
        return postponeComponent;
    }


    @Component(modules = {PostponeModule.class})
    @Singleton
    public interface PostponeComponent {
        void inject(MainActivity mainActivity);
        void inject(SplashScreenActivity splashScreenActivity);

        //Fragment
        void inject(DatePickerFragment datePickerFragment);
        void inject(EditTextFragment editTextFragment);
        void inject(TimePickerFragment datePickerFragment);
    }

    @Module
    public class PostponeModule{

        @Provides
        @Singleton
        public Context providesRootContext(){
            return PostponeApplication.this;
        }

        @Provides
        @Singleton
        public MessageDao providesMessageDao(){
            return databaseHelper.getMessageDao();
        }

        @Provides
        @Singleton
        public ContactDao providesContactDao(){
            return databaseHelper.getContactDao();
        }

        @Provides
        @Singleton
        public EventBus providesEventBus(){
            return  EventBus.getDefault();
        }
    }


}
