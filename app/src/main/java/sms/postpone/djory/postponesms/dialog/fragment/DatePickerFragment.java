package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.text.style.BulletSpan;
import android.widget.DatePicker;

import org.joda.time.DateTime;

import java.util.Calendar;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.event.DatePickerEvent;

import static android.app.DatePickerDialog.*;

public class DatePickerFragment extends DialogFragment
        implements OnDateSetListener {
    @Inject EventBus bus;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        PostponeApplication.app().getPostponeComponent().inject(this);
        final DateTime dateTime = DateTime.now();
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear()-1;
        int day = dateTime.getDayOfMonth();
        // Create a new instance of TimePickerDialog and return it
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);
        datePicker.setButton(BUTTON_NEGATIVE,null,(Message)null);
        return datePicker;
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        DialogFragment timeFragment = new TimePickerFragment();
        bus.post(new DatePickerEvent (year,month,day));
        timeFragment.show(getFragmentManager(), "timePicker");
    }
}