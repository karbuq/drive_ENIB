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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEt,pseudoET,telephoneET,ageET,mdp1ET,mdp2ET;
    private Button creationCompteBT;
    private TextView connextionReturn;
  //  private ProgressBar mProgressBar;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        //déclaration des variable
        firebaseAuth = FirebaseAuth.getInstance();

        emailEt = findViewById(R.id.email);
        pseudoET=findViewById(R.id.pseudo);
        telephoneET=findViewById(R.id.telephone);
        ageET=findViewById(R.id.age);
        mdp1ET=findViewById(R.id.mdp1);
        mdp2ET=findViewById(R.id.mdp2);

        creationCompteBT=findViewById(R.id.creation);

        connextionReturn = findViewById(R.id.reto);
        mProgressDialog =new  ProgressDialog(this);

        //button
        creationCompteBT.setOnClickListener(new View.OnClickListener() {
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
        String prenom=pseudoET.getText().toString();
        String numtel=telephoneET.getText().toString();
        String age=ageET.getText().toString();
        String passworld1=mdp1ET.getText().toString();
        String passworld2=mdp2ET.getText().toString();


        //verification que la création du profile correspond a ce que l'on demade
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(prenom)){
            pseudoET.setError("Entrer votre mot de passe");
            return;
        }
        else if(TextUtils.isEmpty(numtel)){
            telephoneET.setError("Entrer votre mot de passe");
            return;
        }
        else if(TextUtils.isEmpty(age)){
            ageET.setError("Entrer votre mot de passe");
            return;
        }

        else if(TextUtils.isEmpty(passworld1)){
            mdp1ET.setError("Entrer votre mot de passe");
            return;
        }
        else if(TextUtils.isEmpty(passworld2)){
            mdp2ET.setError("Confimer votre mot de passe");
            return;
        }
        else if(!passworld1.equals(passworld2)){
            mdp2ET.setError("Mot de passe différent");
            return;
        }
        else if(passworld1.length()<5){
            mdp1ET.setError("Mot de passe trop court");
            return;
        }
        else if(!isVallidEmail(email)){
            emailEt.setError("Mail invalide");
            return;
        }
        mProgressDialog.setMessage("patientez svp");
        mProgressDialog.show();
        mProgressDialog.setCanceledOnTouchOutside(false);
        //creation du compte
        firebaseAuth.createUserWithEmailAndPassword(email,passworld1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //creation de la base de donné
                    UserDataClass dataClass=new UserDataClass(prenom,numtel,age,email,passworld1);

                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference mDbRef = mDatabase.getReference("Utilisateur");
                    mDbRef.child(passworld1).setValue(dataClass);


                    //reference=rootNode.getReference("Utilisateur");
                    //reference.child(prenom).setValue(dataClass);
                    //passage à l'autre activité
                    Toast.makeText(SignUpActivity.this,"Succés",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,AccueilActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Echec de la connexion",Toast.LENGTH_LONG).show();

                }
                mProgressDialog.dismiss();

            }

        });

    }


    private Boolean isVallidEmail(CharSequence email){
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}

