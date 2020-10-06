package com.example.ecosmetics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ecosmetics.Fragment.CartFragment;
import com.example.ecosmetics.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private FrameLayout cframelayout;
    RecyclerView.LayoutManager layoutManager;
    public static List<Product> lstproduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cframelayout=findViewById(R.id.cartframelayout);
        setFragment(new CartFragment());



        lstproduct=new ArrayList<>();
        //lstproduct.add(new Product("cleanser","Cleanser",560,R.drawable.cleanser));
    }

    private void setFragment(CartFragment cartFragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(cframelayout.getId(),cartFragment);
        fragmentTransaction.commit();
    }


}
