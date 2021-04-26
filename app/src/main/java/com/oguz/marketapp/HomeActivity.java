package com.oguz.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTxt, welcomeNameTxt;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTxt = findViewById(R.id.welcomeTxt);
        welcomeNameTxt = findViewById(R.id.welcomeNameTxt);
        //username = getIntent().getStringExtra("username");


        welcomeNameTxt.setText(username);

    }
}