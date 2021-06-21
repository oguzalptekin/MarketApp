package com.oguz.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oguz.marketapp.R;

public class AddCategoryActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        button = findViewById(R.id.category_add_button);
    }

    public void AddCategoryClicked(View view){
        openDialog();
    }

    public void openDialog(){
        CategoryAddDialog categoryAddDialog = new CategoryAddDialog();
        categoryAddDialog.show(getSupportFragmentManager(), "Category Add");
    }
}