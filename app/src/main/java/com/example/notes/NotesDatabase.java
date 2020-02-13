package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create and upgrade SQLiteDatabase
 */

class NotesDatabase extends SQLiteOpenHelper {

    //Initial Database Version, Database Name, Database Table
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "NotesDatabase";
    private static final String DATABASE_TABLE_NAME = "NotesTableDB";

    //Column Names for the Database Table NotesTable
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    /**
     * Parametrized Constructor
     *
     * @param context Context
     */
    NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database
     *
     * @param sqLiteDatabase SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE TABLE TABLE_NAME(id INT PRIMARY KEY, title TEXT, content TEXT, data TEXT, time TEXT)
        String query = "CREATE TABLE " + DATABASE_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY" + ", " +
                KEY_TITLE + " TEXT, " +
                KEY_CONTENT + " TEXT, " +
                KEY_DATE + " TEXT, " +
                KEY_TIME + " TEXT" + " )";

        sqLiteDatabase.execSQL(query);

    }

    /**
     * Upgrades database on new version found
     *
     * @param sqLiteDatabase SQLiteDatabase
     * @param oldVersion     int
     * @param newVersion     int
     */

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    /**
     * Adds a new note entry to Notes Database
     *
     * @param note Note
     * @return long id
     */

    long addNoteToDatabase(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENT, note.getContent());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        return sqLiteDatabase.insert(DATABASE_TABLE_NAME, null, contentValues);
    }

    /**
     * Obtain the note from the database
     *
     * @param id long
     * @return Note
     */

    Note getNote(long id) {
        //select * from DATABASE_NAME where id =
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] query = new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME};
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_NAME, query,
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new Note(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
    }

    /**
     * Obtain the entire list of notes
     *
     * @return List<Note> listOfNotes
     */

    public List<Note> getNotes() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Note> listOfNotes = new ArrayList<>();
        //select * from DATABASE_NAME

        String query = "SELECT * FROM " + DATABASE_TABLE_NAME + " ORDER BY " + KEY_ID + " DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));
                listOfNotes.add(note);
            } while (cursor.moveToNext());
        }
        return listOfNotes;
    }
}
