package com.web.app.smartroof.Adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.web.app.smartroof.Activity.ArticleActivity;
import com.web.app.smartroof.R;

public class AdapterRecyclerviewimageArticle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int size;
    public AdapterRecyclerviewimageArticle(int size) {
        this.size=size;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder= null;
        if (viewType==-1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_footer_image_article,parent,false);
            viewHolder =new Footer(view);
        }
        else {
            if (size==1){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_image_article, parent, false);
                Holder holder= new Holder(view);
                DisplayMetrics displaymetrics = new DisplayMetrics();
                ((Activity)parent.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int devicewidth = displaymetrics.widthPixels ;
                holder.imageView.getLayoutParams().width = devicewidth;
                viewHolder=holder;


            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recyclerview_image_article, parent, false);
                viewHolder = new Holder(view);
        }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final StaggeredGridLayoutManager.LayoutParams layoutParams =
                new StaggeredGridLayoutManager.LayoutParams(
                        holder.itemView.getLayoutParams());
        if (position == 0) {
            layoutParams.setFullSpan(true);
        } else {
            layoutParams.setFullSpan(false);
        }
        holder.itemView.setLayoutParams(layoutParams);

        if(holder instanceof Footer){
            int number= size-3;
            ((Footer) holder).textView.setText("+"+number);
        }

    }

    @Override
    public int getItemCount() {
        int count;
        if(size>3)
            count=3;
        else
            count=size;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 2) {
            if (size > 3)
                type = -1;
            else
                type = 1;
            return type;
        }
        return super.getItemViewType(position);
    }
    static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView5);
        }
    }
    static class Footer extends RecyclerView.ViewHolder{
        TextView textView;
        public Footer(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view_number_image_article);
        }
    }
}
