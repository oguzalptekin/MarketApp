package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        recyclerView1= findViewById(R.id.snackrecycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        fb = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        snacksArrayList = new ArrayList<Snacks>();
        snackAdapter = new SnackAdapter(SnackActivity.this,snacksArrayList);

        recyclerView1.setAdapter(snackAdapter);

        snackAdapter.setOnItemClickListener(new SnackAdapter.OnItemClickListener() {

            String name,surname,email,phoneNumber;
            @Override
            public void OnItemClick(int position) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    final String currentUserEmail = currentUser.getEmail();
                    fb.collection("Customers").whereEqualTo("email",currentUserEmail).get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot document : task.getResult()) {
                                    name=document.get("firstName").toString();
                                    surname=document.get("lastName").toString();
                                    email=document.get("email").toString();
                                    phoneNumber=document.get("phoneNumber").toString();
                                }
                            }
                        }
                    });
                    /*Map<String, Product> cart = new HashMap<>();
                    cart.put(cart,)
                    Customer customer = new Customer(name, surname, email, phoneNumber,cart);

                    fb.collection("Customers").document(firebaseAuth.getCurrentUser().getUid()).update("customer",cart)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SnackActivity.this, "Product Added to Cart Successfully", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SnackActivity.this, "Sorry there is a mistake", Toast.LENGTH_LONG).show();
                        }
                    });*/
            }
        });

        EventChangeListener();

    }
    private void EventChangeListener(){
        fb.collection("Snacks").addSnapshotListener(new EventListener<QuerySnapshot>() {
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