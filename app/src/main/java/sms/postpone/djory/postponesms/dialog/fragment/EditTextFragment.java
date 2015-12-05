package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import sms.postpone.djory.postponesms.PostponeApplication;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.adapter.ContactAdapter;
import sms.postpone.djory.postponesms.event.SMSEvent;
import sms.postpone.djory.postponesms.model.Contact;
import sms.postpone.djory.postponesms.util.ContactUtil;

import static android.R.layout.simple_dropdown_item_1line;


public class EditTextFragment extends DialogFragment {
    @Bind(R.id.sms_text) EditText smsText;
    @Bind(R.id.contact_search) AutoCompleteTextView autoCompleteTextView;
    @Inject EventBus bus;

    private Contact contact;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View editTextFragment = inflater.inflate(R.layout.edittext_dialog_fragment, null);
        ButterKnife.bind(this, editTextFragment);
        PostponeApplication.app().getPostponeComponent().inject(this);

        loadContact();

        final AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Message");
        createProjectAlert.setView(editTextFragment)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        bus.post(new SMSEvent(contact, smsText.getText().toString()));
                    }
                });
        return createProjectAlert.create();
    }

    public void loadContact() {
        ContactAdapter adapter = new ContactAdapter(this.getContext(),
                simple_dropdown_item_1line, new ArrayList<>(ContactUtil.getContactHolder()));
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                contact = (Contact) adapterView.getItemAtPosition(position);
            }
        });
    }

}