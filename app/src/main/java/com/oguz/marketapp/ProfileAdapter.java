package com.oguz.marketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder>{
    Context context;
    ArrayList<ProfileItems> profileItems;
    private ProfileAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(ProfileAdapter.OnItemClickListener listener){
        mListener=listener;
    }
    public ProfileAdapter(Context context) {
        this.context = context;
    }

    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false);

        return new ProfileAdapter.MyViewHolder(v,mListener);
    }

    public void onBindViewHolder(@NonNull ProfileAdapter.MyViewHolder holder, int position) {
        holder.process.setText(profileItems.get(position).getProcess());
        holder.enter.setImageResource(profileItems.get(position).getEnter());
    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView process;
        private ImageView enter;

        public MyViewHolder(@NonNull View itemView,ProfileAdapter.OnItemClickListener listener) {
            super(itemView);
            process = itemView.findViewById(R.id.process_profile);
            enter =  itemView.findViewById(R.id.profile_im);

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
