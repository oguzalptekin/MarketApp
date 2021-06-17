package com.oguz.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private ArrayAdapter<CharSequence> categoryadapter;
    private ArrayList<CharSequence> categorylist;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        categorySpinner = findViewById(R.id.categorySpinner);
        categorylist = new ArrayList<>();
        fillCategories();
        categoryadapter = new ArrayAdapter<>(this, R.id.categorySpinner, categorylist);
        categoryadapter.setDropDownViewResource(R.id.categorySpinner);
        categorySpinner.setAdapter(categoryadapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void fillCategories(){
        categorylist.add("Snacks");
        categorylist.add("Drinks");
        categorylist.add("Green Grocer");
        categorylist.add("Staple Food");
        categorylist.add("Food");
        categorylist.add("Baker");
        categorylist.add("Pet Food");
        categorylist.add("Technology");
        categorylist.add("Costemtics");
    }
}