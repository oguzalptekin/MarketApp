package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.MyViewHolder>{
    Context context;
    ArrayList<Others> othersArrayList;

    private OthersAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OthersAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public OthersAdapter(Context context, ArrayList<Others> othersArrayList) {
        this.context = context;
        this.othersArrayList = othersArrayList;
    }

    @NonNull
    @Override
    public OthersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.othersitem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OthersAdapter.MyViewHolder holder, int position) {
        Others others= othersArrayList.get(position);

        holder.othersname.setText(others.productname);
        holder.othersprice.setText(others.price+"");
    }

    @Override
    public int getItemCount() {
        return othersArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView othersname,othersprice;
        public MyViewHolder(@NonNull View itemView,OthersAdapter.OnItemClickListener listener) {
            super(itemView);
            othersname = itemView.findViewById(R.id.othersproduct);
            othersprice= itemView.findViewById(R.id.othersprice);

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