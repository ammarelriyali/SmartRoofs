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

import org.w3c.dom.Text;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterRecyclerViewCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Category> list;
    Context context;
    public AdapterRecyclerViewCategory(ArrayList<Category> list){

        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        context=parent.getContext();
        RecyclerView.ViewHolder vh;
        if(viewType==-1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_button_category,parent, false);
            vh = new FooterViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_category, parent, false);
            vh= new Holder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Holder){
            ((Holder) holder).title.setText(list.get(position).getTitle());
            String type=list.get(position).getSubCatType();
            if (type!=null){
                if(type.equals("winter")){
                    Glide.with(context).load(R.drawable.ic_snow).into(((Holder) holder).imageIcon);
                }else if(type.equals("summer")){
                    Glide.with(context).load(R.drawable.ic_sun).into(((Holder) holder).imageIcon);
                }if(type.equals("alltheyear")){
                    Glide.with(context).load(R.drawable.ic_all_year).into(((Holder) holder).imageIcon);
                }
            }
            Glide.with(context).load("http://192.168.1.8:3000/subcat/avatar/"+list.get(position).get_id())
                    .centerCrop().into(((Holder) holder).image);

            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (list.get(position) == null) {
            return -1;
        }
        return 1;
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageIcon,image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title_sub_cat);
            imageIcon = itemView.findViewById(R.id.image_icon_sub_cat);
            image = itemView.findViewById(R.id.image_sub_cat);


        }
    }
    public static class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public void notifyDataSetChanged(ArrayList<Category> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
