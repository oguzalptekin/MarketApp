package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.MyViewHolder>{

    Context context;
    ArrayList<Snacks> snacksArrayList;

    public SnackAdapter(Context context, ArrayList<Snacks> snacksArrayList) {
        this.context = context;
        this.snacksArrayList = snacksArrayList;
    }

    @NonNull
    @Override
    public SnackAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.snackitem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SnackAdapter.MyViewHolder holder, int position) {
        Snacks snacks=snacksArrayList.get(position);

        holder.snackname.setText(snacks.productname);
        holder.snackprice.setText(snacks.price+"");
        holder.snackweight.setText(snacks.weight+"");

    }

    @Override
    public int getItemCount() {
        return snacksArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView snackname, snackprice,snackweight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            snackname = itemView.findViewById(R.id.snackproduct);
            snackprice= itemView.findViewById(R.id.snackprice);
            snackweight= itemView.findViewById(R.id.snackweight);
        }
    }
}
