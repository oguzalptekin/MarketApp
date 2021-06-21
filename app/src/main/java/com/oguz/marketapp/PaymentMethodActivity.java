package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentMethodActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CreditCard> cardlist;
    PaymentAdapter paymentAdapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        BuildRecyclerView();
    }

    private void BuildRecyclerView() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String currentUid = user.getUid();
        DocumentReference reference;
        reference=firebaseFirestore.collection("Customers").document(currentUid);

        recyclerView = findViewById(R.id.paymentrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        cardlist = new ArrayList<CreditCard>();
        paymentAdapter = new PaymentAdapter(PaymentMethodActivity.this, cardlist);

        recyclerView.setAdapter(paymentAdapter);

        paymentAdapter.setOnItemClickListener(new PaymentAdapter.OnItemClickListener() {
            @Override
            public void OnDeleteClick(int position) {
                //delete



                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"),cardlist.get(0), currentUid);
                            firebaseFirestore.collection("Customers").document(currentUid).set(customer);
                        }
                    }
                });
                paymentAdapter.notifyItemRemoved(position);
            }
        });
    }


    public void addCardClicked(View view){
        startActivity(new Intent(PaymentMethodActivity.this, AddCreditCardActivity.class));
    }
}