package com.example.ecosmetics.API;

import com.example.ecosmetics.Model.LoginResponse;
import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

    public interface LoginAPI {
    //for login
    @FormUrlEncoded
    @POST("/users/login")
    Call<SignUpResponse>checkUser(@Field("username") String username,@Field("password") String password);

    @POST("/users/signup")
    Call<SignUpResponse>register(@Body User regUser);

    @GET("users/me")
        Call<User> getUserDetails(@Header("authorization")String token);


}
