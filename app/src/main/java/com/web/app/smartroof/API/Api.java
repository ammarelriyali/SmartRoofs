package com.web.app.smartroof.API;

import com.web.app.smartroof.API.Model.Category;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Api {

@GET("cat/read/all")
Call<ArrayList<Category>> getCategory();

@GET("store/cat/read")
Call<ArrayList<Category>> getCategoryMarket();

@GET("subcat/read/{id}")
Call<ArrayList<Category>> getSubCategoryHome(@Path("id")String id,@QueryMap HashMap<String,String> Page,@QueryMap HashMap<String,String> type );

@GET("article/read/{id}")
Call<ArrayList<Category>> getArticle(@Path("id")String id,@QueryMap HashMap<String,String> Page);

@GET("readArticle/{id}")
Call<Category> readArticle(@Path("id")String id);

@GET("article/search")
Call<ArrayList<Category>> searchArticle(@Body String search);

@POST("article/{id}/likes")
Call<ArrayList<Category>> setLike(@Path("id")String id,@Header("Authorization") String token);

@POST("article/{id}}/unlikes")
Call<ArrayList<Category>> setUnlike(@Path("id")String id,@Header("Authorization") String token);






}
