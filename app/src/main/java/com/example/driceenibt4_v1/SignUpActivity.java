package com.example.driceenibt4_v1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEt,passworlEt1,passworldEt2;
    private Button creationCompte;
    private TextView connextionReturn;
    private ProgressBar mProgressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        //déclaration des variable
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passworlEt1 = findViewById(R.id.passeworl);
        passworldEt2 = findViewById(R.id.confpassworld);
        creationCompte = findViewById(R.id.Connexion);
        connextionReturn = findViewById(R.id.retourconnextion);
       // mProgressBar = new ProgressDialog(this);
        //button
        creationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        connextionReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //fonction qui va enregistrer le profil
    private void Register(){
        String email=emailEt.getText().toString();
        String passworld1=passworlEt1.getText().toString();
        String passworld2=passworldEt2.getText().toString();
        //verification que la création du profile correspond a ce que l'on demade
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(passworld1)){
            passworlEt1.setError("Entrer votre mot de passe");
            return;
        }
        else if(TextUtils.isEmpty(passworld2)){
            passworldEt2.setError("Enter confirme your passeworld");
            return;
        }
        else if(!passworld1.equals(passworld2)){
            passworldEt2.setError("Mot de passe différent");
            return;
        }
        else if(passworld1.length()<5){
            passworlEt1.setError("Mot de passe trop court");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("Mail invalide");
            return;
        }

    }


    private Boolean isVallidEmail(CharSequence email){
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}

