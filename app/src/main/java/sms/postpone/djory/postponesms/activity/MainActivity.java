package sms.postpone.djory.postponesms.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.dialog.fragment.DatePickerFragment;
import sms.postpone.djory.postponesms.event.DatePickerEvent;
import sms.postpone.djory.postponesms.event.SMSEvent;
import sms.postpone.djory.postponesms.event.TimePickerEvent;
import sms.postpone.djory.postponesms.manager.MessageManager;
import sms.postpone.djory.postponesms.model.Contact;
import sms.postpone.djory.postponesms.model.Message;
import sms.postpone.djory.postponesms.util.ContactUtil;
import sms.postpone.djory.postponesms.util.ScheduleUtils;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Inject Context context;
    @Inject MessageManager messageManager;
    @Inject EventBus bus;

    // TODO : Clean this
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PostponeApplication.app().getPostponeComponent().inject(this);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
        super.onPause();
    }


    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    public void getSMSCreator() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onEventBackgroundThread(Message message) {
        messageManager.create(message);
    }

    public void onEventMainThread(Message message) {
        Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void onEventMainThread(TimePickerEvent event) {
        this.hour = event.getHour();
        this.minute = event.getMinute();
    }

    public void onEventMainThread(DatePickerEvent event) {
        this.year = event.getYear();
        this.month = event.getMonth();
        this.day = event.getDay();
    }

    public void onEventMainThread(SMSEvent event) {
        this.sms = event.getSms();
        Message message = new Message(event.getContact(), sms, new DateTime(year, month + 1, day, hour, minute));
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show();
        messageManager.create(message);
        ScheduleUtils.schedule(this, message);
    }
}
