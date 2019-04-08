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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.notesfixed.Resources.notes;
import com.example.notesfixed.model.Note;

import java.util.ArrayList;
import java.util.Calendar;


public class NotesListFragment extends Fragment {
    TextView titleResult, contentResult, dateResult;
    String  newTitle, newContent;
    View rootView;
    FragmentActivity main;
    MenuInflater menuinf;
    static Note noteToRedact = null;
    static int idNoteToRedact = 0;
    public NotesListFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        setHasOptionsMenu(true);
        menuinf = main.getMenuInflater();
        if(newTitle != null && notes != null) {
            for(int i = 0; i < notes.size(); i++) {
                newTitle = notes.get(i).getTitle();
                newContent = notes.get(i).getContent();
                notes.add(new Note(newTitle, newContent, Calendar.getInstance().getTime().toString(), String.valueOf(Resources.notes.size())));
            }
        }
        ListView list = rootView.findViewById(R.id.list);
        titleResult = rootView.findViewById(R.id.title);
        contentResult = rootView.findViewById(R.id.content);
        dateResult = rootView.findViewById(R.id.date);
        titleResult.setVisibility(View.INVISIBLE);
        contentResult.setVisibility(View.INVISIBLE);
        dateResult.setVisibility(View.INVISIBLE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                noteToRedact = new Note(notes.get(position).getTitle().toString(), notes.get(position).getContent().toString(), Calendar.getInstance().getTime().toString(), String.valueOf(Resources.notes.size()));
                idNoteToRedact = position;
                NoteAddFragment noteAddFragment = new NoteAddFragment();
                FragmentTransaction transaction = main.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.frameLayout, noteAddFragment);
                transaction.commit();
            }
        });
        MyAdapter ma = new MyAdapter(notes, this.getLayoutInflater());
        list.setAdapter(ma);


        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:
        {
            noteToRedact = null;
            NoteAddFragment noteAddFragment = new NoteAddFragment();
            FragmentTransaction transaction = main.getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.frameLayout, noteAddFragment);
            transaction.commit();
            return false;
        }
        case R.id.search:

            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }

}

class MyAdapter extends BaseAdapter {
    class ViewHolder{
        TextView title, content, date;
    }
    ArrayList<Note> notes;
    LayoutInflater lif;
    MyAdapter(ArrayList<Note> notes, LayoutInflater lif){
        this.notes =notes; this.lif = lif;
    }
    public int getCount(){
        if(notes != null) {
            return notes.size();
        }else{
            return 0;
        }
    }
    public long getItemId(int index){
        return 0;
    }
    public Object getItem (int index){
        if(notes != null) {
            return notes.get(index);
        }else{
            return null;
        }
    }
    public View getView(int pos, View v, ViewGroup vg){
        Note n = (Note)getItem(pos);
        ViewHolder ci;
        if(v == null){
            v = lif.inflate(R.layout.fragment_notes_list, null);
            ci = new ViewHolder();
            ci.title = v.findViewById(R.id.title);
            ci.content = v.findViewById(R.id.content);
            ci.date = v.findViewById(R.id.date);
        }else{
            ci = (ViewHolder)v.getTag();
        }
        ci.title.setText(n.getTitle());
        ci.content.setText(n.getContent());
        ci.date.setText(n.getLastChange().toString());
        v.setTag(ci);
        return v;
    }
}
