package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SnackActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView1;
    ArrayList<Snacks> snacksArrayList;
    SnackAdapter snackAdapter;
    FirebaseFirestore fb;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView1 = findViewById(R.id.snackrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        snacksArrayList = new ArrayList<Snacks>();
        snackAdapter = new SnackAdapter(SnackActivity.this, snacksArrayList);

        recyclerView1.setAdapter(snackAdapter);

        snackAdapter.setOnItemClickListener(new SnackAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String currentUid = currentUser.getUid();
                DocumentReference reference;
                reference=fb.collection("Customers").document(currentUid);
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            boolean alreadyinCart=false;
                            //String name = task.getResult().getString("firstName");
                            ArrayList<Product> cart = new ArrayList<>();
                            ArrayList<Snacks> temp =(ArrayList<Snacks>) task.getResult().get("cart");
                            if (temp!=null && !temp.isEmpty()){
                                for (int i=0; i<temp.size(); i++){
                                    Snacks snacks = new Snacks();
                                    List<Map<String,Object>> group = (List<Map<String,Object>>) task.getResult().get("cart");
                                    Map<String,Object> map=group.get(i);
                                        snacks.productname= (String)map.get("productname");
                                        if (snacks.productname.equals(snacksArrayList.get(position).productname)){
                                            long quantity=(long)map.get("quantity");
                                            snacks.quantity=((int)quantity)+1;
                                            alreadyinCart=true;
                                        }
                                        else
                                        {
                                            long quantity=(long)map.get("quantity");
                                            snacks.quantity=((int)quantity);
                                        }
                                        long price =(long) map.get("price");
                                        snacks.setPrice((int)price);
                                        snacks.setCategoryname((String)map.get("categoryname"));
                                        cart.add(snacks);
                                    }
                            }
                            if (alreadyinCart==false){
                                Snacks snacks = new Snacks();
                                snacks.quantity=1;
                                snacks.setProductname(snacksArrayList.get(position).productname);
                                snacks.setPrice(snacksArrayList.get(position).price);
                                snacks.setCategoryname("Snacks");
                                cart.add(snacks);
                            }

                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            fb.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SnackActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SnackActivity.this, "Product not added to cart", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });
        EventChangeListener();

    }

    private void EventChangeListener() {
        fb.collection("Snacks").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange doc : value.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        snacksArrayList.add(doc.getDocument().toObject(Snacks.class));
                    }

                    snackAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }

            }
        });
    }
}