package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<Petfood> petfoodArrayList;
    PetfoodAdapter petfoodAdapter;
    FirebaseFirestore fb;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_food);

        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        recyclerView1= findViewById(R.id.petfoodrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        petfoodArrayList = new ArrayList<Petfood>();
        petfoodAdapter = new PetfoodAdapter(PetFoodActivity.this,petfoodArrayList);

        recyclerView1.setAdapter(petfoodAdapter);

        petfoodAdapter.setOnItemClickListener(new PetfoodAdapter.OnItemClickListener() {
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
                            ArrayList<Petfood> temp =(ArrayList<Petfood>) task.getResult().get("cart");
                            if (!temp.isEmpty()){
                                for (int i=0; i<temp.size(); i++){
                                    Petfood petfood=new Petfood();
                                    List<Map<String,Object>> group = (List<Map<String,Object>>) task.getResult().get("cart");
                                    Map<String,Object> map=group.get(i);
                                    petfood.productname= (String)map.get("productname");
                                    if (petfood.productname.equals(petfoodArrayList.get(position).productname)){
                                        long quantity=(long)map.get("quantity");
                                        petfood.quantity=((int)quantity)+1;
                                        alreadyinCart=true;
                                    }
                                    else
                                    {
                                        long quantity=(long)map.get("quantity");
                                        petfood.quantity=((int)quantity);
                                    }
                                    long price =(long) map.get("price");
                                    petfood.setPrice((int)price);
                                    petfood.setCategoryname((String)map.get("categoryname"));
                                    cart.add(petfood);
                                }
                            }
                            if (alreadyinCart==false){
                                Petfood petfood=new Petfood();
                                petfood.quantity=1;
                                petfood.setProductname(petfoodArrayList.get(position).productname);
                                petfood.setPrice(petfoodArrayList.get(position).price);
                                petfood.setCategoryname("Pet Food");
                                cart.add(petfood);
                            }

                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            fb.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(PetFoodActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PetFoodActivity.this, "Product not added to cart", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        EventChangeListener();
    }
    private void EventChangeListener(){
        fb.collection("Pet Food").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firestore error",error.getMessage());
                    return;
                }
                for (DocumentChange doc : value.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        petfoodArrayList.add(doc.getDocument().toObject(Petfood.class));
                    }

                    petfoodAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }

            }
        });
    }
}