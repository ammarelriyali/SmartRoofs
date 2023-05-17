package com.web.app.smartroof.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdapterRecSearchDialog extends RecyclerView.Adapter<AdapterRecSearchDialog.MyHolderSearchDialog> {
    ArrayList<String> list ;
    public AdapterRecSearchDialog(ArrayList<String> set) {
        this.list =  new ArrayList<String>(set);
    }

    @NonNull
    @Override
    public MyHolderSearchDialog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search,parent,false);
        return new MyHolderSearchDialog(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderSearchDialog holder, int position) {
        holder.textView.setText(list.get(position));
        holder.itemView.setTag(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolderSearchDialog extends RecyclerView.ViewHolder{
        TextView textView;
        public MyHolderSearchDialog(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.search_history_txt);
        }
    }
}
