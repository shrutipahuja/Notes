package com.example.notes;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Test Suite for MainActivity - Notes List
 */

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }


    @Test
    public void isActivityLaunched() {
        View recyclerView = mainActivity.findViewById(R.id.notesListRecyclerView);
        View floatingActionButton = mainActivity.findViewById(R.id.addNewNoteFAB);
        assertNotNull(recyclerView);
        assertNotNull(floatingActionButton);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}