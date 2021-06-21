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

    private TechnologyAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(TechnologyAdapter.OnItemClickListener listener){
        mListener=listener;
    }
    public TechnologyAdapter(Context context, ArrayList<Technology> technologyArrayList) {
        this.context = context;
        this.technologyArrayList = technologyArrayList;
    }

    @NonNull
    @Override
    public TechnologyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.technologyitem,parent,false);

        return new MyViewHolder(v,mListener);
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

        public MyViewHolder(@NonNull View itemView,TechnologyAdapter.OnItemClickListener listener) {
            super(itemView);
            techname = itemView.findViewById(R.id.technologyname);
            techprice= itemView.findViewById(R.id.technologyprice);
            techbrand= itemView.findViewById(R.id.technologybrand);

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