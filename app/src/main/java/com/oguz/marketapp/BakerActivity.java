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

public class BakerActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<Baker> bakerArrayList;
    BakerAdapter bakerAdapter;
    FirebaseFirestore fb;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker);

        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView1= findViewById(R.id.bakerrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        bakerArrayList = new ArrayList<Baker>();
        bakerAdapter = new BakerAdapter(BakerActivity.this,bakerArrayList);

        recyclerView1.setAdapter(bakerAdapter);

        bakerAdapter.setOnItemClickListener(new BakerAdapter.OnItemClickListener() {
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
                            ArrayList<Baker> temp =(ArrayList<Baker>) task.getResult().get("cart");
                            if (!temp.isEmpty()){
                                for (int i=0; i<temp.size(); i++){
                                    Baker baker=new Baker();
                                    List<Map<String,Object>> group = (List<Map<String,Object>>) task.getResult().get("cart");
                                    Map<String,Object> map=group.get(i);
                                    baker.productname= (String)map.get("productname");
                                    if (baker.productname.equals(bakerArrayList.get(position).productname)){
                                        long quantity=(long)map.get("quantity");
                                        baker.quantity=((int)quantity)+1;
                                        alreadyinCart=true;
                                    }
                                    else
                                    {
                                        long quantity=(long)map.get("quantity");
                                        baker.quantity=((int)quantity);
                                    }
                                    long price =(long) map.get("price");
                                    baker.setPrice((int)price);
                                    baker.setCategoryname((String)map.get("categoryname"));
                                    cart.add(baker);
                                }
                            }
                            if (alreadyinCart==false){
                                Baker baker=new Baker();
                                baker.quantity=1;
                                baker.setProductname(bakerArrayList.get(position).productname);
                                baker.setPrice(bakerArrayList.get(position).price);
                                baker.setCategoryname("Baker");
                                cart.add(baker);
                            }

                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            fb.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(BakerActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(BakerActivity.this, "Product not added to cart", Toast.LENGTH_LONG).show();
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
        fb.collection("Baker").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        bakerArrayList.add(doc.getDocument().toObject(Baker.class));
                    }

                    bakerAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }

            }
        });
    }
}