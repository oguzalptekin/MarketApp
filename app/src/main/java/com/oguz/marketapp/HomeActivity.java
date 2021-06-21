package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{
    CardView cardView;
    ImageButton cartbutton;
    RecyclerView recyclerView;
    ArrayList<Product> productArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart_navigation:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        return true;
                    case R.id.home_navigation:
                        return true;
                    case R.id.profile_navigation:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        return true;
                }
                return false;
            }
        });

        cartbutton=findViewById(R.id.cartbtn);

        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        cardView = findViewById(R.id.cardview);
        BuildRecyclerView();

        EventChangeListener();


    }
    private void BuildRecyclerView(){
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db= FirebaseFirestore.getInstance();
        productArrayList= new ArrayList<Product>();
        myAdapter=new  MyAdapter(HomeActivity.this,productArrayList);

        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                if (productArrayList.get(position).categoryname.equals("Snacks")) {
                    Intent intenttoproduct = new Intent(HomeActivity.this, SnackActivity.class);
                    startActivity(intenttoproduct);
                }
                if (productArrayList.get(position).categoryname.equals("Green Grocer")) {
                    Intent intenttoproduct = new Intent(HomeActivity.this, GreenGrocerActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Drinks")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, DrinkActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Food")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, FoodActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Technology")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, TechnologyActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Baker")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, BakerActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Staple Food")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, StapleActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Pet Food")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, PetFoodActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Others")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, OthersActivity.class);
                    startActivity(intenttoproduct);
                }
                if(productArrayList.get(position).categoryname.equals("Cosmetics")){
                    Intent intenttoproduct = new Intent(HomeActivity.this, CosmeticsActivity.class);
                    startActivity(intenttoproduct);
                }

            }
        });
    }
    private void EventChangeListener(){
        db.collection("Categories").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange dc: value.getDocumentChanges()){
                    if (dc.getType()==DocumentChange.Type.ADDED){
                        productArrayList.add(dc.getDocument().toObject(Product.class));
                    }
                    myAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }
}