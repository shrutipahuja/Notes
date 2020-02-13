package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetailsActivity extends AppCompatActivity {

    TextView titleDetailsTextView, contentDetailsTextView, dateDetailsTextView, timeDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);
        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();

        NotesDatabase notesDatabase = new NotesDatabase(this);
        Note note = notesDatabase.getNote(id);
        Toast.makeText(this, note.getTitle(), Toast.LENGTH_LONG).show();

        titleDetailsTextView = findViewById(R.id.titleDetailsTextView);
        contentDetailsTextView = findViewById(R.id.contentDetailsTextView);
        dateDetailsTextView = findViewById(R.id.dateDetailsTextView);
        timeDetailsTextView = findViewById(R.id.timeDetailsTextView);

        titleDetailsTextView.setText(note.getTitle());
        contentDetailsTextView.setText(note.getContent());
        dateDetailsTextView.setText(note.getDate());
        timeDetailsTextView.setText(note.getTime());


    }
}
