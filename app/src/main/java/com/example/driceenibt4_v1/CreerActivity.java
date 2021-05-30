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

public class CreerActivity extends AppCompatActivity {

    private EditText Depart,Arrivee,Date,Place;
    private Button valider;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_itineraire);

        Depart = findViewById(R.id.depart);
        Arrivee = findViewById(R.id.arrivee);
        Date = findViewById(R.id.date);
        Place = findViewById(R.id.place);
        valider = findViewById(R.id.valider);


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();

                //recuperer les valeurs
                String depart=Depart.getText().toString();
                String arrivee=Arrivee.getText().toString();
                String date=Date.getText().toString();
                String place=Place.getText().toString();

                //rajouter les conditions

                ItineraireDataClass dataClass=new ItineraireDataClass(depart,arrivee,date,place);

                reference=rootNode.getReference("Itineraire");
                reference.child(depart+arrivee+date+place).setValue(dataClass);

                Intent intent=new Intent(getApplicationContext(),AccueilActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}
