package com.example.notes;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Test Suite for CreateNoteActivity
 */
public class CreateNoteActivityTest {

    @Rule
    public ActivityTestRule<CreateNoteActivity> createNoteActivityActivityTestRule = new ActivityTestRule<>(CreateNoteActivity.class);

    private CreateNoteActivity createNoteActivity = null;
    Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NoteDetailsActivity.class.getName(),
            null, false);

    @Before
    public void setUp() throws Exception {
        createNoteActivity = createNoteActivityActivityTestRule.getActivity();
    }

    @Test
    public void isActivityLaunched() {
        View createNoteHeading = createNoteActivity.findViewById(R.id.createNoteHeading);
        View noteTitleTextView = createNoteActivity.findViewById(R.id.noteTitleTextView);
        View noteTitleEditText = createNoteActivity.findViewById(R.id.noteTitleEditText);
        View noteContentTextView = createNoteActivity.findViewById(R.id.noteContentTextView);
        View noteContentEditText = createNoteActivity.findViewById(R.id.noteContentEditText);
        View saveButton = createNoteActivity.findViewById(R.id.saveButton);
        assertNotNull(createNoteHeading);
        assertNotNull(noteTitleTextView);
        assertNotNull(noteTitleEditText);
        assertNotNull(noteContentTextView);
        assertNotNull(noteContentEditText);
        assertNotNull(saveButton);
    }

    @Test
    public void isDetailsActivityNotLaunchedOnSaveButtonClickWithNullValues() {
        assertNotNull(createNoteActivity.findViewById(R.id.saveButton));
        onView(withId(R.id.noteTitleEditText)).perform(typeText(""));
        onView(withId(R.id.noteContentEditText)).perform(typeText(""));
        onView(withId(R.id.saveButton)).perform(click());
        Activity noteDetailsActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(noteDetailsActivity);
    }

    @Test
    public void isDetailsActivityLaunchedOnSaveButtonClickWithNotNullValues() {
        assertNotNull(createNoteActivity.findViewById(R.id.saveButton));
        onView(withId(R.id.noteTitleEditText)).perform(typeText("TitleX"));
        onView(withId(R.id.noteContentEditText)).perform(typeText("ContentX"));
        onView(withId(R.id.saveButton)).perform(click());
        Activity noteDetailsActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(noteDetailsActivity);
        noteDetailsActivity.finish();
    }

    @Test
    public void isToastMessageDisplayedOnBlankTitleField() {
        onView(withId(R.id.noteTitleEditText)).perform(typeText(""));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withText(R.string.title_blank)).inRoot(withDecorView(not(createNoteActivityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void isToastMessageDisplayedOnBlankContentField() {
        onView(withId(R.id.noteTitleEditText)).perform(typeText("Title X"));
        onView(withId(R.id.noteContentEditText)).perform(typeText(""));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withText(R.string.content_blank)).inRoot(withDecorView(not(createNoteActivityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        createNoteActivity = null;
    }
}