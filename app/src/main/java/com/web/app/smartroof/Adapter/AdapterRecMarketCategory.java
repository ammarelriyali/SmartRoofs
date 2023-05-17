package com.web.app.smartroof.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.R;

import java.util.List;

public class AdapterRecMarketCategory extends RecyclerView.Adapter<AdapterRecMarketCategory.MyHolder> {
    Context context;
    List<Category> list;
    public AdapterRecMarketCategory(Context context, List<Category> list){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview_cycle_home,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        Glide.with(context).load("http://192.168.1.8:3000/store/cat/avatar/"+list.get(position).get_id())
                .centerCrop().into(holder.imageview);
        holder.itemView.setTag(R.id.Category,list.get(position).get_id());
        holder.itemView.setTag("Market");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageview;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textview_list_recyclerview_cycle_home);
            imageview =itemView.findViewById(R.id.image_view_list_recyclerview_cycle_home);


        }
    }
}
