package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

/**
 * Screen 1 - List Notes Screen displaying list of notes
 */

public class MainActivity extends AppCompatActivity {
    Toolbar notesListToolbar;
    RecyclerView notesListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesListToolbar = findViewById(R.id.notesListToolbar);
        setSupportActionBar(notesListToolbar);

        notesListRecyclerView = findViewById(R.id.notesListRecyclerView);


    }
}
