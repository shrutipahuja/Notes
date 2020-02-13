package com.example.notes;

/**
 * POJO Class for inserting and retreiving of data variables from/to database
 */

public class Note {

    private long id;
    private String title;
    private String content;
    private String date;
    private String time;

    public Note() {
        //empty constructor
    }

    /**
     * Parametrized constructor for initialising Note
     * @param title String
     * @param content String
     * @param date String
     * @param time String
     */
    public Note(String title, String content, String date, String time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    /**
     * Parametrized constrcutor to initialise using id
     * @param id long
     * @param title String
     * @param content String
     * @param date String
     * @param time String
     */

    public Note(long id, String title, String content, String date, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
