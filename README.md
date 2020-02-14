# Notes
This little application can be used to take down notes and store them in memory using an SQLiteDatabase from where they can be easily accessed. 

A note has a title, content and is stamped with the current date and time.

Notes has three screens:

<b>Screen 1: Notes List Screen</b>

<img src = "https://user-images.githubusercontent.com/60269503/74525403-085ff480-4f47-11ea-9d47-763346a18297.png" width = "200" height = "400"/>
This screen displays the notes created in a reverse chronological order where the latest note is displayed first. Each note has the title, content and the timestamp.

A FAB can be clicked on to create a new note, which takes the user to the Create Note Screen.

When any note is clicked, the user is redirected to the Note Details Screen.

<b>Screen 2: Create Note Screen</b>

<img src = "https://user-images.githubusercontent.com/60269503/74525422-157ce380-4f47-11ea-9ae8-695eff24786a.png" width = "200" height = "400"/>
This screen can be used to create a new note with title and content. Neither the title nor content can be blank. On clicking the save button, the note is saved to memory. The user is then redirected to the Note Details Screen.

<b>Screen 3: Note Details Screen</b>

<img src = "https://user-images.githubusercontent.com/60269503/74525414-0eee6c00-4f47-11ea-8848-51786e924c82.png" width = "200" height = "400"/>
This screen is launched either when the user creates a new note and saves it or on clicking on the note from the Notes List screen.

This is a static screen which displays the note title, note content and the time stamp when the note was created.

<b>Instructions for building</b>

<i>Android Studio</i>

Import the project from GitHub using File -> New -> Project from Version Control -> GitHub, fill the URL https://github.com/shrutipahuja/Notes.git

It will ask for a base directory, normally AndroidStudioProjects, you can change it to your preference.

After this the Notes app can be built.

(To create a new database of notes, simply change the database name and table name in the NotesDatabase class. Uninstalling and installing the application will also delete the database.)



