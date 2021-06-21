package com.oguz.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oguz.marketapp.R;

public class EditCategoryActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        button = findViewById(R.id.category_edit_button);
    }

    public void editCategoryClicked(View view){
        openDialog();
    }

    public void openDialog(){
        CategoryEditDialog categoryEditDialog = new CategoryEditDialog();
        categoryEditDialog.show(getSupportFragmentManager(), "Category Edit");
    }
}