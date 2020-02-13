package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Screen 3 - Note Details Screen allowing to display the details of a note
 */

public class NoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);

        NotesDatabase notesDatabase = new NotesDatabase(this);
        Note note = notesDatabase.getNote(id);

        TextView titleDetailsTextView = findViewById(R.id.titleDetailsTextView);
        TextView contentDetailsTextView = findViewById(R.id.contentDetailsTextView);
        TextView dateDetailsTextView = findViewById(R.id.dateDetailsTextView);
        TextView timeDetailsTextView = findViewById(R.id.timeDetailsTextView);

        //setting the text values of title, content, date and time
        titleDetailsTextView.setText(note.getTitle());
        contentDetailsTextView.setText(note.getContent());
        dateDetailsTextView.setText(note.getDate());
        timeDetailsTextView.setText(note.getTime());

    }

    /**
     * Redirects to Screen1 on pressing back
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
