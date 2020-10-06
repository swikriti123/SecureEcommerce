package com.example.ecosmetics.API;

import com.example.ecosmetics.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {

    @GET("users/skinproduct")
    Call<List<Product>>getAllProduct();
}
