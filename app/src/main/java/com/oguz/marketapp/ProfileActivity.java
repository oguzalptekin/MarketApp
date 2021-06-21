package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView1;
    private ArrayList<Snacks> snacksArrayList;
    private SnackAdapter snackAdapter;
    private FirebaseFirestore fb;
    private ProgressDialog progressDialog;

    BottomNavigationView bottomNavigationView;
    LinearLayout myAddress, myFavourites, orderHistory, paymentMethod, changePassword, help, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myAddress = findViewById(R.id.myAddress);
        myFavourites = findViewById(R.id.myFavourites);
        orderHistory = findViewById(R.id.orderHistory);
        paymentMethod = findViewById(R.id.paymentMethod);
        changePassword = findViewById(R.id.changePassword);
        help = findViewById(R.id.help);
        logOut = findViewById(R.id.logOut);



        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.profile_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart_navigation:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        return true;
                    case R.id.home_navigation:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        return true;
                    case R.id.profile_navigation:
                        return true;
                }
                return false;
            }
        });
    }

    public void myAddressClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    public void myFavouritesClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    public void orderHistoryClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    public void paymentMethodClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    public void changePasswordClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }
    public void helpClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HelpActivity.class);
        startActivity(intent);
    }
    public void logOutClicked(View view) {
        // Show a toast message.
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}