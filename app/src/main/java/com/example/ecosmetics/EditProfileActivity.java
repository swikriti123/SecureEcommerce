package com.example.ecosmetics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecosmetics.API.LoginAPI;
import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.URL.url;
import static com.example.ecosmetics.URL.url.token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    TextView fname, lname,address,phno,email,username;
    private FrameLayout editprofileframe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fname = findViewById(R.id.firstname);
        // lname=findViewById(R.id.lstname);
        address=findViewById(R.id.uadd);
        phno=findViewById(R.id.uphone);
        email=findViewById(R.id.uemail);
        username=findViewById(R.id.user);
        editprofileframe=findViewById(R.id.editprofileframelayout);
        loadCurrentUser();
    }
    private void loadCurrentUser() {
        //user token access here from URL
        LoginAPI loginAPI = url.getInstance().create(LoginAPI.class);
        Call<User> userCall= loginAPI.getUserDetails(token);

        userCall.enqueue(new Callback<User>(){


            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String firstName = response.body().getFirstname();
                String lastName= response.body().getLastname();
                String uaddress= response.body().getAddress();
                String uphone= response.body().getPhoneno();
                String uemail= response.body().getEmail();
                String susername=response.body().getUsername();
                fname.setText(firstName + lastName);
                address.setText(uaddress);
                phno.setText(uphone);
                email.setText(uemail);
                username.setText(susername);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(EditProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
