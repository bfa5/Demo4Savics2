package com.egwusi.demo4savics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListFragment extends Fragment {
    private static final String ARG_IP_ADRESSE = "ip_address";
    private final String TAG = "yyy-ListFragment";
    RecyclerView foodsRecyclerView;
    //List<UserListResponse> userListResponseData;
    List<Food> mFoodData;
    private Activity activity;
    private String apiServerIp;


    public static ListFragment newInstance(){
       /* Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);*/
        return new ListFragment();//fragment;
    }

    public static ListFragment newInstance(String ipAdress){
        Bundle args = new Bundle();
        args.putSerializable(ARG_IP_ADRESSE, ipAdress);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        activity = getActivity();
        apiServerIp = getArguments() != null ? getArguments().getString(ARG_IP_ADRESSE) : null;



    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        //activity = getActivity();
        //Log.d(TAG, "onCreateView / activity = " + activity);
        View v = inflater.inflate(R.layout.fragment_list,container, false);
        // init the EditText and Button
        foodsRecyclerView = v.findViewById(R.id.foodsRecyclerView);
        foodsRecyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        mFoodData = new ArrayList<Food>() {};
        getFoodListData(); // call a method in which we have implement our GET type web API
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        setFoodInRecyclerView();
    }

    private void setFoodInRecyclerView() {
        Log.d(TAG, "setFoodInRecyclerView");
        FoodsAdapter foodsAdapter = new FoodsAdapter(activity, mFoodData);
        foodsRecyclerView.setAdapter(foodsAdapter);
    }

    private void getFoodListData() {
        Log.d(TAG, "getFoodListData");
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient(apiServerIp).getFoodsList(new Callback<List<Food>>() {
            @Override
            public void success(List<Food> foodLists, Response response) {
                // in this method we will get the response from API
                Log.d(TAG, "getFoodsList / success/foodLists.size() = " + foodLists.size());

                progressDialog.dismiss(); //dismiss progress dialog
                mFoodData = foodLists;
                setFoodInRecyclerView();//setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.d(TAG, "getFoodsList / failure = " + error.toString());
                Toast.makeText(activity, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }


}
