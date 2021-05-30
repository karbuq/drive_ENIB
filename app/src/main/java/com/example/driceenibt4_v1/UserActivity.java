package com.example.driceenibt4_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    //variable du layout
    private EditText Prenom,Nom,NumTel,Age,Email;
    private RadioButton mRadioButton;
    private Button mButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        //Lien entre xml et le java
        Prenom=findViewById(R.id.username);
        Nom=findViewById(R.id.username2);
        NumTel=findViewById(R.id.phonenumber);
        Age=findViewById(R.id.userage);
        mRadioButton=findViewById(R.id.accepterCondition);
        mButton=findViewById(R.id.ComfirmUser);
        Email=findViewById(R.id.email);

        //Enregistrer les donner dans Firebase
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode=FirebaseDatabase.getInstance();

                //recuperer les valeurs
                String prenom=Prenom.getText().toString();
                String nom=Nom.getText().toString();
                String age=Age.getText().toString();
                String numerotel=NumTel.getText().toString();
                String email=Prenom.getText().toString();

                //rajouter les conditions

                UserDataClass dataClass=new UserDataClass(prenom,nom,age,numerotel,email);

                reference=rootNode.getReference("Users");
                reference.child(prenom).setValue(dataClass);




                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("massage");

                myRef.setValue("Hello, World!");

                Toast.makeText(UserActivity.this,"Appuis bouton enregistré",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),AccueilActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}
