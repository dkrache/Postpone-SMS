package sms.postpone.djory.postponesms.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import sms.postpone.djory.postponesms.model.Message;
import sms.postpone.djory.postponesms.receiver.TaskReceiver;


/**
 * Created by ws on 14/02/15.
 */
public class ScheduleUtils {


    public static void schedule(final View v, final Message message) {
        Toast.makeText(v.getContext(), "Enregistré ", Toast.LENGTH_SHORT).show();
        Intent intentAlarm = new Intent(v.getContext(), TaskReceiver.class);
        AlarmManager alarmManager = (AlarmManager) v.getContext().getSystemService(Context.ALARM_SERVICE);
        Random generator = new Random();
        alarmManager.set(AlarmManager.RTC_WAKEUP, message.getDate().getMillis(), PendingIntent.getBroadcast(v.getContext(), generator.nextInt(), intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public static void schedule(final Context context, final List<Message> messages) {
        Intent intentAlarm = new Intent(context, TaskReceiver.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        for (Message message : messages) {
            Toast.makeText(context, "Enregistré : " + message.getDate().getMillis(), Toast.LENGTH_SHORT).show();
            Random generator = new Random();
            alarmManager.set(AlarmManager.RTC_WAKEUP, message.getDate().getMillis(), PendingIntent.getBroadcast(context, generator.nextInt(), intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        }
    }

}
