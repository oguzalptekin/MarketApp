package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText nameTxt, surnameTxt, emailTxt, passwordTxt, confirm_passwordTxt, phoneTxt;
    Button SignUpBtn, SignInBtn;
    private String name, surname, email, phoneNumber, password, confirm_password;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameTxt = findViewById(R.id.nameTxt);
        surnameTxt = findViewById(R.id.surnameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        confirm_passwordTxt = findViewById(R.id.confirm_passwordTxt);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
    }

    public void SignUpClicked(View view){

        name = nameTxt.getText().toString();
        surname = surnameTxt.getText().toString();
        email = emailTxt.getText().toString();
        phoneNumber = phoneTxt.getText().toString();
        password = passwordTxt.getText().toString();
        confirm_password = confirm_passwordTxt.getText().toString();


        if(name.matches("")){
            nameTxt.setError("Name required");
            nameTxt.requestFocus();
        }
        if(surname.matches("")){
            surnameTxt.setError("Surname required");
            surnameTxt.requestFocus();
        }
        if(email.matches("")){
            emailTxt.setError("E-mail required");
            emailTxt.requestFocus();
        }
        if(phoneNumber.matches("")){
            phoneTxt.setError("Phone number required");
            phoneTxt.requestFocus();
        }
        if(password.matches("")){
            passwordTxt.setError("Password required");
            passwordTxt.requestFocus();
        }
        if(confirm_password.matches("")){
            confirm_passwordTxt.setError("Password required");
            confirm_passwordTxt.requestFocus();
        }
        else{
            if(!(password.equals(confirm_password))){
                Toast.makeText(getApplicationContext(), "Those passwords didn’t match. Try again.", Toast.LENGTH_LONG).show();
            }
            else{
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Customer customer = new Customer(name, surname, email, phoneNumber,firebaseAuth.getCurrentUser().getUid());
                        System.out.println(customer.getPhoneNumber());
                        HashMap<String, Object>  customerData = new HashMap<>();
                        customerData.put("customer", customer);
                        firebaseFirestore.collection("Customers").document(firebaseAuth.getCurrentUser().getUid()).set(customer)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_LONG).show();
                                        Intent intentHome = new Intent(SignUpActivity.this, LoadingScreen.class);
                                        //intentHome.putExtra("username", customer.getFirstName());
                                        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intentHome);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                        /*firebaseFirestore.collection("Customers").add(customerData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_LONG).show();
                                Intent intentHome = new Intent(SignUpActivity.this, LoadingScreen.class);
                                //intentHome.putExtra("username", customer.getFirstName());
                                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentHome);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                            }
                        });*/
                        //Toast.makeText(SignUpActivity.this, "User created", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }


    public void SignInClicked(View view){
        Intent intentback = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intentback);
        finish();
    }
}