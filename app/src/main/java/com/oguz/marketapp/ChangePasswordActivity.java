package com.oguz.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText currPass, newPass, confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currPass = findViewById(R.id.currPass);
        newPass = findViewById(R.id.newPass);
        confPass = findViewById(R.id.confPass);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void changePasswordClick(View view){

        System.out.println("tıklandı");

        String current = currPass.getText().toString().trim();
        String newone = newPass.getText().toString().trim();
        String confirm = confPass.getText().toString().trim();

        if(TextUtils.isEmpty(current)){
            currPass.setError("Can't be left blank");
            currPass.requestFocus();
        }
        if(TextUtils.isEmpty(newone)){
            newPass.setError("Can't be left blank");
            newPass.requestFocus();
        }
        if(TextUtils.isEmpty(confirm)){
            confPass.setError("Can't be left blank");
            confPass.requestFocus();
        }
        else{
            if(!(current.matches(confirm))){
                Toast.makeText(getApplicationContext(), "Those passwords didn’t match. Try again.", Toast.LENGTH_LONG).show();
            }
            else {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), current);
                user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        user.updatePassword(newone).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ChangePasswordActivity.this, "Your password updated successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ChangePasswordActivity.this, HomeActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("değişemedi");
                                Toast.makeText(ChangePasswordActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("credential hatası");
                        Toast.makeText(ChangePasswordActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}