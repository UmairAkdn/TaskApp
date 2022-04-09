package com.example.taskapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taskapp.R;
import com.example.taskapp.model.DataModel;
import com.example.taskapp.viewmodel.DataListViewModel;

import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.MyViewHolder> {
    private Context context;
    private List<DataModel> dataList;
    private ItemClickListener clickListener;


    public DataListAdapter(Context context, List<DataModel> dataList, ItemClickListener clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.clickListener = clickListener;

    }

    public void setDataList(List<DataModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvId.setText("ID: " + this.dataList.get(position).getId());
        holder.tvUserId.setText("User ID: " + this.dataList.get(position).getUserId());
        holder.tvTitle.setText("Title: " +this.dataList.get(position).getTitle());
        holder.tvBody.setText("Body: " +this.dataList.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onDataClick(dataList.get(position));
            }
        });


    }

   @Override
    public int getItemCount() {
        if(this.dataList != null) {
            return this.dataList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBody,tvUserId,tvId,tvTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView)itemView.findViewById(R.id.tvBody);
            tvUserId = (TextView)itemView.findViewById(R.id.tvUserId);
            tvId = (TextView)itemView.findViewById(R.id.tvId);

        }
    }


    public interface ItemClickListener{
        public void onDataClick(DataModel data);
    }
}
