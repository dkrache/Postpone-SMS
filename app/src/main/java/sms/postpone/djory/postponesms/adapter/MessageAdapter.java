package sms.postpone.djory.postponesms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import sms.postpone.djory.postponesms.R;
import sms.postpone.djory.postponesms.model.Message;

public class MessageAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<Message> messages;
    private LayoutInflater inflater;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("hh:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yy");
    public MessageAdapter(Context context, int resource, List<Message> messages) {
        inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        Message message = (Message) getItem(position);
        if (v == null) {
            v = inflater.inflate(R.layout.item_message, parent, false);
            viewHolder = new ViewHolder(v, message);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.name.setText(message.getContact().getName());
        viewHolder.phone.setText(message.getContact().getPhone());
        viewHolder.time.setText(TIME_FORMATTER.print(message.getDate().getMillis()));
        viewHolder.texte.setText(message.getMessage());
        return v;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.header_message, parent, false);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name

        holder.date.setText(DATE_FORMATTER.print(messages.get(position).getDate().getMillis()));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return messages.get(position).getDate().withTime(0,0,0,0).getMillis();
    }


    class ViewHolder {
        @Bind(R.id.header) RelativeLayout header;
        @Bind(R.id.name) TextView name;
        @Bind(R.id.phone) TextView phone;
        @Bind(R.id.time) TextView time;
        @Bind(R.id.delete) ImageView delete;
        @Bind(R.id.message) TextView texte;
        private Message message;
        private View view;
        public ViewHolder(View view, Message message) {
            ButterKnife.bind(this, view);
            this.view = view;
            this.message = message;
        }

        @OnClick(R.id.header)
        public synchronized void switchVisibility() {
            if (texte.getVisibility() == View.VISIBLE) {
                texte.setVisibility(View.GONE);
            } else {
                texte.setVisibility(View.VISIBLE);
            }
        }

        @OnClick(R.id.delete)
        public void delete() {
            messages.remove(message);
        }
    }

    class HeaderViewHolder {
        @Bind(R.id.date) TextView date;

        public HeaderViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
