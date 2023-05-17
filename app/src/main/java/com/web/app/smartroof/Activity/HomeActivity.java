package com.web.app.smartroof.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.web.app.smartroof.Dialog.CategoriesDialog;
import com.web.app.smartroof.Dialog.SearchDialog;
import com.web.app.smartroof.Dialog.StoreCategoriesDialog;
import com.web.app.smartroof.R;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private String txt;
    private boolean isArticlesSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        txt=getIntent().getStringExtra("search_txt");
        isArticlesSearch=getIntent().getBooleanExtra("isArticle",false);
        if(getIntent().getBooleanExtra("isSearch",false)){
            new SearchDialog(this, isArticlesSearch, txt).show();
        }



    }

    public void goSearch(View view) {
        new SearchDialog(this, true, null).show();
    }

    public void goSections(View view) {
        CategoriesDialog bottomSheetDialog = new CategoriesDialog();
        bottomSheetDialog.show(getSupportFragmentManager(), "categories_bottom_sheet");
    }

    public void goSectionsMarket(View view) {
        StoreCategoriesDialog bottomSheetDialog = new StoreCategoriesDialog();
        bottomSheetDialog.show(getSupportFragmentManager(),"store_categories_bottom_sheet");
    }

    public void goFavorite(View view) {

        startActivity(new Intent(this, FavoriteActivity.class));
    }

    public void showMoreNews(View view) {
        Toast.makeText(this, "showMoreNews", Toast.LENGTH_SHORT).show();
    }

    public void changeName(View view) {
        Toast.makeText(this, "amar", Toast.LENGTH_SHORT).show();


    }

    public void goPrivacyPolicy(View view) {
        Toast.makeText(this, "Privacy policy", Toast.LENGTH_SHORT).show();
    }

    public void goCommunication(View view) {
        Toast.makeText(this, "Communication", Toast.LENGTH_SHORT).show();
    }

    public void goUs(View view) {
        Toast.makeText(this, "us", Toast.LENGTH_SHORT).show();
    }



    public void changeNumber(View view) {
        Toast.makeText(this, "changeNumber", Toast.LENGTH_SHORT).show();
    }


    public void goCart(View view) {
        startActivity(new Intent(this,CartActivity.class));
    }

    public void goCategory(View view) {
        switch (view.getTag().toString()){
            case "Home":
                Intent intent=new Intent(this,CategoryActivity.class);
                intent.putExtra(getString(R.string.Key_id_Category),String.valueOf(view.getTag(R.id.Category)));
                intent.putExtra(getString(R.string.Key_name_Category),String.valueOf(view.getTag(R.id.name_Category)));

                startActivity(intent);
            case "Market":

        }
    }

    public void openItemSearchHistory(View view) {
        Toast.makeText(this, view.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public void signIn(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }
}