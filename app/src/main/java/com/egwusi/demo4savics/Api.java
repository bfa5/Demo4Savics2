package com.egwusi.demo4savics;

import retrofit.RestAdapter;

public class Api {
    public static ApiInterface getClient() {

        // change your base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:8001/meals") //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

    /*public static ApiInterface getClient() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://healthyblackmen.org") //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        //ApiInterface api = adapter.create(ApiInterface.class);
        return adapter.create(ApiInterface.class);//api;
    }*/
}
