package com.example.ecosmetics.bll;

import com.example.ecosmetics.API.LoginAPI;
import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.URL.url;
import com.example.ecosmetics.serverresponse.SignUpResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpBLL {
    String firstname;
    String lastname;
    String address;
    String email;
    String phoneno;
    String username;
    String password;
    //String userType = "User";
    boolean isSuccess = false;

    public SignUpBLL(String firstname, String lastname, String address, String email, String phoneno, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;
        this.username = username;
        this.password = password;
    }

    public boolean register() {

        User userRegModel = new User(firstname, lastname, address, email, phoneno, username, password);

       LoginAPI api = url.getInstance().create(LoginAPI.class);

        Call<SignUpResponse> voidCall = api.register(userRegModel);

        try {
            Response<SignUpResponse> voidResponse = voidCall.execute();
            if (voidResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess =false;

        }
        return isSuccess;

    }
}
