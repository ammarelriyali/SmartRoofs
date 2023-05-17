package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewimageArticle;
import com.web.app.smartroof.R;
import utils.RealmDB;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.MyStaggeredGridLayoutManager;

public class ArticleActivity extends AppCompatActivity {
    RecyclerView recyclerViewMarket,recyclerViewArticle;
    Api api;
    Category category;
    TextView title,content,countTxt;
    ImageView iconCount,icon;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        recyclerViewMarket = findViewById(R.id.recyclerview_market_article);
        recyclerViewArticle=findViewById(R.id.recyclerview_image_article);
        title=findViewById(R.id.title_article);
        content=findViewById(R.id.conten_article);
        countTxt =findViewById(R.id.count_number_article);
        iconCount=findViewById(R.id.icon_heart_count);
        icon=findViewById(R.id.image_view_heart_love_article);

        Realm.init(this);
        realm= Realm.getDefaultInstance();


        api = RetrofitBuilder.getRetrofitInstance().create(Api.class);
        Call<Category> call= api.readArticle(getIntent().getStringExtra(getString(R.string.Key_id_article)));
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.body()!=null){
                    category=response.body();
                    title.setText(category.getTitle());
                    content.setText(category.getContent());
                    RealmDB results=realm.where(RealmDB.class).equalTo("id",category.get_id()).findFirst();
                    Log.i("TAG", "onResponse: "+results);
                    if(results!=null){
                        icon.setImageResource(R.drawable.icon_heart_home);
                        iconCount.setImageResource(R.drawable.icon_heart_home);
                        countTxt.setTextColor(getResources().getColor(R.color.green));
                    }
                }

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t.getMessage());

            }
        });


        int size=1;
        if(size==2) {
            recyclerViewArticle.setLayoutManager(new GridLayoutManager(this,2));
        }
        else if(size==1) {

            recyclerViewArticle.setLayoutManager(new LinearLayoutManager(this));

        }
        else {

            MyStaggeredGridLayoutManager staggeredGridLayoutManager= new MyStaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
            recyclerViewArticle.setLayoutManager(staggeredGridLayoutManager);
        }
        recyclerViewArticle.setAdapter(new AdapterRecyclerviewimageArticle(size));

//        ArrayList<Market> markets = new ArrayList<>();
//        markets.add(new Market("200","مجرفه"));
//        markets.add(new Market("100","جاروف"));
//        markets.add(new Market("500","خشبه"));
//        markets.add(new Market("1000","فوطه"));
//        markets.add(new Market("100000","اشطا"));
//        markets.add(new Market("23455600","تيش"));
//        AdapterRecyclerviewMarket adapterRecyclerviewMarket = new AdapterRecyclerviewMarket(markets);
//        recyclerViewMarket.setNestedScrollingEnabled(false);
//        recyclerViewMarket.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerViewMarket.setHasFixedSize(true);
//        recyclerViewMarket.setAdapter(adapterRecyclerviewMarket);
    }

    public void goBackArticle(View view) {
        finish();
    }

    public void share(View view) {
        Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
    }

    public void like(View view) {
        Boolean drawable = ((ImageView) view).getDrawable().
                getConstantState().
                equals(getDrawable(R.drawable.icon_heart_home).getConstantState());
        if (drawable) {
            ((ImageView) view).setImageResource(R.drawable.icon_heart_empty_line);
            iconCount.setImageResource(R.drawable.icon_heart_empty_gray);
            countTxt.setTextColor(getResources().getColor(R.color.secondary_text));
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            RealmResults<RealmDB> results = realm.where(RealmDB.class).equalTo("id",category.get_id()).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();

        }
        else {
            iconCount.setImageResource(R.drawable.icon_heart_home);
            countTxt.setTextColor(getResources().getColor(R.color.green));
            ((ImageView) view).setImageResource(R.drawable.icon_heart_home);
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            RealmDB realmDB = realm.createObject(RealmDB.class,category.get_id());
            realmDB.setTitle(category.getTitle());
            realmDB.setContent(category.getContent());
            realmDB.setDate(category.getUpdatedAt());
            realm.commitTransaction();

        }

    }

    public void likeCount(View view) {
        Boolean drawable = iconCount.getDrawable().
                getConstantState().
                equals(getDrawable(R.drawable.icon_heart_home).getConstantState());
        if (drawable) {
            iconCount.setImageResource(R.drawable.icon_heart_empty_gray);
            countTxt.setTextColor(getResources().getColor(R.color.secondary_text));
            icon.setImageResource(R.drawable.icon_heart_empty_line);
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            RealmResults results = realm.where(RealmDB.class).equalTo("id",category.get_id()).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();

        }
        else {
            icon.setImageResource(R.drawable.icon_heart_home);
            iconCount.setImageResource(R.drawable.icon_heart_home);
            countTxt.setTextColor(getResources().getColor(R.color.green));
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            RealmDB realmDB = realm.createObject(RealmDB.class,category.get_id());
            realmDB.setTitle(category.getTitle());
            realmDB.setContent(category.getContent());
            realmDB.setDate(category.getUpdatedAt());
            realm.commitTransaction();
        }


    }
}