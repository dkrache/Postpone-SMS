package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.event.SMSEvent;


public class EditTextFragment extends DialogFragment {
    @Bind(R.id.sms_text) EditText smsText;
    @Inject EventBus bus;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View editTextFragment = inflater.inflate(R.layout.edittext_dialog_fragment, null);
        ButterKnife.bind(this, editTextFragment);
        PostponeApplication.app().getPostponeComponent().inject(this);

        final AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Message");
        createProjectAlert.setView(editTextFragment)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        bus.post(new SMSEvent(smsText.getText().toString()));
                    }
                });
        return createProjectAlert.create();
    }
}