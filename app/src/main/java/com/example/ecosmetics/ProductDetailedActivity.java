package com.example.ecosmetics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ecosmetics.URL.url;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailedActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    String product_name;
    String product_desc;
    int product_rate;
    CircleImageView product_img;
    String product_image;

    private Button btnaddcart;
    private TextView textViewName, textViewDesc, textViewRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        product_img= findViewById(R.id.productimg);
        textViewName = findViewById(R.id.proname);
        textViewDesc = findViewById(R.id.prodesc);
        textViewRate = findViewById(R.id.prorate);
        btnaddcart= findViewById(R.id.btnaddcart);

        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openaddcart = new Intent(ProductDetailedActivity.this,CartActivity.class);
                startActivity(openaddcart);
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            product_image=extras.getString("product_image");
            product_name = extras.getString("product_name");
            product_desc = extras.getString("product_desc");
            product_rate = extras.getInt("product_rate");
            textViewName.setText(product_name);
            textViewDesc.setText(product_desc);
            textViewRate.setText(String.valueOf(product_rate));
            String imgPath = url.BASE_URL + "uploads/" + product_image;
            try {
                URL url=new URL(imgPath);
                product_img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (Exception e) {
            }
        }

    }
}
