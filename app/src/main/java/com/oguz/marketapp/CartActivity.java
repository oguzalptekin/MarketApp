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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> productArrayList;
    CartAdapter cartAdapter;
    FirebaseFirestore db;
    Button paymentbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        paymentbutton = findViewById(R.id.Paymentbtn);
        paymentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
        BuildRecyclerView();

        EventChangeListener();

    }
    private void BuildRecyclerView() {
        recyclerView = findViewById(R.id.cartrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        productArrayList = new ArrayList<Product>();
        cartAdapter = new CartAdapter(CartActivity.this, productArrayList);

        recyclerView.setAdapter(cartAdapter);

        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void OnDeleteClick(int position) {
                productArrayList.remove(position);
                ComputeTotalPrice();
                updatefb();
                cartAdapter.notifyItemRemoved(position);
            }

            @Override
            public void OnIncClick(int position) {
                productArrayList.get(position).quantity=productArrayList.get(position).quantity+1;
                cartAdapter.notifyItemChanged(position);
                ComputeTotalPrice();
                updatefb();
            }

            @Override
            public void OnDecClick(int position) {
                productArrayList.get(position).quantity=productArrayList.get(position).quantity-1;
                ComputeTotalPrice();
                updatefb();
                cartAdapter.notifyItemChanged(position);
                if (productArrayList.get(position).quantity==0){
                    productArrayList.remove(position);
                    updatefb();
                    cartAdapter.notifyItemRemoved(position);
                }
            }
        });
    }
    private void ComputeTotalPrice(){
        TextView pricetext=(TextView)findViewById(R.id.TotalPricetext);
        int totalprice=0;
        for (int i=0;i<productArrayList.size();i++){
            totalprice+=productArrayList.get(i).quantity*productArrayList.get(i).price;
        }
        pricetext.setText(totalprice+"₺");
    }
    private void updatefb(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUid = currentUser.getUid();
        DocumentReference reference;
        reference=db.collection("Customers").document(currentUid);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {

                    ArrayList<Product> tempAr=new ArrayList<>();
                    for (int i=0;i<productArrayList.size();i++){
                        Snacks snacks=new Snacks();
                        snacks.productname=productArrayList.get(i).categoryname;
                        snacks.price=productArrayList.get(i).price;
                        snacks.quantity=productArrayList.get(i).quantity;
                        tempAr.add(snacks);
                    }
                    Customer customer = new Customer(task.getResult().getString("firstName"),
                            task.getResult().getString("lastName"), task.getResult().getString("email"),
                            task.getResult().getString("phoneNumber"),tempAr , currentUid);
                    db.collection("Customers").document(currentUid).set(customer);
                }
            }
        });
    }
    private void EventChangeListener(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUid = currentUser.getUid();
        DocumentReference reference;
        reference=db.collection("Customers").document(currentUid);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    ArrayList<Product> temp = (ArrayList<Product>) task.getResult().get("cart");
                    if (!temp.isEmpty()) {
                        for (int i = 0; i < temp.size(); i++) {
                            Product product = new Product();
                            List<Map<String, Object>> group = (List<Map<String, Object>>) task.getResult().get("cart");
                            Map<String, Object> map = group.get(i);
                            product.categoryname= (String)map.get("productname");
                            long quantity=(long)map.get("quantity");
                            product.quantity=((int)quantity);
                            long price =(long) map.get("price");
                            product.setPrice((int)price);
                            productArrayList.add(product);
                            cartAdapter.notifyDataSetChanged();
                        }
                        ComputeTotalPrice();
                    }
                }
            }
        });
    }
}