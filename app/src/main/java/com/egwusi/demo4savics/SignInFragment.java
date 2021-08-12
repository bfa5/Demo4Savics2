package com.egwusi.demo4savics;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SignInFragment extends Fragment {
    private final String TAG = "yyy-SignInFragment";
    //private SignUpResponse signUpResponsesData;
    private EditText email, password, name;
    private EditText ipAddress;
    private Button signIn;
    private Activity activity;



    public static SignInFragment newInstance(){
        return new SignInFragment();//fragment;
    }

    public SignInFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //activity = getActivity();
        //Log.d(TAG, "onCreateView / activity = " + activity);
        View v = inflater.inflate(R.layout.fragment_signin,container, false);
        // init the EditText and Button
        ipAddress = v.findViewById(R.id.username);
        /*email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);*/
        signIn = v.findViewById(R.id.signIn);
        // implement setOnClickListener event on sign up Button
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                // validate the fields and call sign method to implement the api
                /*if (validate(name) && validate(email) && validate(password)) {
                    signIn();
                }*/
            }
        });
        return v;
    }
    
    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void signIn() {
        if (!validate(ipAddress)) return;
        // display a progress dialog
        ListFragment listFragment = ListFragment.newInstance(ipAddress.getText().toString().trim());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
