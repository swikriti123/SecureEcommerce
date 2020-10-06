package com.example.ecosmetics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecosmetics.API.LoginAPI;
import com.example.ecosmetics.Fragment.CartFragment;
import com.example.ecosmetics.Fragment.DashboardFragment;
import com.example.ecosmetics.Model.Category;
import com.example.ecosmetics.Model.Product;
import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.URL.url;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    private FrameLayout frameLayout;

    private static final String TAG = "DashboardActivity";

    private TextView hloginusername;
    private TextView hloginemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        context=this;
        getSupportActionBar().hide();


        mDrawerLayout= findViewById(R.id.drawyerlayout);
        Toolbar toolbar = findViewById(R.id.app_cart);
        NavigationView nv = findViewById(R.id.navigationview);

        View headerView= LayoutInflater.from(context).inflate(R.layout.sideheader,null);
        nv.addHeaderView(headerView);
        hloginusername = headerView.findViewById(R.id.loginusername);
        hloginemail = headerView.findViewById(R.id.loginemail);

        LoginAPI api = url.getInstance().create(LoginAPI.class);
        Call<User> getuserdetails = api.getUserDetails(url.token);

        getuserdetails.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){
                    hloginusername.setText(response.body().getFirstname());
                    hloginemail.setText(response.body().getEmail());
                }
                else{
                    Toast.makeText(DashboardActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        nv.setNavigationItemSelectedListener(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashboardActivity.this, CartActivity.class);
                startActivity(i);
            }
        });
        mToggle= new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frameLayout=findViewById(R.id.framelayout);
        setFragment(new DashboardFragment());
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();

        switch (menuItem.getItemId()){
            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new DashboardFragment()).commit();

                break;
            case R.id.Cart:
                Intent opencart =new Intent(DashboardActivity.this, CartActivity.class);
                startActivity(opencart);
                break;
            case  R.id.Userprofile:
                Intent openeditprofile =new Intent(DashboardActivity.this, EditProfileActivity.class);
                startActivity(openeditprofile);
                break;
            case  R.id.Maps:
                Intent openmaps =new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(openmaps);
                break;
            case  R.id.Logout:
                logout();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        if(url.token!="Bearer"){
            url.token="Bearer";
        }
        Intent gologout= new Intent(DashboardActivity.this,LoginActivity.class);
        startActivity(gologout);
    }

}
