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

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ImageView logo;
    EditText emailTxt, passwordTxt;
    Button SignInBtn, SignUpBtn, forgetBtn;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo_imageview);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        forgetBtn = findViewById(R.id.forgetBtn);
        SignInBtn = findViewById(R.id.SignInBtn);
        SignUpBtn = findViewById(R.id.SignUpBtn);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){
            /*System.out.println(firebaseAuth.getCurrentUser().getEmail());
            Intent homeintent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeintent);
            finish();*/
        }
    }

    public void SignUpClicked(View view){
        Intent intenttoSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intenttoSignUp);
        finish();
    }

    public void SignInClicked(View view){

        email = emailTxt.getText().toString();
        password = passwordTxt.getText().toString();

        if(email.matches("")){
            emailTxt.setError("E-mail required");
            emailTxt.requestFocus();
        }
        if(password.matches("")){
            passwordTxt.setError("Password required");
            passwordTxt.requestFocus();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intentHome = new Intent(LoginActivity.this,HomeActivity.class);
                    //intentHome.putExtra("username",  .getFirstName());
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentHome);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

        }


    }

    public void forgetClicked(View view){

    }
}