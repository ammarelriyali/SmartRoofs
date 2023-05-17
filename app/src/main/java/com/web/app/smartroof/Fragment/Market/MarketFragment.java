package com.web.app.smartroof.Fragment.Market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterRecMarketCategory;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewMarket;
import com.web.app.smartroof.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketFragment extends Fragment {
    RecyclerView recyclerViewCycle;
    RecyclerView recyclerViewMarket;

    Api api;
    ArrayList<Category> categories ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_market, container, false);
        recyclerViewCycle = root.findViewById(R.id.recyclerview_cycle_market);
        api= RetrofitBuilder.getRetrofitInstance().create(Api.class);
        Call< ArrayList<Category> > call = api.getCategoryMarket();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                categories=response.body();
                List<Category> categorieslist =categories.subList(0,6);
                AdapterRecMarketCategory adapterRecyclerviewCycleHome = new AdapterRecMarketCategory(getContext(),categorieslist);
                recyclerViewCycle.setAdapter(adapterRecyclerviewCycleHome);
                recyclerViewCycle.setLayoutManager(new GridLayoutManager(getContext(),3));
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {


            }
        });

        recyclerViewMarket = root.findViewById(R.id.recyclerview_grid_market);
//        ArrayList<Market> markets = new ArrayList<>();
//        markets.add(new Market("200","مجرفه"));
//        markets.add(new Market("100","جاروف"));
//        markets.add(new Market("500","خشبه"));
//        markets.add(new Market("1000","فوطه"));
//        markets.add(new Market("100000","اشطا"));
//        markets.add(new Market("23455600","تيش"));
//        AdapterRecyclerviewMarket adapterRecyclerviewMarket = new AdapterRecyclerviewMarket(markets);
//        recyclerViewMarket.setNestedScrollingEnabled(false);
//        recyclerViewMarket.setLayoutManager(new GridLayoutManager(getContext(),2));
//        recyclerViewMarket.setHasFixedSize(true);
//        recyclerViewMarket.setAdapter(adapterRecyclerviewMarket);
        return root;
    }
}