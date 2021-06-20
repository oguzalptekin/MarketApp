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

public class OthersActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<Others> othersArrayList;
    OthersAdapter othersAdapter;
    FirebaseFirestore fb;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        recyclerView1= findViewById(R.id.othersrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        othersArrayList = new ArrayList<Others>();
        othersAdapter = new OthersAdapter(OthersActivity.this,othersArrayList);

        recyclerView1.setAdapter(othersAdapter);

        othersAdapter.setOnItemClickListener(new OthersAdapter.OnItemClickListener() {
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
                            ArrayList<Others> temp =(ArrayList<Others>) task.getResult().get("cart");
                            if (!temp.isEmpty()){
                                for (int i=0; i<temp.size(); i++){
                                    Others others = new Others();
                                    List<Map<String,Object>> group = (List<Map<String,Object>>) task.getResult().get("cart");
                                    Map<String,Object> map=group.get(i);
                                    others.productname= (String)map.get("productname");
                                    if (others.productname.equals(othersArrayList.get(position).productname)){
                                        long quantity=(long)map.get("quantity");
                                        others.quantity=((int)quantity)+1;
                                        alreadyinCart=true;
                                    }
                                    else
                                    {
                                        long quantity=(long)map.get("quantity");
                                        others.quantity=((int)quantity);
                                    }
                                    long price =(long) map.get("price");
                                    others.setPrice((int)price);
                                    others.setCategoryname((String)map.get("categoryname"));
                                    cart.add(others);
                                }
                            }
                            if (alreadyinCart==false){
                                Others others = new Others();
                                others.quantity=1;
                                others.setProductname(othersArrayList.get(position).productname);
                                others.setPrice(othersArrayList.get(position).price);
                                others.setCategoryname("Others");
                                cart.add(others);
                            }

                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            fb.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(OthersActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(OthersActivity.this, "Product not added to cart", Toast.LENGTH_LONG).show();
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
        fb.collection("Others").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        othersArrayList.add(doc.getDocument().toObject(Others.class));
                    }

                    othersAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }

            }
        });
    }
}