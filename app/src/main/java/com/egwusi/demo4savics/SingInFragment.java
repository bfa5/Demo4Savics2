package com.egwusi.demo4savics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;
import java.util.UUID;

import retrofit.Callback;
import retrofit.client.Response;

public class SingInFragment extends Fragment {
    private final String TAG = "yyy-SingInFragment";
    private SignUpResponse signUpResponsesData;
    private EditText email, password, name;
    private Button signUp;
    private Activity activity;

    public static SingInFragment newInstance(){
       /* Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);*/
        return new SingInFragment();//fragment;
    }

    public SingInFragment() {
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
        View v = inflater.inflate(R.layout.fragment_singin,container, false);
        // init the EditText and Button
        name = v.findViewById(R.id.username);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        signUp = v.findViewById(R.id.signUp);
        // implement setOnClickListener event on sign up Button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate the fields and call sign method to implement the api
                if (validate(name) && validate(email) && validate(password)) {
                    signUp();
                }
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

    private void signUp() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        Api.getClient().registration(name.getText().toString().trim(),
                email.getText().toString().trim(),
                password.getText().toString().trim(),
                "email", new Callback<SignUpResponse>() {
                    @Override
                    public void success(SignUpResponse signUpResponse, Response response) {
                        // in this method we will get the response from API
                        progressDialog.dismiss(); //dismiss progress dialog
                        signUpResponsesData = signUpResponse;
                        // display the message getting from web api
                        Toast.makeText(MainActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss(); //dismiss progress dialog

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
