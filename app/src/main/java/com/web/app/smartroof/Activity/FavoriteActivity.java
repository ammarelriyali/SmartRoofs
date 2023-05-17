package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.web.app.smartroof.Adapter.AdapterRecyclerviewFavoirte;
import com.web.app.smartroof.R;
import utils.RealmDB;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    TextView textView, textView2;
    Button button;

    Realm realm;
    RealmResults<RealmDB> results;
    AdapterRecyclerviewFavoirte adapterRecyclerviewFavoirte;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.recyclerview_grid_favorite);
        imageView = findViewById(R.id.image_favorite_empty_heart);
        button = findViewById(R.id.button_favorite);
        textView = findViewById(R.id.text_view_favorite_1);
        textView2 = findViewById(R.id.text_view_favorite_2);

        Realm.init(this);
        realm = Realm.getDefaultInstance();
        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }
        results = realm.where(RealmDB.class).findAll();
        realm.commitTransaction();
        if (results.isEmpty() || results == null) {
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            adapterRecyclerviewFavoirte = new AdapterRecyclerviewFavoirte(results);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterRecyclerviewFavoirte);

        }
    }

    public void goHome(View view) {
        finish();
    }


    public void unlike(View view) {
        RealmDB realmDB=results.get((Integer) view.getTag());
        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }
        RealmResults<RealmDB> results = realm.where(RealmDB.class).equalTo("id",realmDB.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        adapterRecyclerviewFavoirte.notifyChangedItem(this.results);


    }
    public void openFavoriteArticle(View view) {
        index= (int) view.getTag(R.id.index);
        Intent intent= new Intent(this,ArticleActivity.class);
        intent.putExtra(getString(R.string.Key_id_article),view.getTag().toString());

        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(index!=-1){
            adapterRecyclerviewFavoirte.notifyItemChanged(index);
        }
    }
}
