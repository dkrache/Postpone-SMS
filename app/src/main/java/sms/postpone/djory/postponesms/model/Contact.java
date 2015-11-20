package sms.postpone.djory.postponesms.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by excilys on 01/11/15.
 */
@DatabaseTable(tableName = "contact")
public class Contact implements Comparable {
    @DatabaseField(generatedId = true) private Long id;
    @DatabaseField private String nom;
    @DatabaseField private String phone;

    public Contact() {
    }

    public Contact(String nom, String phone) {
        this.nom = nom;
        this.phone = phone;
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
    
}