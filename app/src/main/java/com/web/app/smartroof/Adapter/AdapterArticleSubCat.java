
package com.web.app.smartroof.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.R;
import utils.RealmDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdapterArticleSubCat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Category> list;
    Context context;
    Realm realm;
    int progress=-1;

    public AdapterArticleSubCat(ArrayList<Category> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();
        RecyclerView.ViewHolder vh;
        if (viewType == -1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_article_sub_cat_footer, parent, false);
            vh = new FooterViewHolder(view);
        } else if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_progress_footre, parent, false);
            vh = new FooterProgressViewHolder(view);
            progress=-1;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_article_sub_cat, parent, false);
            vh = new Holder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        if (holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).button.setTag(position);
        }
        if (holder instanceof Holder) {
            Category category = list.get(position);
            ((Holder) holder).title.setText(category.getTitle());
            ((Holder) holder).content.setText(category.getContent());
            ((Holder) holder).iconImage.setTag(position);
            ((Holder) holder).itemView.setTag(category.get_id());
            ((Holder) holder).itemView.setTag(R.id.index,position);

            String dtStart = category.getUpdatedAt();
            if (dtStart != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                try {
                    Date date;
                    date = format.parse(dtStart);
                    SimpleDateFormat format1 = new SimpleDateFormat("EEE LLL dd,yyyy");
                    String output = format1.format(date);
                    ((Holder) holder).date.setText(output);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            RealmResults<RealmDB> results = realm.where(RealmDB.class).equalTo("id", category.get_id()).findAll();
            if (results == null || results.isEmpty()) {
                ((Holder) holder).iconImage.setImageResource(R.drawable.icon_heart_empty_line);
            } else {
                ((Holder) holder).iconImage.setImageResource(R.drawable.icon_heart_home);
            }
            realm.commitTransaction();
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (progress==0){
            Log.i("TAG", "getItemViewType: "+progress);
            return 0;
        }
        else if (list.get(position) == null&&progress!=0) {
            Log.i("TAG", "getItemViewType: "+9);
            return -1;
        }
        else {
            return 1;
        }
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title, content, date;
        ImageView image, iconImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_article_sub_cat);
            content = itemView.findViewById(R.id.content_article_sub_cat);
            date = itemView.findViewById(R.id.text_view_recyclerview_favorite_date);
            iconImage = itemView.findViewById(R.id.image_icon_article_sub_cat);

        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.button_Article_footer);
        }
    }
    public static class FooterProgressViewHolder extends RecyclerView.ViewHolder {

        public FooterProgressViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public void notifyDataSetChanged(ArrayList<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void notifyChangedItem(int pos) {
        notifyItemChanged(pos);
    }
    public void updateFooter(int pos){
        this.progress=0;
        notifyItemRemoved(pos);
        notifyItemInserted(pos);
    }

}
