package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BakerAdapter extends RecyclerView.Adapter<BakerAdapter.MyViewHolder>{

    Context context;
    ArrayList<Baker> bakerArrayList;

    private BakerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(BakerAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public BakerAdapter(Context context, ArrayList<Baker> bakerArrayList) {
        this.context = context;
        this.bakerArrayList = bakerArrayList;
    }

    @NonNull
    @Override
    public BakerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bakeritem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BakerAdapter.MyViewHolder holder, int position) {
        Baker baker = bakerArrayList.get(position);

        holder.bakername.setText(baker.productname);
        holder.bakerprice.setText(baker.price+"");
    }

    @Override
    public int getItemCount() {
        return bakerArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bakername, bakerprice;

        public MyViewHolder(@NonNull View itemView,BakerAdapter.OnItemClickListener listener) {
            super(itemView);
            bakername = itemView.findViewById(R.id.bakername);
            bakerprice= itemView.findViewById(R.id.bakerprice);

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
