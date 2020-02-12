package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Screen 2 - Create Note Screen allowing to create a new note
 */
public class CreateNoteActivity extends AppCompatActivity {

    EditText noteTitleEditText, noteContentEditText;
    Calendar calendar;
    String currentDate, currentTime;
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

        //get current year, month, date
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = padding(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + padding(calendar.get(Calendar.MINUTE));

        //Log.i("DATE AND TIME", currentDate + "   " + currentTime);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button pressed", "Save Button");
            }
        });


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

}
