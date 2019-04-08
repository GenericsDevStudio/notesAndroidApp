package com.example.notesfixed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesfixed.model.Note;

import java.util.Calendar;


public class NoteAddFragment extends Fragment {
    MenuItem deleteBtn;
    FragmentActivity main;
    View rootView;
    EditText newTitle;
    EditText newContent;
    Context c;
    Boolean redact = false;
    public NoteAddFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_note_add, container, false);
        setHasOptionsMenu(true);
        newTitle = rootView.findViewById(R.id.Head);
        newContent = rootView.findViewById(R.id.Note);
        if(NotesListFragment.noteToRedact != null) {
            newTitle.setText(NotesListFragment.noteToRedact.getTitle());
            newContent.setText(NotesListFragment.noteToRedact.getContent());
            redact = true;
        }else{
            newTitle.getText().clear();
            newContent.getText().clear();
            redact = false;
        }
        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_add_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
        deleteBtn = menu.findItem(R.id.delete);
        if(NotesListFragment.noteToRedact != null){
            deleteBtn.setVisible(true);
        }else{
            deleteBtn.setVisible(false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                //
                if(newContent.getText().toString().isEmpty() && newTitle.getText().toString().isEmpty()){
                    Toast.makeText(c, "You cannot save a blank note", Toast.LENGTH_SHORT).show();
                    break;
                }else if(newContent.getText().toString().isEmpty() || newTitle.getText().toString().isEmpty()) {
                    Toast.makeText(c, "Title or note content is missing", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    if(redact){
                        Resources.redactNote(NotesListFragment.idNoteToRedact,
                                new Note(newTitle.getText().toString(), newContent.getText().toString(), Calendar.getInstance().getTime().toString(), String.valueOf(Resources.notes.size())));
                        NotesListFragment notes = new NotesListFragment();
                        FragmentTransaction transaction = main.getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        transaction.replace(R.id.frameLayout, notes);
                        transaction.commit();
                        break;
                    }else{
                        Resources.addNewNote
                                (new Note(newTitle.getText().toString(), newContent.getText().toString(), Calendar.getInstance().getTime().toString(), String.valueOf(Resources.notes.size())));
                        NotesListFragment notes = new NotesListFragment();
                        FragmentTransaction transaction = main.getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        transaction.replace(R.id.frameLayout, notes);
                        transaction.commit();
                        break;
                    }
                }
            case R.id.backAdd:
                NotesListFragment notes2 = new NotesListFragment();
                FragmentTransaction transaction2 = main.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.replace(R.id.frameLayout, notes2);
                transaction2.commit();
                break;
            case R.id.delete:
                Resources.deleteNote(NotesListFragment.idNoteToRedact);
                NotesListFragment notes3 = new NotesListFragment();
                FragmentTransaction transaction3 = main.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction3.replace(R.id.frameLayout, notes3);
                transaction3.commit();
                break;
        }   return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
        c = context;
    }
}
