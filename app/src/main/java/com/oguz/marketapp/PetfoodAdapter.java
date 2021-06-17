package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetfoodAdapter extends RecyclerView.Adapter<PetfoodAdapter.MyViewHolder>{

    Context context;
    ArrayList<Petfood> petfoodArrayList;

    public PetfoodAdapter(Context context, ArrayList<Petfood> petfoodArrayList) {
        this.context = context;
        this.petfoodArrayList = petfoodArrayList;
    }

    @NonNull
    @Override
    public PetfoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.petfooditem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetfoodAdapter.MyViewHolder holder, int position) {
        Petfood petfood = petfoodArrayList.get(position);

        holder.petfoodname.setText(petfood.productname);
        holder.petfoodprice.setText(petfood.price+"");
        holder.petfoodwhich.setText(petfood.whichAnimal);
    }

    @Override
    public int getItemCount() {
        return petfoodArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView petfoodname, petfoodprice,petfoodwhich;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            petfoodname = itemView.findViewById(R.id.petfoodname);
            petfoodprice= itemView.findViewById(R.id.petfoodprice);
            petfoodwhich= itemView.findViewById(R.id.petfoodwhich);
        }
    }
}
