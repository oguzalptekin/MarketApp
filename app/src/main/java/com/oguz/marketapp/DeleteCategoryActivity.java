package com.oguz.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oguz.marketapp.R;

public class DeleteCategoryActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        button = findViewById(R.id.category_delete_button);
    }

    public void deleteCategoryClicked(View view){
        openDialog();
    }

    public void openDialog(){
        CategoryDeleteDialog categoryDeleteDialog = new CategoryDeleteDialog();
        categoryDeleteDialog.show(getSupportFragmentManager(), "Category Delete");
    }
}