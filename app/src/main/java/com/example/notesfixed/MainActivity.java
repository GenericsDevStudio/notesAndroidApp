package com.example.notesfixed;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.notesfixed.model.Note;

import java.util.Calendar;


// TODO: FIX BUG WITH BACK BUTTON ON NotesListFragment, SET MIN SDK VERSION 23
// LOGIN: lololo PASSWORD: lel


public class MainActivity extends AppCompatActivity {
    //    User devtest = new User(1, "undefned4@gmail.com", "123");
    Note devNote = new Note("devnote", "Just a test.", Calendar.getInstance().getTime().toString(), String.valueOf(1213));

    Resources resources = new Resources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        resources.registerUser(devtest);
//        Resources.notes.add(devNote);

        LoginFragment logFrag = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.frameLayout, logFrag);
        transaction.commit();
    }
}
