package com.example.driceenibt4_v1;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ProfilActivity extends AppCompatActivity {

    private static final String TAG = "ProfilActivity";

    private TextView retourTv;
    private EditText email,nom,telephone,age,mdp;
    private Button confirmatinBt;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        retourTv=findViewById(R.id.reto);
        confirmatinBt=findViewById(R.id.confirmbt);

        //hooks
        email=findViewById(R.id.email);
        nom=findViewById(R.id.pseudo);
        telephone=findViewById(R.id.telephone);
        age=findViewById(R.id.age);
        mdp=findViewById(R.id.mdp1);

        //Showdata
        showAllUserData();

        retourTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        confirmatinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this, AccueilActivity.class);
                startActivity(intent);
                finish();

            }


    });



    }

    private void showAllUserData() {
        Intent intent=getIntent();
        String user_prenom=intent.getStringExtra("prenom");
        String user_email=intent.getStringExtra("email");
        String user_numtel=intent.getStringExtra("numTel");
        String user_age=intent.getStringExtra("age");

        email.setHint(user_email);
        nom.setHint(user_prenom);
        telephone.setHint(user_numtel);
        age.setHint(user_age);

    }


}


