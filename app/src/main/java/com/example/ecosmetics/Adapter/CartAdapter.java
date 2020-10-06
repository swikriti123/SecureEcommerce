package com.example.ecosmetics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ecosmetics.Model.Product;
import com.example.ecosmetics.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<Product> productList;
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.activity_cart,parent,false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
    //holder.img_product.setImageResource(productList.get(position).getProductimg());
    holder.txt_productname.setText(productList.get(position).getProductname());
    holder.txt_productdesc.setText(productList.get(position).getProductdesc());
    holder.txt_productrate.setText(new StringBuilder("Rs").append(productList.get(position).getRate()));
    holder.txtquantity.setNumber(String.valueOf(productList.get(position).getQuantity()));
//        holder.txtquantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
//            @Override
//            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
//                Product cart= productList.get(position);
//                cart.quantity= newValue;
//                Common.cartRepository.updateCart(cart);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView txt_productname, txt_productdesc,txt_productrate;
        ElegantNumberButton txtquantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=(ImageView)itemView.findViewById(R.id.cartimgproduct);
            txt_productname=(TextView)itemView.findViewById(R.id.txtcproname);
            txt_productdesc=(TextView)itemView.findViewById(R.id.txtcprodesc);
            txt_productrate=(TextView)itemView.findViewById(R.id.txtcprate);
            txtquantity=(ElegantNumberButton) itemView.findViewById(R.id.txtquantity);

        }
    }
}
