package com.example.driceenibt4_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivty extends AppCompatActivity {

    private Button profillBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activty);

        profillBT=findViewById(R.id.profilbt);

        profillBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivty.this, ProfilActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}