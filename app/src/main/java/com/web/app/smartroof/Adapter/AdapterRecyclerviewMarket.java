package com.web.app.smartroof.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.R;

import java.util.ArrayList;

public class AdapterRecyclerviewMarket extends RecyclerView.Adapter<AdapterRecyclerviewMarket.Holder>{
//    ArrayList<Market> markets;

    public static class Holder extends RecyclerView.ViewHolder{
        TextView salary,name_tool;
        public Holder(@NonNull View itemView) {
            super(itemView);
            salary=itemView.findViewById(R.id.text_view_salary_market);
            name_tool=itemView.findViewById(R.id.text_view_name_tool);
        }
    }
//    public AdapterRecyclerviewMarket(ArrayList<Market> markets){
//        this.markets=markets;
//    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_market,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
//    Market market= markets.get(position);
//    holder.name_tool.setText(market.getName_tool());
//    holder.salary.setText(market.getSalary());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
