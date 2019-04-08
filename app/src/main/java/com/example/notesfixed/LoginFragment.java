package com.example.notesfixed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;


public class LoginFragment extends Fragment{
    View rootView;
    Button logIn;
    TextView signUp;
    TextView wrong;
    EditText email;
    EditText password;
    FragmentActivity main;

    Resources resources = new Resources();

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        logIn = rootView.findViewById(R.id.logInButton);
        signUp = rootView.findViewById(R.id.signUpTextView);
        wrong = rootView.findViewById(R.id.textViewWrong);
        email = rootView.findViewById(R.id.editTextEmailLog);
        password = rootView.findViewById(R.id.editTextPassword);
        wrong.setVisibility(View.INVISIBLE);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resources.checkUser(email.getText().toString(), password.getText().toString())){
                    NotesListFragment notes = new NotesListFragment();
                    FragmentTransaction transaction = main.getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    transaction.replace(R.id.frameLayout, notes);
                    transaction.addToBackStack("Login Fragment");
                    transaction.commit();
                }else{
                    wrong.setVisibility(View.VISIBLE);
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFrag = new SignUpFragment();
                FragmentTransaction transaction = main.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.frameLayout, signUpFrag);
                transaction.addToBackStack("Login Fragment");
                transaction.commit();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }
}