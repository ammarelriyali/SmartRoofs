package com.web.app.smartroof.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.web.app.smartroof.R;
import java.util.ArrayList;


public class AdapterRecyclerViewPropertiesToolName extends RecyclerView.Adapter<AdapterRecyclerViewPropertiesToolName.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;

    public AdapterRecyclerViewPropertiesToolName(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview_properties_tool_name, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_view_recyclerview_porperties_tool_name);
        }
    }
}