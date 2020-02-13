package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Screen 2 - Create Note Screen allowing to create a new note
 */
public class CreateNoteActivity extends AppCompatActivity {

    EditText noteTitleEditText, noteContentEditText;
    Calendar calendar;
    String currentDate, currentTime, noteTitle, noteContent;
    Button saveButton;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);



        saveButton = findViewById(R.id.saveButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //get current year, month, date, time and format them
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.US);
        currentTime = simpleTimeFormat.format(calendar.getTime());
        currentDate = simpleDateFormat.format(calendar.getTime());
    }


    /**
     * Saves note created to NoteDatabase
     * @param view View
     */
    public void saveNote(View view) {
        noteTitle = noteTitleEditText.getText().toString();
        noteContent = noteContentEditText.getText().toString();

        if (noteTitle.isEmpty() ) {
            Toast.makeText(this, "Title cannot be blank", Toast.LENGTH_LONG).show();
        } else if (noteContent.isEmpty()) {
            Toast.makeText(this, "Content cannot be blank", Toast.LENGTH_LONG).show();
        } else {
            Note note = new Note(noteTitle, noteContent, currentDate, currentTime);
            NotesDatabase notesDatabase = new NotesDatabase(this);
            id = notesDatabase.addNoteToDatabase(note);
            Note noteCreated = notesDatabase.getNote(id);
            goToDetailActivity();
        }
    }

    private void goToDetailActivity() {
        Intent goToMainIntent = new Intent(this, NoteDetailsActivity.class);
        goToMainIntent.putExtra("ID", id);
        startActivity(goToMainIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
