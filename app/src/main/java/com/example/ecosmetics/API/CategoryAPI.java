package com.example.ecosmetics.API;

import com.example.ecosmetics.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("users/categories")
    Call<List<Category>> getAllCategory();
}
