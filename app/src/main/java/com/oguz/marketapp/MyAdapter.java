package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> productArrayList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public MyAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v , mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = productArrayList.get(position);

        holder.name.setText(product.categoryname);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.tvcategoryname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.OnItemClick(position);
                    }
                }
            });
        }
    }

}