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

    private CosmeticsAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(CosmeticsAdapter.OnItemClickListener listener){
        mListener=listener;
    }
    public CosmeticsAdapter(Context context, ArrayList<Cosmetics> cosmeticsArrayList) {
        this.context = context;
        this.cosmeticsArrayList = cosmeticsArrayList;
    }

    @NonNull
    @Override
    public CosmeticsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cosmeticsitem,parent,false);

        return new MyViewHolder(v,mListener);
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

        public MyViewHolder(@NonNull View itemView, CosmeticsAdapter.OnItemClickListener listener) {
            super(itemView);
            cosmeticsname = itemView.findViewById(R.id.cosmeticname);
            cosmeticsprice= itemView.findViewById(R.id.cosmeticprice);

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