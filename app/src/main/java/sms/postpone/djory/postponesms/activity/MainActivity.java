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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.nav_view) NavigationView navigationView;
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            List<Contact> contacts = ContactUtil.getContacts(this);
            Log.d("test", contacts.toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    public void getSMSCreator() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        Message message = new Message(null, sms, new DateTime(year, month+1, day, hour, minute));
        Toast.makeText(this,message.toString(),Toast.LENGTH_LONG).show();
    }
}
