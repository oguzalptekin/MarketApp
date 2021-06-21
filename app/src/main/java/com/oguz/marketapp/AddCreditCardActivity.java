package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class AddCreditCardActivity extends AppCompatActivity {

    EditText namesurname, cardno, month, year, cvc;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card);

        namesurname = findViewById(R.id.namesurname);
        cardno = findViewById(R.id.cardno);
        month = findViewById(R.id.monthtxt);
        year = findViewById(R.id.daytxt);
        cvc = findViewById(R.id.cvctxt);
    }

    public void addtoUserClicked(View view){

        int monthDate = Integer.parseInt(month.getText().toString());
        int yearDate = Integer.parseInt(year.getText().toString());
        int cardNumber = Integer.parseInt(cardno.getText().toString());
        String owner = namesurname.getText().toString();
        int cvcNo = Integer.parseInt(cvc.getText().toString());


        Date date = new Date(monthDate, yearDate);
        CreditCard creditCard = new CreditCard(owner, cardNumber, date, cvcNo);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUid = currentUser.getUid();
        DocumentReference reference;
        reference=firebaseFirestore.collection("Customers").document(currentUid);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    Customer customer = new Customer(task.getResult().getString("firstName"),
                            task.getResult().getString("lastName"), task.getResult().getString("email"),
                            task.getResult().getString("phoneNumber"), creditCard, currentUid);

                    firebaseFirestore.collection("Customers").document(currentUid).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddCreditCardActivity.this, "Credit Card added successfully", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddCreditCardActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}