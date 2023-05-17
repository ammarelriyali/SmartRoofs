package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.web.app.smartroof.Adapter.AdapterRecyclerViewCart;
import com.web.app.smartroof.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerView =findViewById(R.id.recyclerview_cart);
        AdapterRecyclerViewCart adapterRecyclerViewCart= new AdapterRecyclerViewCart();
        recyclerView.setAdapter(adapterRecyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}