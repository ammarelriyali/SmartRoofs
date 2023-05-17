 package com.web.app.smartroof.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.app.smartroof.Activity.SearchActivity;
import com.web.app.smartroof.Adapter.AdapterRecSearchDialog;
import com.web.app.smartroof.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SearchDialog extends Dialog {

    EditText searchEditText;
    TextView historySearchTxt , txtEmpty;
    ImageView backButton;
    InputMethodManager imm;
    boolean isArticlesSearch;
    String searchTxt;
    RecyclerView recyclerView;
    static ArrayList<String> arrayList =new ArrayList<>();
    SharedPreferences sharedPref;
    Gson gson = new Gson();
    String json;


    public SearchDialog(@NonNull Context context, boolean isArticlesSearch, String searchTxt) {
        super(context, R.style.Theme_SmartRoofs);
        this.isArticlesSearch = isArticlesSearch;
        this.searchTxt = searchTxt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_search);
        backButton = findViewById(R.id.back_btn);
        searchEditText = findViewById(R.id.search_edit_txt);
        historySearchTxt = findViewById(R.id.txt_search_history);
        recyclerView =findViewById(R.id.search_history_list);
        txtEmpty=findViewById(R.id.txt_search_empty);
        sharedPref = PreferenceManager.getDefaultSharedPreferences( getContext() );

        json= sharedPref.getString("set_search", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        if (json==null){
            recyclerView.setVisibility(View.GONE);
            historySearchTxt.setVisibility(View.GONE);
        }
        else {
            txtEmpty.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            AdapterRecSearchDialog dialog =new AdapterRecSearchDialog(arrayList);
            recyclerView.setAdapter(dialog);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        if(searchTxt != null){
            searchAgain();
        }else{
            searchFirstTime();
        }
    }

    private void searchAgain() {
        searchEditText.setText(searchTxt);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String searchTxt = v.getText().toString().trim();
                if (!searchTxt.isEmpty()){
                    SharedPreferences.Editor editor = sharedPref.edit();
                    json = sharedPref.getString("set_search", null);
                    Type type = new TypeToken<ArrayList<String>>() {}.getType();
                    arrayList = gson.fromJson(json, type);
                    arrayList.add(0,searchTxt);
                    json = gson.toJson(arrayList);
                    editor.putString("set_search", json);
                    editor.apply();

                    startSearchActivity(searchTxt);
                    return false;
                }
                return true;
            }
        });
    }

    private void searchFirstTime() {
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String searchTxt = v.getText().toString().trim();
                if (!searchTxt.isEmpty()){
                    SharedPreferences.Editor editor = sharedPref.edit();
                    json = sharedPref.getString("set_search", null);
                    Type type = new TypeToken<ArrayList<String>>() {}.getType();
                    arrayList = gson.fromJson(json, type);
                    Log.i("TAG", "onEditorAction: "+searchTxt);
                    if(arrayList==null){
                        arrayList= new ArrayList<>();
                    }
                    arrayList.add(0,searchTxt);
                    json = gson.toJson(arrayList);
                    editor.putString("set_search", json);
                    editor.apply();

                    startSearchActivity(searchTxt);
                    return false;
                }
                return true;
            }
        });
    }

    private void startSearchActivity(String searchTxt) {

        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra("search_txt", searchTxt);
        intent.putExtra("isArticle", isArticlesSearch);
        getContext().startActivity(intent);
        hideKeyboard();

    }

    @Override
    protected void onStart() {
        super.onStart();
        openKeyboard();
    }



    private void openKeyboard() {
        searchEditText.requestFocus();
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void hideKeyboard() {
        imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS,0);
        dismiss();
    }


}
