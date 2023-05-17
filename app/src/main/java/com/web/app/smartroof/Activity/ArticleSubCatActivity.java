package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterArticleSubCat;
import com.web.app.smartroof.R;
import utils.RealmDB;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.MyLinearLayoutManager;

public class ArticleSubCatActivity extends AppCompatActivity {
    Api api;
    TextView textView;
    String id;
    ArrayList<Category> firstList = new ArrayList<>();
    ProgressBar progressBar;
    RecyclerView recyclerView;
    AdapterArticleSubCat adapterArticleSubCat;
    private int page,index;
    long mLastClickTime;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_sub_cat);
        progressBar = findViewById(R.id.prog_article_sub_cat);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.rec_article_sub_cat);
        textView = findViewById(R.id.header_title_article_sub_cat);
        textView.append(getIntent().getStringExtra(getString(R.string.Key_Article_sub_cat_title)));
        id = getIntent().getStringExtra(getString(R.string.Key_Article_sub_cat));

        page = 0;
        index=-1;
        mLastClickTime=1;

        api = RetrofitBuilder.getRetrofitInstance().create(Api.class);
        Realm.init(this);
        realm= Realm.getDefaultInstance();
        getCategoryFrist("0");


    }



    public void gobackCat(View view) {
        finish();
    }

    void getCategoryFrist(String page) {
        Call<ArrayList<Category>> call = api.getArticle(id, new HashMap<String, String>() {{
            put("page", page);
        }});
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.body() == null) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    firstList = response.body();
                    if (firstList.size() == 10) {
                        firstList.add(null);
                    }

                    adapterArticleSubCat = new AdapterArticleSubCat(firstList);
                    recyclerView.setAdapter(adapterArticleSubCat);
                    recyclerView.setLayoutManager(new MyLinearLayoutManager(getBaseContext()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ArticleSubCatActivity.this, "check your Network", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void getCategory(String page) {
        Call<ArrayList<Category>> call = api.getArticle(id, new HashMap<String, String>() {{
            put("page", page);
        }});
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.body() == null) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.GONE);
                    ArrayList<Category> list;
                    list = response.body();
                    if (list.size() == 10) {
                        list.add(null);
                    }
                    firstList.remove(firstList.size() - 1);
                    firstList.addAll(list);
                    adapterArticleSubCat.notifyDataSetChanged(firstList);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ArticleSubCatActivity.this, "check your Network", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void getMoreArticleSubCat(View view) {
        page++;
        adapterArticleSubCat.updateFooter((Integer) view.getTag());
        firstList.add(null);
//        getCategory(String.valueOf(page));

    }

    public void signlike(View view) {
        int pos = (Integer) view.getTag();
        Category category = firstList.get(pos);
        Boolean drawable = ((ImageView) view).getDrawable().
                getConstantState().
                equals(getDrawable(R.drawable.icon_heart_home).getConstantState());
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            return;
        }
        else {
            if (drawable) {
                if (!realm.isInTransaction()) {
                    realm.beginTransaction();
                }
                RealmResults<RealmDB> results = realm.where(RealmDB.class).equalTo("id", category.get_id()).findAll();
                results.deleteAllFromRealm();
                realm.commitTransaction();

            } else {
                if (!realm.isInTransaction()) {
                    realm.beginTransaction();
                }
                    RealmDB realmDB = realm.createObject(RealmDB.class, category.get_id());
                    realmDB.setTitle(category.getTitle());
                    realmDB.setContent(category.getContent());
                    realmDB.setDate(category.getUpdatedAt());
                    realm.commitTransaction();
                }
            }
            mLastClickTime= SystemClock.elapsedRealtime();
                    adapterArticleSubCat.notifyChangedItem(pos);

        }



    public void openArticle(View view) {
        index= (int) view.getTag(R.id.index);
        Intent intent= new Intent(this,ArticleActivity.class);
        intent.putExtra(getString(R.string.Key_id_article),view.getTag().toString());

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(index!=-1){
            adapterArticleSubCat.notifyItemChanged(index);
        }
    }
}