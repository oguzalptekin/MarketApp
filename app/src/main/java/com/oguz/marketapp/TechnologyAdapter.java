package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.MyViewHolder>{
    Context context;
    ArrayList<Technology> technologyArrayList;

    public TechnologyAdapter(Context context, ArrayList<Technology> technologyArrayList) {
        this.context = context;
        this.technologyArrayList = technologyArrayList;
    }

    @NonNull
    @Override
    public TechnologyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.technologyitem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnologyAdapter.MyViewHolder holder, int position) {
        Technology technology = technologyArrayList.get(position);

        holder.techname.setText(technology.productname);
        holder.techprice.setText(technology.price+"");
        holder.techbrand.setText(technology.brand);
    }

    @Override
    public int getItemCount() {
        return technologyArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView techname, techprice,techbrand;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            techname = itemView.findViewById(R.id.technologyname);
            techprice= itemView.findViewById(R.id.technologyprice);
            techbrand= itemView.findViewById(R.id.technologybrand);
        }
    }
}
