package com.example.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class to create and upgrade SQLiteDatabase
 */

public class NotesDatabase extends SQLiteOpenHelper {

    //Database query constants
    private static final String CREATE_TABLE = "CREATE TABLE";
    private static final String INT_PRIMARY_INT = "INT PRIMARY INT";
    private static final String TEXT = "TEXT";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";

    //Initital Database Version, Database Name, Database Table
    private static int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "NotesDB";
    private static final String DATABASE_TABLE = "NotesTable";

    //Column Names for the Database Table NotesTable
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    /**
     * Parametrized Constructor
     * @param context Context
     */
    NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database
     * @param sqLiteDatabase SQLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE TABLE TABLE_NAME(id INT PRIMARY KEY, title TEXT, content TEXT, data TEXT, time TEXT)
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" +
                        KEY_ID + " INT PRIMARY KEY" + ", " +
                        KEY_TITLE + " TEXT, " +
                        KEY_CONTENT  + " TEXT, " +
                        KEY_DATE + " TEXT, " +
                        KEY_TIME + " TEXT)";

        sqLiteDatabase.execSQL(query);

    }

    /**
     *  Upgrades database on new version found
     * @param sqLiteDatabase SQLiteDatabase
     * @param oldVersion int
     * @param newVersion int
     */

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);

    }
}
