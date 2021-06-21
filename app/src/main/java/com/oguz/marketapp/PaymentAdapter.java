package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder>{
    ImageView delete;
    Context context;
    ArrayList<CreditCard> cardlist;

    private PaymentAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnDeleteClick(int position);
    }

    public void setOnItemClickListener(PaymentAdapter.OnItemClickListener listener){
        mListener=listener;
    }
    public PaymentAdapter(@NonNull Context context, ArrayList<CreditCard> cardlist) {
        this.context = context;
        this.cardlist = cardlist;
    }

    public PaymentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.payment_item,parent,false);

        return new PaymentAdapter.MyViewHolder(v,mListener);
    }

    public void onBindViewHolder(@NonNull PaymentAdapter.MyViewHolder holder, int position) {
        CreditCard card = cardlist.get(position);

        holder.cardowner.setText(card.getCardholderName());
        holder.cardnumber.setText(card.getCardNumber());
    }

    public int getItemCount() {
        return cardlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cardowner, cardnumber;
        ImageButton deletebutton;

        public MyViewHolder(@NonNull View itemView,PaymentAdapter.OnItemClickListener listener) {
            super(itemView);
            cardowner = itemView.findViewById(R.id.cardowner);
            cardnumber=itemView.findViewById(R.id.cardnumberTxt);
            deletebutton=itemView.findViewById(R.id.deleteCard);

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
        }
    }


}
