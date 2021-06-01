package com.oguz.marketapp;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryHolder> {

    private ArrayList<Category> categories;

    public CategoriesRecyclerAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_categories, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
        holder.categoryImageview.setImageResource(categories.get(position).getImage());
    }

    @Override
    public int getItemCount() { //kaç tane liste elemanı olacağını gösterecek. (arraylist<products>.size)
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryImageview;
        TextView categoryName;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageview = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryNameTxt);
        }
    }
}
