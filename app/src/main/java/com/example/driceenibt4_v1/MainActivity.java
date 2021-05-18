package com.example.driceenibt4_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity{

    //declaration des variable utilisé dans le code
    private EditText emailEt,passwolrdEt;
    private Button connexionButton;
    private TextView creationCompteTv;
    private TextView forgetmdpTv;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEt = findViewById(R.id.email);
        passwolrdEt = findViewById(R.id.passeworl);
        connexionButton = findViewById(R.id.Connexion);
        creationCompteTv = findViewById(R.id.joinuS);
        forgetmdpTv = findViewById(R.id.forgetPassworld);
        mProgressBar = new ProgressBar(this);
        //action des button et liens
        creationCompteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }






    //fonction qui permait de rentrer dans l'application
    private void Login() {
    }
}