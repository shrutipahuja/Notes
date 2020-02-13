package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Screen 2 - Create Note Screen allowing to create a new note
 */
public class CreateNoteActivity extends AppCompatActivity {

    private EditText noteTitleEditText, noteContentEditText;
    private String currentDate, currentTime;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //get current year, month, date, time and format them
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.US);
        currentTime = simpleTimeFormat.format(calendar.getTime());
        currentDate = simpleDateFormat.format(calendar.getTime());
    }


    /**
     * Saves note created to NoteDatabase
     *
     * @param view View
     */
    public void saveNote(View view) {
        String noteTitle = noteTitleEditText.getText().toString();
        String noteContent = noteContentEditText.getText().toString();

        if (noteTitle.isEmpty()) {
            Toast.makeText(this, "Title cannot be blank", Toast.LENGTH_LONG).show();
        } else if (noteContent.isEmpty()) {
            Toast.makeText(this, "Content cannot be blank", Toast.LENGTH_LONG).show();
        } else {
            Note note = new Note(noteTitle, noteContent, currentDate, currentTime);
            NotesDatabase notesDatabase = new NotesDatabase(this);
            id = notesDatabase.addNoteToDatabase(note);
            goToDetailActivity();
        }
    }

    private void goToDetailActivity() {
        Intent goToMainIntent = new Intent(this, NoteDetailsActivity.class);
        goToMainIntent.putExtra("ID", id);
        startActivity(goToMainIntent);
    }


}
