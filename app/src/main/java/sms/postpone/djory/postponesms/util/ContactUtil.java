package sms.postpone.djory.postponesms.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.LinkedList;
import java.util.List;

import sms.postpone.djory.postponesms.model.Contact;

import static java.util.Collections.sort;

/**
 * Created by ws on 14/02/15.
 */
public final class ContactUtil {
    private ContactUtil() {

    }

    public static List<Contact> getContacts(Context context) {
        List<Contact> contacts = new LinkedList<Contact>();

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String id = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;
        String hasPhoneNumberStr = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri phoneContentURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String phoneContactID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Loop for every contact in the phone
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(cursor.getColumnIndex(id));
            String name = cursor.getString(cursor.getColumnIndex(displayName));
            int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(hasPhoneNumberStr)));
            // Query and loop for every phone number of the contact
            Cursor phoneCursor = contentResolver.query(phoneContentURI, null, phoneContactID + " = ?", new String[]{contact_id}, null);
            while (phoneCursor.moveToNext()) {
                contacts.add(new Contact(name, phoneCursor.getString(phoneCursor.getColumnIndex(number))));
            }
            phoneCursor.close();
        }

        sort(contacts);
        return contacts;
    }
}

