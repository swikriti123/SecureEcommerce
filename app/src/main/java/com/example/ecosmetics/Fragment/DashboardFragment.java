package com.example.ecosmetics.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecosmetics.API.CategoryAPI;
import com.example.ecosmetics.API.ProductAPI;
import com.example.ecosmetics.Adapter.CategoryAdapter;
import com.example.ecosmetics.Adapter.ProductAdapter;
import com.example.ecosmetics.DashboardActivity;
import com.example.ecosmetics.Model.Category;
import com.example.ecosmetics.Model.Product;
import com.example.ecosmetics.R;
import com.example.ecosmetics.URL.url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    Context context;
    String TAG="DashboardFragment";

    public DashboardFragment() {
        // Required empty public constructor
    }
    private RecyclerView procat_recyclerview, rv_product;
    androidx.appcompat.widget.SearchView searchView;
    private List<Category> lstcat= new ArrayList<>();
    private List<Product> lstpro =new ArrayList<>();
    private List<Product> SearchProductList = new ArrayList<Product>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context =getContext();

        procat_recyclerview=view.findViewById(R.id.procat_recyclerview);
        procat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        searchView=view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    SearchProductList = lstpro;

                }
                else{
                    SearchProductList = new ArrayList<>();
                    for(Product product:lstpro){
                        if(product.getProductname().contains(newText)){
                            SearchProductList.add(product);
                        }
                    }
                }
                ProductAdapter adapter = new ProductAdapter(context,SearchProductList);
                rv_product.setAdapter(adapter);
                return false;
            }

        });


        rv_product=view.findViewById(R.id.pro_recyclerview);
        rv_product.setLayoutManager(new GridLayoutManager(getContext(),3));

        CategoryAPI categoryAPI= url.getInstance().create(CategoryAPI.class);
        Call<List<Category>> categoryCall = categoryAPI.getAllCategory();

        categoryCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                Toast.makeText(context,"category fetched", Toast.LENGTH_SHORT).show();
                lstcat = response.body();
                CategoryAdapter ca = new CategoryAdapter(context,lstcat);
                procat_recyclerview.setAdapter(ca);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(context, "fail"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });

        ProductAPI productAPI = url.getInstance().create(ProductAPI.class);
        Call<List<Product>> productCall = productAPI.getAllProduct();
        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                //Toast.makeText(context,"Product fetched", Toast.LENGTH_SHORT).show();
                lstpro = response.body();
                SearchProductList = lstpro;
                ProductAdapter pa = new ProductAdapter(context,lstpro);
                rv_product.setAdapter(pa);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, "failed to fetched" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return view;
    }

}
