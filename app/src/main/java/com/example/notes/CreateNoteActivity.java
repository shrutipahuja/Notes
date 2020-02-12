package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Screen 2 - Create Note Screen allowing to create a new note
 */
public class CreateNoteActivity extends AppCompatActivity {

    EditText noteTitleEditText, noteContentEditText;
    Calendar calendar;
    String currentDate, currentTime, noteTitle, noteContent;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
//        noteTitleEditText.setSelection(1);
        noteContentEditText = findViewById(R.id.noteContentEditText);
//        noteContentEditText.setSelection(1);
        saveButton = findViewById(R.id.saveButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = noteTitleEditText.getText().toString();
        noteContent = noteContentEditText.getText().toString();

        //get current year, month, date
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = padding(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + padding(calendar.get(Calendar.MINUTE));

        //Log.i("DATE AND TIME", currentDate + "   " + currentTime);
    }

    /**
     * Adds a zero before the minute or hour if less than 10
     * @param time int
     */
    private String padding(int time) {
        if(time < 10)
            return "0" + time;
        else
            return String.valueOf(time);
    }

    /**
     * Saves note created to NoteDatabase
     * @param view View
     */

    public void saveNote(View view) {
        if(noteTitle!=null && noteContent!=null) {
            Note note = new Note(noteTitle, noteContent, currentDate, currentTime);
            NotesDatabase notesDatabase = new NotesDatabase(this);
            notesDatabase.addNoteToDatabase(note);
            Log.i("Button pressed", "Save Button");
            saveButton.setEnabled(true);
        } else if (noteTitle==null) {
            saveButton.setEnabled(false);
            Toast.makeText(this, "Note Title cannot be empty", Toast.LENGTH_LONG).show();
        } else if (noteContent==null) {
            saveButton.setEnabled(false);
            Toast.makeText(this, "Note Title cannot be empty", Toast.LENGTH_LONG).show();
        }
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
