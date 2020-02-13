package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Screen 1 - List Notes Screen displaying list of notes
 */

public class MainActivity extends AppCompatActivity {
//    Toolbar notesListToolbar;
    RecyclerView notesListRecyclerView;
    NotesAdapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesDatabase notesDatabase = new NotesDatabase(this);
        notes = notesDatabase.getNotes();
        notesListRecyclerView = findViewById(R.id.notesListRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        notesListRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotesAdapter(this, notes );
        notesListRecyclerView.setAdapter(adapter);

    }

    public void createNote(View view) {
        Intent showCreateNoteScreen = new Intent(this, CreateNoteActivity.class);
        startActivity(showCreateNoteScreen);
    }
}
