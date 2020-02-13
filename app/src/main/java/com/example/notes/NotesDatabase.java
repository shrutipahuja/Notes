package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    private static final String DATABASE_NAME = "NotesDBMS";
    private static final String DATABASE_TABLE = "NotesTableDBMS";

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

    public long addNoteToDatabase(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENT, note.getContent());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        long ID = sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
        Log.d("Inserted",String.valueOf(ID));
        return ID;
    }

    public Note getNote(long id) {
        //select * from DATABASE_NAME where id =
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        return new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));

    }

    public List<Note> getNotes() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Note> listOfNotes = new ArrayList<>();
        //select * from DATABASE_NAME

        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));
                listOfNotes.add(note);
            }while(cursor.moveToNext());
        }
                return listOfNotes;

    }
}
