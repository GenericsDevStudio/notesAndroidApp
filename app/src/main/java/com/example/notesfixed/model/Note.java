package com.example.notesfixed.model;

public class Note {

    private String title;
    private String content;
    private String lastChange;
    private String noteid;

    public Note(String title, String content, String lastchange, String noteid){
        this.title = title; this.content = content; this.lastChange = lastchange; this.noteid = noteid;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLastChange() {
        return lastChange;
    }

    public String getNoteid() {
        return noteid;
    }

}
