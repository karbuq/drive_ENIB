package com.example.driceenibt4_v1;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

import androidx.appcompat.app.AppCompatActivity;

public class CreerActivity extends AppCompatActivity {

    private EditText depart,arrivee,date,place;
    private Button valider;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_itineraire);

        depart = findViewById(R.id.depart);
        arrivee = findViewById(R.id.arrivee);
        date = findViewById(R.id.date);
        place = findViewById(R.id.place);
        valider = findViewById(R.id.valider);

        FirebaseDatabase rootNode;
        DatabaseReference reference;

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();

                //recuperer les valeurs
                String depart=depart.getText().toString();
                String arrivee=arrivee.getText().toString();
                String date=date.getText().toString();
                String place=place.getText().toString();

                //rajouter les conditions

                ItineraireDataClass dataClass=new ItineraireDataClass(depart,arrivee,date,place);

                reference=rootNode.getReference("Itineraire");
                reference.child(depart+arrivee+date+place).setValue(dataClass);

                Toast.makeText(UserActivity.this,"Appuis bouton enregistré",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),AccueilActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}
