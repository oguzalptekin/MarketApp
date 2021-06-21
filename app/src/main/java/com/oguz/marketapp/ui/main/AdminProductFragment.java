package com.oguz.marketapp.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oguz.marketapp.AddCategoryActivity;
import com.oguz.marketapp.AddProductActivity;
import com.oguz.marketapp.AdminItems;
import com.oguz.marketapp.AdminRecyclerAdapter;
import com.oguz.marketapp.DeleteCategoryActivity;
import com.oguz.marketapp.DeleteProductActivity;
import com.oguz.marketapp.EditCategoryActivity;
import com.oguz.marketapp.EditProductActivity;
import com.oguz.marketapp.R;

import java.util.ArrayList;


public class AdminProductFragment extends Fragment implements View.OnClickListener{

    PageViewModel pageViewModel;
    private RecyclerView recyclerView;
    private ArrayList<AdminItems> itemlist;
    AdminRecyclerAdapter adminRecyclerAdapter;

    public static AdminProductFragment newInstance(){
        return new AdminProductFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        itemlist = new ArrayList<>();
        itemlist.add(new AdminItems(R.drawable.add, "Add Product", R.drawable.greater));
        itemlist.add(new AdminItems(R.drawable.delete, "Delete Product", R.drawable.greater));
        itemlist.add(new AdminItems(R.drawable.edit, "Edit Product", R.drawable.greater));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_admin_product, container, false);
        recyclerView = view.findViewById(R.id.product_recyclerview);
        AdminRecyclerAdapter adminRecyclerAdapter = new AdminRecyclerAdapter(getContext(), itemlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adminRecyclerAdapter);

        adminRecyclerAdapter.setOnItemClickListener(new AdminRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                if (position==0) {
                    Intent intenttoadd = new Intent(getActivity(), AddProductActivity.class);
                    startActivity(intenttoadd);
                }
                else if(position==1){
                    Intent intenttodel = new Intent(getActivity(), DeleteProductActivity.class);
                    startActivity(intenttodel);
                }
                else if(position == 2){
                    Intent intenttoedit = new Intent(getActivity(), EditProductActivity.class);
                    startActivity(intenttoedit);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //xml'in tanıtımları vs burada oluyor
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {

    }
}