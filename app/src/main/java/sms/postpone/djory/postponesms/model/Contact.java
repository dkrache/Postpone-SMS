package sms.postpone.djory.postponesms.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by excilys on 01/11/15.
 */
@DatabaseTable(tableName = "contact")
public class Contact implements Comparable {

    public static final String NAME = "name";
    public static final String PHONE = "phone";

    @DatabaseField(columnName = NAME)
    private String nom;
    @DatabaseField(columnName = PHONE, unique = true, id = true)
    private String phone;

    public Contact() {
    }

    public Contact(String nom, String phone) {
        this.nom = nom;
        // TODO : need tweak here
        this.phone = phone.replaceAll(" ", "").replace("+33","0");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Contact) {
            return nom.compareTo(((Contact) o).getNom());
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return !(phone != null ? !phone.equals(contact.phone) : contact.phone != null);

    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }

    public String toString() {
        return nom;
    }

}
