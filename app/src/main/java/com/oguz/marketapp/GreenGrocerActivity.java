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

public class GreenGrocerActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<GreenGrocer> greenGrocerArrayList;
    GreenGrocerAdapter greenGrocerAdapter;
    FirebaseFirestore fb;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_grocer);

        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        recyclerView1= findViewById(R.id.grocerrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        greenGrocerArrayList = new ArrayList<GreenGrocer>();
        greenGrocerAdapter = new GreenGrocerAdapter(GreenGrocerActivity.this,greenGrocerArrayList);

        recyclerView1.setAdapter(greenGrocerAdapter);

        greenGrocerAdapter.setOnItemClickListener(new GreenGrocerAdapter.OnItemClickListener() {
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
                            ArrayList<GreenGrocer> temp =(ArrayList<GreenGrocer>) task.getResult().get("cart");
                            if (temp!=null && !temp.isEmpty()){
                                for (int i=0; i<temp.size(); i++){
                                    GreenGrocer greenGrocer = new GreenGrocer();
                                    List<Map<String,Object>> group = (List<Map<String,Object>>) task.getResult().get("cart");
                                    Map<String,Object> map=group.get(i);
                                        greenGrocer.productname= (String)map.get("productname");
                                        if (greenGrocer.productname.equals(greenGrocerArrayList.get(position).productname)){
                                            long quantity=(long)map.get("quantity");
                                            greenGrocer.quantity=((int)quantity)+1;
                                            alreadyinCart=true;
                                        }
                                        else{
                                            long quantity=(long)map.get("quantity");
                                            greenGrocer.quantity=((int)quantity);
                                        }
                                        long price =(long) map.get("price");
                                        greenGrocer.setPrice((int)price);
                                        greenGrocer.setCategoryname((String)map.get("categoryname"));
                                        cart.add(greenGrocer);
                                    }
                            }
                            if (alreadyinCart==false){
                                GreenGrocer greenGrocer = new GreenGrocer();
                                greenGrocer.quantity=1;
                                greenGrocer.setProductname(greenGrocerArrayList.get(position).productname);
                                greenGrocer.setPrice(greenGrocerArrayList.get(position).price);
                                greenGrocer.setCategoryname("Green Grocer");
                                cart.add(greenGrocer);
                            }

                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            fb.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(GreenGrocerActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(GreenGrocerActivity.this, "Product not added to cart", Toast.LENGTH_LONG).show();
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
        fb.collection("Green Grocer").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        greenGrocerArrayList.add(doc.getDocument().toObject(GreenGrocer.class));
                    }

                    greenGrocerAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }

            }
        });
    }
}