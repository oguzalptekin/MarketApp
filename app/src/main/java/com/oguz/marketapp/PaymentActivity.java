package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    EditText cardHolderName,cardNum,cardLimit,cardDate,cvv;

    ImageButton creditCard,cashPay;

    private String name,num,limit,date,cvvNum;


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardHolderName = findViewById(R.id.cardholdertxt);
        cardNum=findViewById(R.id.cardnumbertxt);
        cardLimit=findViewById(R.id.cardlimittxt);
        cardDate=findViewById(R.id.expireDate);
        cvv=findViewById(R.id.cvv);
        creditCard=findViewById(R.id.cardbtn);
        cashPay=findViewById(R.id.cashbtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=cardHolderName.getText().toString();
                num=cardNum.getText().toString();
                limit=cardLimit.getText().toString();
                date=cardDate.getText().toString();
                cvvNum=cvv.getText().toString();


                int cardLim=Integer.parseInt(limit);

                if (name.matches("")){
                    cardHolderName.setError("Name required");
                    cardHolderName.requestFocus();
                }
                if(num.matches("")){
                    cardNum.setError("Card Number required");
                    cardNum.requestFocus();
                }
                else if(num.length()!=16){
                    cardNum.setError("Card Number must be 16 digits");
                    cardNum.requestFocus();
                }
                if(limit.matches("")){
                    cardLimit.setError("Limit Required");
                    cardLimit.requestFocus();
                }
                else if(cardLim<500){
                    cardLimit.setError("Limit must be at least 500 TL");
                    cardLimit.requestFocus();
                }
                if(date.matches("")){
                    cardDate.setError("Date Required");
                    cardDate.requestFocus();
                }
                else if (date.charAt(2)!='/'){
                    cardDate.setError("Please use '/ between YY and MMMM");
                    cardDate.requestFocus();
                }
                else{
                    String yyyy=date.substring(3);
                    String mm=date.substring(0,2);
                    int year=Integer.parseInt(yyyy);
                    int month= Integer.parseInt(mm);
                    if (year<2025){
                        cardDate.setError("Expiration year must be at least 2025");
                        cardDate.requestFocus();
                    }
                    if(month>12 || month<1){
                        cardDate.setError("Invalid month format");
                        cardDate.requestFocus();
                    }
                }
                if(cvvNum.matches("")){
                    cvv.setError("Cvv Required");
                    cvv.requestFocus();
                }
                else if(cvvNum.length()!=3){
                    cvv.setError("Cvv must be 3 digits");
                    cvv.requestFocus();
                }
                CreditCard creditCard=new CreditCard(name,num,cardLim,date,cvvNum);

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String currentUid = currentUser.getUid();
                DocumentReference reference;
                reference=firebaseFirestore.collection("Customers").document(currentUid);
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            ArrayList<Product> cart = new ArrayList<>();
                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),creditCard,
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            firebaseFirestore.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(PaymentActivity.this, "Your order is being prepared...", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PaymentActivity.this, "Error...Please try again later", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });
        cashPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String currentUid = currentUser.getUid();
                DocumentReference reference;
                reference=firebaseFirestore.collection("Customers").document(currentUid);
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            ArrayList<Product> cart = new ArrayList<>();
                            Customer customer = new Customer(task.getResult().getString("firstName"),
                                    task.getResult().getString("lastName"), task.getResult().getString("email"),
                                    task.getResult().getString("phoneNumber"), cart, currentUid);
                            firebaseFirestore.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(PaymentActivity.this, "Your order is being prepared...", Toast.LENGTH_LONG).show();

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                                            startActivity(intent);
                                        }
                                    }, 1500);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PaymentActivity.this, "Error...Please try again later", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}