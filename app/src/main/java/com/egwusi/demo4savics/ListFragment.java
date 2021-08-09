package com.egwusi.demo4savics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListFragment extends Fragment {
    private final String TAG = "yyy-ListFragment";
    RecyclerView recyclerView;
    List<UserListResponse> userListResponseData;
    private Activity activity;

    private Callbacks mCallbacks;

    public interface Callbacks{
        void onSingUp(SignUpResponse signUpResponse);
    }


    public static ListFragment newInstance(){
       /* Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);*/
        return new ListFragment();//fragment;
    }

    public ListFragment() {
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
        View v = inflater.inflate(R.layout.fragment_list,container, false);
        // init the EditText and Button
        recyclerView = v.findViewById(R.id.recyclerView);
        getUserListData(); // call a method in which we have implement our GET type web API
        return v;
    }
    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getUsersList(new Callback<List<UserListResponse>>() {
            @Override
            public void success(List<UserListResponse> userListResponses, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                userListResponseData = userListResponses;
                setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(activity, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(activity, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
