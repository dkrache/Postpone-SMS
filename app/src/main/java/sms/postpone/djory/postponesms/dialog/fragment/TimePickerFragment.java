package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.event.TimePickerEvent;

import static android.content.DialogInterface.BUTTON_NEGATIVE;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    @Inject EventBus bus;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        PostponeApplication.app().getPostponeComponent().inject(this);
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        timePicker.setButton(BUTTON_NEGATIVE, null, (Message) null);
        // Create a new instance of TimePickerDialog and return it
        return timePicker;
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        DialogFragment editTextFragment = new EditTextFragment();
        bus.post(new TimePickerEvent(hour, minute));
        editTextFragment.show(getFragmentManager(), "timePicker");
    }
}