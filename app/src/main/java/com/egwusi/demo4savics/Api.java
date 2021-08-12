package com.egwusi.demo4savics;

import retrofit.RestAdapter;

public class Api {
    public static ApiInterface getClient(String ip) {
        String endPoint = "http://"+ip+":8001";
        //base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(endPoint) //Set the Root URL
                //.setEndpoint("http://localhost:8001")
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

}
