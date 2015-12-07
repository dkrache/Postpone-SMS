package sms.postpone.djory.postponesms.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import sms.postpone.djory.postponesms.activity.MainActivity;
import sms.postpone.djory.postponesms.manager.MessageManager;
import sms.postpone.djory.postponesms.model.Message;

/**
 * Created by ws on 07/02/15.
 */
public class TaskReceiver extends BroadcastReceiver {

    @Inject MessageManager messageManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        DateTime dateTime = DateTime.now();
        List<Message> messages = messageManager.getMessage(dateTime);
        for (Message message : messages) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(message.getContact().getPhone(), null, message.getMessage(), null, null);
            createNotification(context, "Message send to : " + message.getContact().getName());
        }
    }


    private final void createNotification(Context context, String message) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(context, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context,
                1, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Message")
                .setContentText(message)
                .setContentIntent(pendingIntent);

        mNotification.notify(1, builder.build());
    }
}
