package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterRecyclerViewCategory;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewCycleHome;
import com.web.app.smartroof.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.MyLinearLayoutManager;

public class CategoryActivity extends AppCompatActivity {
    Api api;
    ProgressBar progressBar;
    ArrayList<Category> firstList = new ArrayList<>();
    RecyclerView recyclerView;
    String id;
    TextView textView;
    int clickedType,pageAllYear=0,pageSummer=0,pageWinter=0,pageHome=0;
    AdapterRecyclerViewCategory adapterRecyclerViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        textView=findViewById(R.id.header_title_sub_cat);
        textView.setText(getIntent().getStringExtra(getString(R.string.Key_name_Category)));
        recyclerView=findViewById(R.id.recyclerview_category);
        progressBar= findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        api= RetrofitBuilder.getRetrofitInstance().create(Api.class);
        id = getIntent().getStringExtra(getString(R.string.Key_id_Category));
        getCategoryFrist("","");
        clickedType=1;

    }

    public void all(View view) {
        if(clickedType!=1){
            progressBar.setVisibility(View.VISIBLE);
            getCategoryFrist("","");
            clickedType=1;
            setZero();
        }
    }


    public void summer(View view) {
        if(clickedType!=2){
            progressBar.setVisibility(View.VISIBLE);
            getCategoryFrist("","summer");
            clickedType=2;
            setZero();
        }

    }

    public void winter(View view) {
        if(clickedType!=3){
            progressBar.setVisibility(View.VISIBLE);
            getCategoryFrist("","winter");
            clickedType=3;
            setZero();
        }
    }

    public void allYear(View view) {
        if(clickedType!=4){
            progressBar.setVisibility(View.VISIBLE);
            getCategoryFrist("","alltheyear");
            clickedType=4;
            setZero();
        }
    }

    public void goHome(View view) {
        finish();
    }


    public void getMore(View view) {
        switch (clickedType){
            case 1:
                pageHome++;
                getCategory(firstList,String.valueOf(pageHome),"");
            case 2:
                pageSummer++;
                getCategory(firstList,String.valueOf(pageSummer),"summer");

            case 3:
                pageWinter++;
                getCategory(firstList,String.valueOf(pageWinter),"winter");
                break;
            case 4:
                pageAllYear++;
                getCategory(firstList,String.valueOf(pageAllYear),"alltheyear");


        }
    }

    void getCategoryFrist(String page,String type){
        Call<ArrayList<Category>> call = api.getSubCategoryHome(id,new HashMap<String, String>(){{put("page",page);}},
                new HashMap<String, String>(){{put("type",type);}});
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if(response.body()==null){
                    progressBar.setVisibility(View.GONE);

                }else {
                progressBar.setVisibility(View.GONE);
                firstList=response.body();
                if(firstList.size()==10){
                    firstList.add(null);
                }
                adapterRecyclerViewCategory= new AdapterRecyclerViewCategory(firstList);
                recyclerView.setAdapter(adapterRecyclerViewCategory);
                recyclerView.setLayoutManager(new MyLinearLayoutManager(getBaseContext()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CategoryActivity.this, "check your Network", Toast.LENGTH_SHORT).show();

            }
        });
    }


    void getCategory(ArrayList firstList ,String page,String type){
        Call<ArrayList<Category>> call = api.getSubCategoryHome(id,new HashMap<String, String>(){{put("page",page);}},
                new HashMap<String, String>(){{put("type",type);}});
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if(response.body()==null){
                    progressBar.setVisibility(View.GONE);

                }else {
                    progressBar.setVisibility(View.GONE);
                    ArrayList<Category> list;
                    list = response.body();
                    if (list.size() == 10) {
                        list.add(null);
                    }
                    firstList.remove(firstList.size() - 1);
                    firstList.addAll(list);
                    adapterRecyclerViewCategory.notifyDataSetChanged(firstList);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CategoryActivity.this, "check your Network", Toast.LENGTH_SHORT).show();

            }
        });
    }
    void setZero(){
        pageSummer=pageAllYear=pageHome=pageWinter=0;

    }

    public void GoArticle(View view) {
        Category category=firstList.get((Integer) view.getTag());
        Intent intent = new Intent(this,ArticleSubCatActivity.class);
        intent.putExtra(getString(R.string.Key_Article_sub_cat_title),category.getTitle());
        intent.putExtra(getString(R.string.Key_Article_sub_cat),category.get_id());
        startActivity(intent);
    }
}