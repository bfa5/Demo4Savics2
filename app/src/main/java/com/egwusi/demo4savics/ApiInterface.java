package com.egwusi.demo4savics;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {
// For POST request
        @FormUrlEncoded    // annotation that used with POST type request
        @POST("/demo/login.php") // specify the sub url for our base url
        public void login(
                @Field("user_email") String user_email,
                @Field("user_pass") String user_pass, Callback<SignUpResponse> callback);

//user_email and user_pass are the post parameters and SignUpResponse is a POJO class which recieves the response of this API
// for GET request

        @GET("/demo/countrylist.php") // specify the sub url for our base url
        public void getVideoList(Callback<List<CountryResponse>> callback);

// CountryResponse is a POJO class which receives the response of this API

}
