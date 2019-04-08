package com.example.notesfixed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notesfixed.model.User;

public class SignUpFragment extends Fragment{
    FragmentActivity main;
    Toolbar tool;
    Button signUpButton;
    View rootView;
    TextView canLogin;
    EditText password1;
    EditText password2;
    EditText email;
    TextView wrong;

    Resources resources = new Resources();

    public SignUpFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
//        tool = rootView.findViewById(R.id.toolbar);
//        tool.setTitle("Toolbar");
        setHasOptionsMenu(true);
        signUpButton = rootView.findViewById(R.id.signUpButton);
        password1 = rootView.findViewById(R.id.editTextPass1);
        password2 = rootView.findViewById(R.id.editTextPass2);
        email = rootView.findViewById(R.id.editTextEmailLog);
        canLogin = rootView.findViewById(R.id.textViewYouCanLogin);
        wrong = rootView.findViewById(R.id.textViewWrongSignUp);
        wrong.setVisibility(View.INVISIBLE);
        canLogin.setVisibility(View.INVISIBLE);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password1.getText().toString().equals(password2.getText().toString())){
                    resources.registerUser(new User(1, email.getText().toString(), password2.getText().toString()));
                    canLogin.setVisibility(View.VISIBLE);
                    wrong.setVisibility(View.INVISIBLE);
                }else{
                    wrong.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu_signup, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                LoginFragment logFrag = new LoginFragment();
                FragmentTransaction transaction = main.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.frameLayout, logFrag);
                transaction.addToBackStack("Sign Up Fragment");
                transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }
}