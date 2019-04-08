package com.example.notesfixed;


import com.example.notesfixed.model.Note;
import com.example.notesfixed.model.User;

import java.util.ArrayList;


public class Resources {


    static User CURRENT_USER = new User(123, "test_user", "qwerty");
    public static ArrayList<Note> notes = new ArrayList<>();
    public void registerUser(final User u) {
        CURRENT_USER = u;
    }
    public static void addNewNote(Note n){
        notes.add(n);
    }
    public static void redactNote(int position, Note noteToReplace){
        notes.remove(position);
        notes.add(position, noteToReplace);
    }
    public static void deleteNote(int position){
        notes.remove(position);
    }
    public boolean checkUser(String email, String password) {
        if(email.equals(CURRENT_USER.getEmail()) && password.equals(CURRENT_USER.getPassword())){
            return true;
        }else{
            return false;
        }
    }
}

