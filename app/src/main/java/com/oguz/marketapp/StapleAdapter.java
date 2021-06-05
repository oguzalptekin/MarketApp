package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StapleAdapter extends RecyclerView.Adapter<StapleAdapter.MyViewHolder>{

    Context context;
    ArrayList<StapleFood> stapleFoodArrayList;

    public StapleAdapter(Context context, ArrayList<StapleFood> stapleFoodArrayList) {
        this.context = context;
        this.stapleFoodArrayList = stapleFoodArrayList;
    }

    @NonNull
    @Override
    public StapleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.staplefooditem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StapleAdapter.MyViewHolder holder, int position) {
        StapleFood stapleFood = stapleFoodArrayList.get(position);

        holder.staplename.setText(stapleFood.productname);
        holder.stapleprice.setText(stapleFood.price+"");
    }

    @Override
    public int getItemCount() {
        return stapleFoodArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView staplename, stapleprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            staplename = itemView.findViewById(R.id.staplename);
            stapleprice= itemView.findViewById(R.id.stapleprice);
        }
    }
}
