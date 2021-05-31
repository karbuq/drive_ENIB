package com.example.driceenibt4_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.SaveRequest;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity{

    //declaration des variable utilisé dans le code
    private EditText emailEt,passworlddEt;
    private Button connexionButton;
    private TextView creationCompteTv;
    private TextView forgetmdpTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEt = findViewById(R.id.email);
        passworlddEt = findViewById(R.id.passeworl);
        connexionButton = findViewById(R.id.Connexion);
        creationCompteTv = findViewById(R.id.joinuS);
        forgetmdpTv = findViewById(R.id.forgetPassworld);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        //action des button et liens
        //passage a l'activité création de compte
        creationCompteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login();
                Register();
            }
        });

        forgetmdpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"va voir tes mail si on la coder...",Toast.LENGTH_SHORT).show();
            }
        });

    }






    //fonction qui permait de rentrer dans l'application
    private void Login() {
        String email=emailEt.getText().toString();
        String passworld1=passworlddEt.getText().toString();


        //verification que la création du profile correspond a ce que l'on demade
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(passworld1)){
            passworlddEt.setError("Entrer votre mot de passe");
            return;
        }//else{
           // Register();
        //}

        //boite de dialoque
        progressDialog.setMessage("Patientez svp");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth.signInWithEmailAndPassword(email,passworld1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){



                    Toast.makeText(MainActivity.this,"Succés",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(MainActivity.this, MenuActivty.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"Echec de l'authentification'",Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }

        });
    }
    //verification que la basse de donnée à été créer
    //nécessaire pour la suite de l'apli
    private void Register() {
        final String userEnterEmail=emailEt.getText().toString().trim();
        final String userEnterPassworld=passworlddEt.getText().toString().trim();



        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Utilisateur");

        Query ckeckEmail=reference.orderByChild("email").equalTo(userEnterEmail);

        ckeckEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override//verf1
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){//verf2

                    emailEt.setError(null);
                    emailEt.setEnabled(false);

                    String passwordDB=snapshot.child(userEnterPassworld).child("passeworld").getValue(String.class);

                    if(passwordDB.equals(userEnterPassworld)){
                        emailEt.setError(null);
                        emailEt.setEnabled(false);
                        //recuperation de la base de doné

                        String nameDB=snapshot.child(userEnterPassworld).child("prenom").getValue(String.class);
                        String emailDB=snapshot.child(userEnterPassworld).child("email").getValue(String.class);
                        String numTelDB=snapshot.child(userEnterPassworld).child("numTel").getValue(String.class);
                        String ageDB=snapshot.child(userEnterPassworld).child("age").getValue(String.class);

                        Toast.makeText(MainActivity.this,"Echec de l'authentification'",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MainActivity.this, MenuActivty.class);

                        intent.putExtra("prenom",nameDB);
                        intent.putExtra("email",emailDB);
                        intent.putExtra("numTel",numTelDB);
                        intent.putExtra("age",ageDB);

                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"ici",Toast.LENGTH_LONG).show();
                    }
                    else{
                        passworlddEt.setError("Wrong Passeworld");
                        passworlddEt.requestFocus();
                    }
                }
                else{
                    emailEt.setError("No such email exist");
                    emailEt.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}