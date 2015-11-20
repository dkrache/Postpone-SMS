package sms.postpone.djory.postponesms.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sms.postpone.djory.postponesms.R;


public class EditTextFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Message");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View editTextFragment = inflater.inflate(R.layout.edittext_dialog_fragment, null);
        createProjectAlert.setView(editTextFragment)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), ((EditText) editTextFragment.findViewById(R.id.sms_text)).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
        return createProjectAlert.create();
    }
}