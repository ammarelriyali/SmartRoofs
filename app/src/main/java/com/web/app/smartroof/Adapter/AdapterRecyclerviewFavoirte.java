package com.web.app.smartroof.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.R;
import utils.RealmDB;

import io.realm.RealmResults;

public class AdapterRecyclerviewFavoirte extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RealmResults<RealmDB> list;
    public AdapterRecyclerviewFavoirte(RealmResults<RealmDB> list){
     this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder vh;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_favorite, parent, false);
            vh= new Holder(view);

        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ((Holder)holder).header.setText(list.get(position).getTitle());
            ((Holder)holder).subject.setText(list.get(position).getContent());
            ((Holder) holder).icon.setTag(position);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class Holder extends RecyclerView.ViewHolder{
        TextView header,date,subject;
        ImageView icon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            header=itemView.findViewById(R.id.text_view_recyclerview_favorite_header);
            subject=itemView.findViewById(R.id.text_view_recyclerview_favorite_subject);
            date=itemView.findViewById(R.id.text_view_recyclerview_favorite_date);
            icon=itemView.findViewById(R.id.image_view_recyclerview_favorite_heater);
        }
    }
    public void notifyChangedItem(RealmResults<RealmDB> list){
        this.list=list;
        notifyDataSetChanged();
    }

}
