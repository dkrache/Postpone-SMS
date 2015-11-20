package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.DialogInterface.BUTTON_NEGATIVE;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        Toast.makeText(this.getContext(), savedInstanceState.getInt("year"), Toast.LENGTH_LONG).show();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        timePicker.setButton(BUTTON_NEGATIVE, null, (Message) null);
        // Create a new instance of TimePickerDialog and return it
        return timePicker;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        DialogFragment editTextFragment = new EditTextFragment();
        editTextFragment.show(getFragmentManager(), "timePicker");
    }
}