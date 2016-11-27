package ideall.gabrielrunescape.com.br.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Classe para controlar o fluxo de infomrações entre o banco de dados e a aplicação.
 */
public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE = "ideall.sdb";

    private static final String CREATE_PROJECT = "CREATE TABLE IF NOT EXISTS Project (\n" +
            "  ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "  Name VARCHAR(32) NOT NULL,\n" +
            "  Author INTEGER NOT NULL,\n" +
            "  Description TEXT NULL,\n" +
            "  Created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "  CONSTRAINT fk_Project_Contacts\n" +
            "    FOREIGN KEY (Author)\n" +
            "    REFERENCES Contact (ID)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE CASCADE\n" +
            ");\n";

    private static final String CREATE_CONTACT = "CREATE TABLE IF NOT EXISTS Contact (\n" +
            "  ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "  Name VARCHAR(16) NOT NULL,\n" +
            "  Email VARCHAR(32) NULL,\n" +
            "  Created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP\n" +
            ");";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public CustomSQLiteOpenHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        db.execSQL(CREATE_PROJECT);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Project;");
        db.execSQL("DROP TABLE IF EXISTS Contact;");
        onCreate(db);
    }
}