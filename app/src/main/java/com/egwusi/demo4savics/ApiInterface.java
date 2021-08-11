package com.egwusi.demo4savics;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {

        // API's endpoints
        @GET("/retrofit/getuser.php")
        public void getFoodList(
                Callback<List<UserListResponse>> callback);

        @FormUrlEncoded // annotation used in POST type requests
        @POST("/retrofit/register.php")     // API's endpoints
        public void registration(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("password") String password,
                                 @Field("logintype") String logintype,
                                 Callback<SignUpResponse> callback);
        // API's endpoints
        @GET("/retrofit/getuser.php")
        public void getUsersList(
                Callback<List<UserListResponse>> callback);

// For POST request
        @FormUrlEncoded    // annotation that used with POST type request
        @POST("/demo/login.php") // specify the sub url for our base url
        public void login(
                @Field("user_email") String user_email,
                @Field("user_pass") String user_pass, Callback<SignUpResponse> callback);



// UserListResponse is POJO class to get the data from API, In above method we use List<UserListResponse> because the data in our API is starting from JSONArray and callback is used to get the response from api and it will set it in our POJO class


//user_email and user_pass are the post parameters and SignUpResponse is a POJO class which recieves the response of this API
// for GET request
/*

        @GET("/demo/countrylist.php") // specify the sub url for our base url
        public void getVideoList(Callback<List<CountryResponse>> callback);

*/
// CountryResponse is a POJO class which receives the response of this API

}
