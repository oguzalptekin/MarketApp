package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{
    Context context;
    ArrayList<Food> foodArrayList;

    private FoodAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(FoodAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public FoodAdapter(Context context, ArrayList<Food> foodArrayList) {
        this.context = context;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fooditem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        Food food = foodArrayList.get(position);

        holder.foodname.setText(food.productname);
        holder.foodprice.setText(food.price+"");
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView foodname, foodprice;

        public MyViewHolder(@NonNull View itemView,FoodAdapter.OnItemClickListener listener) {
            super(itemView);
            foodname = itemView.findViewById(R.id.foodproduct);
            foodprice= itemView.findViewById(R.id.foodprice);

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
