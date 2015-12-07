package sms.postpone.djory.postponesms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, int resource, List<Contact> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_contact, null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        Contact p = getItem(position);
        viewHolder.contactName.setText(p.getName());
        viewHolder.contactPhone.setText(p.getPhone());
        return v;
    }

    class ViewHolder {
        @Bind(R.id.contact_name) TextView contactName;
        @Bind(R.id.contact_phone) TextView contactPhone;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
