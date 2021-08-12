package com.egwusi.demo4savics;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {

        // API's endpoints
        @GET("/meals")
        public void getFoodsList(
                Callback<List<Food>> callback);

}
