package ideall.gabrielrunescape.com.br.DAO;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import ideall.gabrielrunescape.com.br.objects.Project;
import ideall.gabrielrunescape.com.br.controller.CustomSQLiteOpenHelper;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Objeto para armazenamento dos projetos antes de transmitir ao banco de dados através da
 * classe CustomSQLiteOpenHelper.
 */
public class ProjectDAO {
    private SQLiteDatabase database;
    private CustomSQLiteOpenHelper sqLiteOpenHelper;
    private String[] columns = { "ID", "Name", "Author" };

    public ProjectDAO(Context context) {
        sqLiteOpenHelper = new CustomSQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteOpenHelper.close();
    }

    public Project create(Project proj) {
        ContentValues values = new ContentValues();
        values.put("Name", proj.getName());
        values.put("Author", proj.getAuthor());

        long id = database.insert("Project", null, values);

        Cursor cursor = database.query("Project", columns, "ID = " + id, null, null, null, null);
        cursor.moveToFirst();

        Project project = new Project();
        project.setID(cursor.getLong(0));
        project.setName(cursor.getString(1));
        project.setAuthor(cursor.getString(2));

        cursor.close();

        return project;
    }

    public void update(Project proj) {
        long id = proj.getID();

        ContentValues values = new ContentValues();
        values.put("Name", proj.getName());
        values.put("Author", proj.getAuthor());

        database.update("Project", values, "ID = " + id, null);
    }

    public void delete(Project proj) {
        long id = proj.getID();

        database.delete("Project", "ID = " + id, null);
    }

    public List<Project> getAll() {
        List<Project> projects = new ArrayList<Project>();

        Cursor cursor = database.query("Project", columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Project p = new Project();
            p.setID(cursor.getLong(0));
            p.setName(cursor.getString(1));
            p.setAuthor(cursor.getString(2));

            projects.add(p);
            cursor.moveToNext();
        }

        return projects;
    }
}