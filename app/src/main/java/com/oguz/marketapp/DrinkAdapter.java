package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.MyViewHolder>{

    Context context;
    ArrayList<Drinks> drinksArrayList;

    private DrinkAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(DrinkAdapter.OnItemClickListener listener){
        mListener=listener;
    }


    public DrinkAdapter(Context context, ArrayList<Drinks> drinksArrayList) {
        this.context = context;
        this.drinksArrayList = drinksArrayList;

    }

    @NonNull
    @Override
    public DrinkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.drinkitem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Drinks drinks=drinksArrayList.get(position);

        holder.drinkname.setText(drinks.productname);
        holder.drinklitre.setText(drinks.litre+"");
        holder.drinkprice.setText(drinks.price+"");

    }

    @Override
    public int getItemCount() {
        return drinksArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView drinkname, drinkprice,drinklitre;

        public MyViewHolder(@NonNull View itemView,DrinkAdapter.OnItemClickListener listener) {
            super(itemView);
            drinkname = itemView.findViewById(R.id.drinkproduct);
            drinkprice= itemView.findViewById(R.id.drinkprice);
            drinklitre= itemView.findViewById(R.id.drinklitre);

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