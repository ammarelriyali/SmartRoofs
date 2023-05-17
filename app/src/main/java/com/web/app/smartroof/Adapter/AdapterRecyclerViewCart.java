package com.web.app.smartroof.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.R;

import java.util.ArrayList;

public class AdapterRecyclerViewCart extends RecyclerView.Adapter<AdapterRecyclerViewCart.MyHolder> {

    public AdapterRecyclerViewCart(){}
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyckerview_cart,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class MyHolder extends RecyclerView.ViewHolder{
         public MyHolder(@NonNull View itemView) {
             super(itemView);
         }
     }
}
