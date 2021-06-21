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

    private SnackAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(SnackAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public SnackAdapter(Context context, ArrayList<Snacks> snacksArrayList) {
        this.context = context;
        this.snacksArrayList = snacksArrayList;
    }

    @NonNull
    @Override
    public SnackAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.snackitem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SnackAdapter.MyViewHolder holder, int position) {
        Snacks snacks=snacksArrayList.get(position);

        holder.snackname.setText(snacks.productname);
        holder.snackprice.setText(String.valueOf(snacks.price));
        holder.snackweight.setText(String.valueOf(snacks.weight));
    }

    @Override
    public int getItemCount() {
        return snacksArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView snackname, snackprice,snackweight;

        public MyViewHolder(@NonNull View itemView, SnackAdapter.OnItemClickListener listener) {
            super(itemView);
            snackname = itemView.findViewById(R.id.snackproduct);
            snackprice= itemView.findViewById(R.id.snackprice);
            snackweight= itemView.findViewById(R.id.snackweight);

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