package com.oguz.marketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.opHolder> {

    Context mcontext;
    ArrayList<AdminItems> adminItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=  listener;
    }

    public AdminRecyclerAdapter(Context mcontext, ArrayList<AdminItems> adminItems) {
        this.mcontext = mcontext;
        this.adminItems = adminItems;

    }

    @NonNull
    @Override
    public opHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_admin, parent, false);
        opHolder holder = new opHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull opHolder holder, int position) {
        holder.transaction.setText(adminItems.get(position).getTransaction());
        holder.symbol.setImageResource(adminItems.get(position).getSymbol());
        holder.enter.setImageResource(adminItems.get(position).getEnter());

    }

    @Override
    public int getItemCount() {
        return adminItems.size();
    }

    public static class opHolder extends RecyclerView.ViewHolder{

        private LinearLayout itemLayout;
        private ImageView symbol;
        private TextView transaction;
        private ImageView enter;

        public opHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.itemLinear);
            symbol = itemView.findViewById(R.id.optionImage);
            transaction = itemView.findViewById(R.id.optionTxt);
            enter = itemView.findViewById(R.id.enterImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
