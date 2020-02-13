package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

/**
 * Screen 1 - List Notes Screen displaying list of notes
 */

public class MainActivity extends AppCompatActivity {
//    Toolbar notesListToolbar;
    RecyclerView notesListRecyclerView;
    NotesAdapter noteAdapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesDatabase notesDatabase = new NotesDatabase(this);
        notes = notesDatabase.getNotes();
        notesListRecyclerView = findViewById(R.id.notesListRecyclerView);
        notesListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NotesAdapter(this, notes );
        notesListRecyclerView.setAdapter(noteAdapter);

    }

    /**
     * onClick method for FAB on Notes List Screen
     * @param view View
     */

    public void createNote(View view) {
        Intent showCreateNoteScreen = new Intent(this, CreateNoteActivity.class);
        startActivity(showCreateNoteScreen);
    }
}
