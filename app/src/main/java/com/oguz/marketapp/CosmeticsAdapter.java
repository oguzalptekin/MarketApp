package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CosmeticsAdapter extends RecyclerView.Adapter<CosmeticsAdapter.MyViewHolder>{
        Context context;
        ArrayList<Cosmetics> cosmeticsArrayList;

    public CosmeticsAdapter(Context context, ArrayList<Cosmetics> cosmeticsArrayList) {
        this.context = context;
        this.cosmeticsArrayList = cosmeticsArrayList;
    }

    @NonNull
    @Override
    public CosmeticsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cosmeticsitem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CosmeticsAdapter.MyViewHolder holder, int position) {
        Cosmetics cosmetics = cosmeticsArrayList.get(position);

        holder.cosmeticsname.setText(cosmetics.productname);
        holder.cosmeticsprice.setText(cosmetics.price+"");
    }

    @Override
    public int getItemCount() {
        return cosmeticsArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cosmeticsname, cosmeticsprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cosmeticsname = itemView.findViewById(R.id.cosmeticname);
            cosmeticsprice= itemView.findViewById(R.id.cosmeticprice);
        }
    }
}
