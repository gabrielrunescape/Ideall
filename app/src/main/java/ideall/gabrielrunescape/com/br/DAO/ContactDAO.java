package ideall.gabrielrunescape.com.br.DAO;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import ideall.gabrielrunescape.com.br.objects.Contact;
import ideall.gabrielrunescape.com.br.controller.CustomSQLiteOpenHelper;

/**
 * Created by gabriel on 27/11/16.
 */

public class ContactDAO {
    private SQLiteDatabase database;
    private CustomSQLiteOpenHelper sqLiteOpenHelper;
    private String[] columns = { "ID", "Name", "Email" };

    public ContactDAO(Context context) {
        sqLiteOpenHelper = new CustomSQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteOpenHelper.close();
    }

    public Contact create(Contact cont) {
        ContentValues values = new ContentValues();
        values.put("Name", cont.getName());
        values.put("Email", cont.getEmail());
        values.put("Created", "CURRENT_TIMESTAMP()");

        long id = database.insert("Contact", null, values);

        Cursor cursor = database.query("Contact", columns, "ID = " + id, null, null, null, null);
        cursor.moveToFirst();

        Contact project = new Contact();
        project.setID(cursor.getLong(0));
        project.setName(cursor.getString(1));
        project.setEmail(cursor.getString(2));

        cursor.close();

        return project;
    }

    public void update(Contact cont) {
        long id = cont.getID();

        ContentValues values = new ContentValues();
        values.put("Name", cont.getName());
        values.put("Email", cont.getEmail());
        values.put("Created", "CURRENT_TIMESTAMP()");

        database.update("Contact", values, "ID = " + id, null);
    }

    public void delete(Contact cont) {
        long id = cont.getID();

        database.delete("Contact", "ID = " + id, null);
    }

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<Contact>();

        Cursor cursor = database.query("Contact", columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Contact p = new Contact();
            p.setID(cursor.getLong(0));
            p.setName(cursor.getString(1));
            p.setEmail(cursor.getString(2));

            contacts.add(p);
            cursor.moveToNext();
        }

        return contacts;
    }
}
