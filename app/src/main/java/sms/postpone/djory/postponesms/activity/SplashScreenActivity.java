package sms.postpone.djory.postponesms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.event.LoadedContactEvent;
import sms.postpone.djory.postponesms.manager.ContactManager;
import sms.postpone.djory.postponesms.model.Contact;
import sms.postpone.djory.postponesms.util.ContactUtil;

public class SplashScreenActivity extends Activity {

    @Inject ContactManager contactManager;
    @Inject EventBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        PostponeApplication.app().getPostponeComponent().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<Contact> contacts = ContactUtil.getContacts(SplashScreenActivity.this);
                contactManager.save(contacts);
                ContactUtil.setContactHolder(contacts);
                bus.post(new LoadedContactEvent());
            }
        }).start();
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    public void onEventMainThread(LoadedContactEvent event){
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
