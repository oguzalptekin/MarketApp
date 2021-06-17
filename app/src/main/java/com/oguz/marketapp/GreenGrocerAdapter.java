package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GreenGrocerAdapter extends RecyclerView.Adapter<GreenGrocerAdapter.MyViewHolder>{

    Context context;
    ArrayList<GreenGrocer> greenGrocerArrayList;

    public GreenGrocerAdapter(Context context, ArrayList<GreenGrocer> greenGrocerArrayList) {
        this.context = context;
        this.greenGrocerArrayList = greenGrocerArrayList;
    }

    @NonNull
    @Override
    public GreenGrocerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.greengroceritem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GreenGrocerAdapter.MyViewHolder holder, int position) {
        GreenGrocer greenGrocer= greenGrocerArrayList.get(position);

        holder.grocername.setText(greenGrocer.productname);
        holder.grocerprice.setText(greenGrocer.price+"");
        holder.grocerweight.setText(greenGrocer.weight+"");
    }

    @Override
    public int getItemCount() {
        return greenGrocerArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView grocername, grocerprice,grocerweight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            grocername = itemView.findViewById(R.id.grocerproduct);
            grocerprice= itemView.findViewById(R.id.grocerprice);
            grocerweight= itemView.findViewById(R.id.grocerweight);
        }
    }
}
