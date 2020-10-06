package com.example.ecosmetics.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecosmetics.Model.Category;
import com.example.ecosmetics.R;
import com.example.ecosmetics.URL.url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    Context mContext;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category>categoryList){
        this.mContext =mContext;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category cat = categoryList.get(position);
       //holder.category_img.setImageResource(cat.getImage());

        String imgPath = url.BASE_URL + "uploads/" + cat.getImage();
        StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.category_img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
        }
        holder.category_name.setText(cat.getCategory());
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        CircleImageView category_img;
        TextView category_name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            category_img=itemView.findViewById(R.id.cat_image);
            category_name=itemView.findViewById(R.id.cat_name);
        }
    }
}
