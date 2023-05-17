package com.web.app.smartroof.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.R;

import java.util.ArrayList;

public class AdapterRecyclerviewLinearHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
//    ArrayList<News> list;
//    public AdapterRecyclerviewLinearHome(Context context, ArrayList<News> list){
//     this.context=context;
//     this.list=list;
//    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder vh;
        if(viewType==-1){
             view= LayoutInflater.from(context).inflate(R.layout.footer_button_home,parent, false);
             vh = new FooterViewHolder(view);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview_linear_home, parent, false);
            vh= new Holder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Holder){
//            News subject = list.get(position);
//            ((Holder)holder).header.setText(list.get(position).getHeader());
//            ((Holder)holder).subject.setText(list.get(position).getSubject());
//            ((Holder)holder).date.setText(list.get(position).getDate());
        }
    }

    @Override
    public int getItemCount() {

        return 0;
    }
    @Override
    public int getItemViewType(int position) {
//        if (list.get(position) == null) {
//            return -1;
//        }
//

        return 1;
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView header,date,subject;
        public Holder(@NonNull View itemView) {
            super(itemView);
            header=itemView.findViewById(R.id.text_view_recyclerview_linear_headr);
            subject=itemView.findViewById(R.id.text_view_recyclerview_linear_subject);
            date=itemView.findViewById(R.id.text_view_recyclerview_linear_date);


        }
    }
    public static class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
