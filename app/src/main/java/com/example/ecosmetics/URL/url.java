package com.example.ecosmetics.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
//public static final String BASE_URL  = "http://127.0.0.1:3001/"; for unit testing
    public static final String BASE_URL  = "http://10.0.2.2:3001/";
   // public static final String BASE_URL  = "http://172.26.8.89:3001/";//for physical ip address of the laptop
    public static String token="Bearer ";
    public static String imagePath=BASE_URL+"uploads/";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
