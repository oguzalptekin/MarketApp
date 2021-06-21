package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.oguz.marketapp.R;

import java.sql.SQLOutput;
import java.util.HashMap;

public class AddProductActivity extends AppCompatActivity {

    private EditText addProduct_categoryName, addProduct_type, addProduct_productName, addProduct_price, addProduct_stock, special1, special2, special3;
    FirebaseFirestore firebaseFirestore;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        addProduct_categoryName = findViewById(R.id.addProduct_categoryName);
        addProduct_type = findViewById(R.id.addProduct_type);
        addProduct_productName = findViewById(R.id.addProduct_productName);
        addProduct_price = findViewById(R.id.addProduct_price);
        addProduct_stock = findViewById(R.id.addProduct_stock);
        special1 = findViewById(R.id.special1);
        special2 = findViewById(R.id.special2);
        special3 = findViewById(R.id.special3);
        firebaseFirestore = FirebaseFirestore.getInstance();
        button = findViewById(R.id.product_add_button);

        addProduct_categoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equalsIgnoreCase("Snacks")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Calory");
                    special2.setHint("Weight");
                    special3.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                    special2.setVisibility(View.VISIBLE);
                    special3.setVisibility(View.VISIBLE);
                    Toast.makeText(AddProductActivity.this, "Test", Toast.LENGTH_LONG).show();
                }
                else if(s.toString().equalsIgnoreCase("Drinks")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Liter");
                    special2.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                    special2.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Green Grocer")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Weight");
                    special1.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Staple Food")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Brand");
                    special2.setHint("Weight");
                    special1.setVisibility(View.VISIBLE);
                    special2.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Food")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Baker")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Weight");
                    special1.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Pet Food")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Animal");
                    special2.setHint("Weight");
                    special3.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                    special2.setVisibility(View.VISIBLE);
                    special3.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Cosmetics")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Gender");
                    special2.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                    special2.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Technology")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                }
                else if(s.toString().equalsIgnoreCase("Others")){
                    special1.setVisibility(View.INVISIBLE); special2.setVisibility(View.INVISIBLE); special3.setVisibility(View.INVISIBLE);
                    special1.setHint("Brand");
                    special1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        System.out.println("burası");
    }

    public void addProductClicked(View view){

        System.out.println("tıkk");

        HashMap<String, Object> product = new HashMap<>();

        String category = addProduct_categoryName.getText().toString();
        System.out.println(category);
        String type = addProduct_type.getText().toString();
        System.out.println(type);
        int price = Integer.parseInt(addProduct_price.getText().toString());
        int stock = Integer.parseInt(addProduct_stock.getText().toString());
        String name = addProduct_productName.getText().toString();

        if(category.matches("Snacks")){
            category = "Snacks";
            int calory = Integer.parseInt(special1.getText().toString());
            int weight = Integer.parseInt(special2.getText().toString());
            String brand = special3.getText().toString();
            Snacks snack = new Snacks(category, type, price, stock, 1, name, calory, weight, brand);
            product.put("Snacks", snack);
            System.out.println("?");
        }
        else if(category.matches("Drinks")){
            category = "Drinks";
            int liter = Integer.parseInt(special1.getText().toString());
            String brand = special2.getText().toString();
            Drinks drink = new Drinks(category, type, price, stock, 1, name,liter, brand);
            product.put("Drinks", drink);
        }
        else if(category.matches("Green Grocer")){
            category = "GreenGrocer";
            int weight = Integer.parseInt(special1.getText().toString());
            GreenGrocer greenGrocer = new GreenGrocer(category, type, price, stock, 1, name,weight);
            product.put("Green Grocer", greenGrocer);
        }
        else if(category.matches("Staple Food")){
            category = "StapleFood";
            String brand = special1.getText().toString();
            int weight = Integer.parseInt(special2.getText().toString());
            StapleFood stapleFood = new StapleFood(category, type, price, stock, 1, name,brand, weight);
            product.put("customer", stapleFood);
        }
        else if(category.matches("Food")){
            category = "Food";
            String brand = special1.getText().toString();
            Food food = new Food(category, type, price, stock, 1, name,brand);
            product.put("Staple Food", food);
        }
        else if(category.matches("Baker")){
            category = "Baker";
            int weight = Integer.parseInt(special1.getText().toString());
            Baker baker = new Baker(category, type, price, stock, 1, name, weight);
            product.put("Baker", baker);
        }
        else if(category.matches("Pet Food")){
            category = "Petfood";
            String animal = special1.getText().toString();
            int weight = Integer.parseInt(special2.getText().toString());
            String brand = special3.getText().toString();
            Petfood petfood = new Petfood(category, type, price, stock, 1, name,animal, weight, brand);
            product.put("Pet Food", petfood);
        }
        else if(category.matches("Cosmetics")){
            category = "Cosmetics";
            String gender = special1.getText().toString();
            String brand = special2.getText().toString();
            Cosmetics cosmetics = new Cosmetics(category, type, price, stock, 1, name, gender, brand);
            product.put("Cosmetics", cosmetics);
        }
        else if(category.matches("Technology")){
            category = "Technology";
            String brand = special1.getText().toString();
            Technology technology = new Technology(category, type, price, stock, 1, name, brand);
            product.put("Technology", technology);
        }
        else if(category.matches("Others")){
            category = "Others";
            String brand = special1.getText().toString();
            Others other = new Others(category, type, price, stock, 1, name, brand);
            product.put("Others", other);
        }

        //System.out.println(product.isEmpty());
        firebaseFirestore.collection(category).document("Test")
                .set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("eklendi");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("ADD", "Error ", e);
            }
        });
    }
}