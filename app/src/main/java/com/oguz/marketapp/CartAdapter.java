package com.oguz.marketapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    ImageButton deletebutton;
    Context context;
    ArrayList<Product> productArrayList;

    private CartAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnDeleteClick(int position);
        void OnIncClick(int position);
        void OnDecClick(int position);
    }

    public void setOnItemClickListener(CartAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public CartAdapter(@NonNull Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartitem,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Product product=productArrayList.get(position);

        holder.name.setText(product.categoryname);
        holder.price.setText(product.price + " ");
        holder.quantity.setText(product.quantity + " ");
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,quantity;
        ImageButton deletebutton,incbutton,decbutton;

        public MyViewHolder(@NonNull View itemView,CartAdapter.OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.cartname);
            price=itemView.findViewById(R.id.cartprice);
            quantity=itemView.findViewById(R.id.editquantity);
            deletebutton=itemView.findViewById(R.id.deleteitembtn);
            incbutton=itemView.findViewById(R.id.incbutton);
            decbutton=itemView.findViewById(R.id.decbutton);


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.OnItemClick(position);
                    }
                }
            });*/
            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.OnDeleteClick(position);
                    }
                }
            });
            incbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.OnIncClick(position);
                    }
                }
            });
            decbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.OnDecClick(position);
                    }
                }
            });
        }
    }
}
